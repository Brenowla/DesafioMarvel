package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.TestObjectsConstants
import com.example.desafiomarvel.TestObjectsConstants.characterResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CharactersResponseMapperTest {

    @Test
    fun mapper_shouldConvertRight(){
        val mapped = CharactersResponseMapper.transformTo(characterResponse)
        assertThat(mapped, `is`(TestObjectsConstants.character))
    }
}