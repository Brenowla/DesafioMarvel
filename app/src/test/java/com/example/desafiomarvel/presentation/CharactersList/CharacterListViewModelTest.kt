package com.example.desafiomarvel.presentation.CharactersList

import android.icu.text.UnicodeSetIterator
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafiomarvel.TestObjectsConstants.pageInfo
import com.example.desafiomarvel.domain.usecase.GetListCharactersUseCase
import com.example.desafiomarvel.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    private val mGetListUseCase = mockk<GetListCharactersUseCase>()
    private lateinit var mCharactersListViewModel: CharacterListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        mCharactersListViewModel = CharacterListViewModel(mGetListUseCase)
    }

    @Test
    fun getCharacters_shouldReturnPageInfo() {
        coEvery {mGetListUseCase.produce(any())} returns flow { emit(pageInfo) }
        dispatcher.runBlockingTest {
            dispatcher.pauseDispatcher()
            mCharactersListViewModel.getCharacters()
            assertThat(mCharactersListViewModel.loading.getOrAwaitValue(), `is`(true))
            dispatcher.resumeDispatcher()
            assertThat(mCharactersListViewModel.loading.getOrAwaitValue(), `is`(false))
            assertThat(mCharactersListViewModel.page.getOrAwaitValue(), `is`(pageInfo))
        }
    }

    @Test
    fun nextPage_shouldIncreasePageNumber() {
        coEvery {mGetListUseCase.produce(any())} returns flow { emit(pageInfo) }
        mCharactersListViewModel.getPage().value = pageInfo
        mCharactersListViewModel.getLoading().value = false
        mCharactersListViewModel.setPageNumber(0)
        mCharactersListViewModel.nextPage()
        assertThat(mCharactersListViewModel.pageNumber, `is`(1))
    }

    @Test
    fun backPage_shouldIncreasePageNumber() {
        coEvery {mCharactersListViewModel.getCharacters()} returns Unit
        mCharactersListViewModel.getPage().value = pageInfo
        mCharactersListViewModel.getLoading().value = false
        mCharactersListViewModel.setPageNumber(1)
        mCharactersListViewModel.backPage()
        assertThat(mCharactersListViewModel.pageNumber, `is`(0))
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }
}