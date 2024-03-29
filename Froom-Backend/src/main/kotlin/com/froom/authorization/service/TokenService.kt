package com.froom.authorization.service

import com.froom.authorization.model.dto.TokenDto
import com.froom.exception.type.TokenException
import com.froom.user.model.domain.User
import com.froom.user.service.UserService
import com.froom.user.util.toDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService,
    @Value("\${spring.security.jwt.prefix}")
    private val tokenType: String,
    @Value("\${spring.security.jwt.access.expiration}")
    private val accessExpirationTime: Long,
    @Value("\${spring.security.jwt.access.unit}")
    private val accessExpirationUnit: ChronoUnit,
    @Value("\${spring.security.jwt.refresh.expiration}")
    private val refreshExpirationTime: Long,
    @Value("\${spring.security.jwt.refresh.unit}")
    private val refreshExpirationUnit: ChronoUnit,
) {
    private final val INVALID_TOKEN = "Invalid token"
    private final val UUID_SIGNATURE: String = "UUID"
    private final val REFRESH: String = "REFRESH"
    private final val ALGORITHM = "HS256"

    fun generateToken(user: User): TokenDto {
        return TokenDto(
            accessToken = createToken(user),
            refreshToken = createRefreshToken(user),
            expiresIn = accessExpirationTime,
            unit = accessExpirationUnit.name,
            tokenType = tokenType,
            user = user.toDto(),
        )
    }

    fun checkTokenAndReturnUser(token: String): User? {
        return try {
            val jwt = decodeToken(token)
            val uuid: UUID = getUUIDFromClaim(jwt)
            val isRefresh = jwt.claims[REFRESH] as? Boolean
            if (isRefresh != null && isRefresh) {
                throw TokenException("Invalid token: Refresh token not allowed.")
            }
            userService.findByUuid(uuid)
        } catch (e: Exception) {
            throw TokenException(INVALID_TOKEN)
        }
    }

    fun checkRefreshTokenAndReturnUser(token: String): User {
        return try {
            val jwt = decodeToken(token)
            val uuid: UUID = getUUIDFromClaim(jwt)
            val isRefresh = jwt.claims[REFRESH] as? Boolean
            if (isRefresh != null && !isRefresh) {
                throw TokenException("Invalid token: Access token not allowed.")
            }
            userService.findByUuid(uuid) ?: throw TokenException("Invalid token")
        } catch (e: Exception) {
            throw TokenException(INVALID_TOKEN)
        }
    }

    private fun decodeToken(token: String): Jwt {
        return jwtDecoder.decode(token)
    }

    private fun getUUIDFromClaim(jwt: Jwt): UUID {
        return when (val uuidClaim = jwt.claims[UUID_SIGNATURE]) {
            is String -> UUID.fromString(uuidClaim)
            is UUID -> uuidClaim
            else -> throw TokenException("Unexpected type for UUID claim")
        }
    }

    private fun createRefreshToken(user: User): String {
        val jwsHeader = JwsHeader.with { ALGORITHM }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(refreshExpirationTime, refreshExpirationUnit))
            .subject(user.userName)
            .claim(UUID_SIGNATURE, user.uuid)
            .claim(REFRESH, true)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    private fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { ALGORITHM }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(accessExpirationTime, accessExpirationUnit))
            .subject(user.userName)
            .claim(UUID_SIGNATURE, user.uuid)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }
}