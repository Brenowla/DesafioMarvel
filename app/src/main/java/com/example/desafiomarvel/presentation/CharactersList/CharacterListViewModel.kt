package com.example.desafiomarvel.presentation.CharactersList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiomarvel.constants.MarvelConstants.LIMIT
import com.example.desafiomarvel.domain.model.PageInfo
import com.example.desafiomarvel.domain.usecase.GetListCharactersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val mGetListCharactersUseCase: GetListCharactersUseCase
) : ViewModel() {

    private val mPage = MutableLiveData<PageInfo>()
    val page: LiveData<PageInfo>
        get() = mPage

    private val mLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = mLoading

    private var mPageNumber = 0
    val pageNumber: Int
        get() = mPageNumber

    private var mQuery: String? = null

    fun loadInitialPage(query: String) {
        if(query == mQuery) return
        mQuery = if (query.isBlank()) {
            null
        } else {
            query
        }
        mPageNumber = 0
        getCharacters()
    }

    fun getCharacters() {
        mLoading.value = true
        mGetListCharactersUseCase.produce(
            GetListCharactersUseCase.Params(
                mPageNumber * LIMIT,
                LIMIT,
                mQuery
            )
        )
            .map { pageInfo ->
                mPage.value = pageInfo
            }
            .onCompletion {
                mLoading.value = false
            }
            .catch { err ->
                Log.d("Error", err.message ?: "")
            }.launchIn(viewModelScope)
    }

    fun nextPage() {
        mPage.value?.let { pageInfo ->
            if ((mPageNumber * LIMIT + LIMIT) < pageInfo.total && loading.value == false) {
                mPageNumber += 1
                getCharacters()
            }
        }
    }

    fun backPage() {
        mPage.value?.let { pageInfo ->
            if (mPageNumber > 0 && loading.value == false) {
                mPageNumber -= 1
                getCharacters()
            }
        }
    }

    @TestOnly
    fun getPage() = mPage

    @TestOnly
    fun setPageNumber(page: Int) {
        mPageNumber = page
    }

    @TestOnly
    fun getLoading() = mLoading
}