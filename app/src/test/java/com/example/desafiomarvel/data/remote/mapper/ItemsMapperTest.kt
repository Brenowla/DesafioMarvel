package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.TestObjectsConstants.comic
import com.example.desafiomarvel.TestObjectsConstants.itemResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ItemsMapperTest {

    @Test
    fun mapper_shouldConvertRight() {
        val mapped = ItemsMapper.transformTo(itemResponse)
        assertThat(mapped, `is`(comic))
    }
}