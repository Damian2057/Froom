package com.froom.util.retrofit.category

import lombok.extern.slf4j.Slf4j
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@Slf4j
class CategoryAdapter(environment: Environment) {

    val categoryUrl: String = environment.getRequiredProperty("CATEGORY_URL")


}