package com.example.desafiomarvel.domain.usecase

import com.example.desafiomarvel.TestObjectsConstants.limit
import com.example.desafiomarvel.TestObjectsConstants.offset
import com.example.desafiomarvel.TestObjectsConstants.pageInfo
import com.example.desafiomarvel.TestObjectsConstants.query
import com.example.desafiomarvel.TestObjectsConstants.runtimeException
import com.example.desafiomarvel.data.remote.repository.MarvelRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.fail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GetListCharactersUseCaseTest {

    private val mMarvelRepositoryImpl = mockk<MarvelRepositoryImpl>()
    private val mGetListCharactersUseCase = GetListCharactersUseCase(mMarvelRepositoryImpl)

    @Test
    fun useCase_checkResponse() {
        coEvery { mMarvelRepositoryImpl.getListCharacters(any(), any(), any()) } returns pageInfo
        runBlockingTest {
            mGetListCharactersUseCase.produce(
                GetListCharactersUseCase.Params(
                    offset,
                    limit,
                    query
                )
            ).map { page ->
                assertThat(page, `is`(pageInfo))
            }.catch {
                fail()
            }
        }
    }

    @Test
    fun useCase_checkFailure() {
        coEvery { mMarvelRepositoryImpl.getListCharacters(any(), any(), any()) } throws runtimeException
        runBlockingTest {
            mGetListCharactersUseCase.produce(
                GetListCharactersUseCase.Params(
                    offset,
                    limit,
                    query
                )
            ).map {
                fail()
            }.catch { err ->
                assertThat(err, `is`(runtimeException))
            }
        }
    }
}