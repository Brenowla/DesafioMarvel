package com.example.desafiomarvel.data.remote.repository

import com.example.desafiomarvel.TestObjectsConstants.limit
import com.example.desafiomarvel.TestObjectsConstants.listResponse
import com.example.desafiomarvel.TestObjectsConstants.offset
import com.example.desafiomarvel.TestObjectsConstants.pageInfo
import com.example.desafiomarvel.TestObjectsConstants.query
import com.example.desafiomarvel.TestObjectsConstants.runtimeException
import com.example.desafiomarvel.data.remote.service.MarvelCharactersService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.fail
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.RuntimeException

class MarvelRepositoryImplTest {

    private val mMarvelCharactersService = mockk<MarvelCharactersService>()
    private val mMarvelRepositoryImpl = MarvelRepositoryImpl(mMarvelCharactersService)

    @Test
    fun getListCharacters_checkResponseAndCall() {
        coEvery { mMarvelCharactersService.getCharacters(any(), any(), any()) } returns listResponse
        runBlockingTest {
            val response = mMarvelRepositoryImpl.getListCharacters(offset, limit, query)
            assertThat(response, `is`(pageInfo))
            coVerify(exactly = 1) { mMarvelCharactersService.getCharacters(any(), any(), any()) }
        }
    }

    @Test
    fun getListCharacters_checkFailure() {
        coEvery { mMarvelCharactersService.getCharacters(any(), any(), any()) } throws runtimeException
        runBlockingTest {
            try {
                mMarvelRepositoryImpl.getListCharacters(offset, limit, query)
                fail()
            } catch (err: RuntimeException) {
                assertThat(err, `is`(runtimeException))
            }
        }
    }
}