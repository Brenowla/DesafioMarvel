package com.example.desafiomarvel.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface UseCase<Type> {
    val dispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    fun observe(): Flow<Type?>
}
