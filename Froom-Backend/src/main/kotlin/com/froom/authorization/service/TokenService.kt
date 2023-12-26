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
    private final val ID: String = "id"
    private final val REFRESH: String = "refresh"

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
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims[ID] as Long
            val isRefresh = jwt.claims[REFRESH] as? Boolean
            if (isRefresh != null && isRefresh) {
                throw TokenException("Invalid token: Refresh token not allowed.")
            }
            userService.findById(userId)
        } catch (e: Exception) {
            throw TokenException("Invalid token")
        }
    }

    private fun createRefreshToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(refreshExpirationTime, refreshExpirationUnit))
            .subject(user.userName)
            .claim(ID, user.id)
            .claim(REFRESH, true)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    private fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(accessExpirationTime, accessExpirationUnit))
            .subject(user.userName)
            .claim(ID, user.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }
}