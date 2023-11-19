package com.froom.authorization.util

import com.froom.user.model.domain.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return this.principal as User
}