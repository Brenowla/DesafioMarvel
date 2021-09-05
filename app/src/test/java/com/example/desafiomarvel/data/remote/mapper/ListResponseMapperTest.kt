package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.TestObjectsConstants.listResponse
import com.example.desafiomarvel.TestObjectsConstants.pageInfo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ListResponseMapperTest {

    @Test
    fun mapper_shouldConvertRight() {
        val mapped = ListResponseMapper.transformTo(listResponse)
        assertThat(mapped, `is`(pageInfo))
    }
}