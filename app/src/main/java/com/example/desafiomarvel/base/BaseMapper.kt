package com.example.desafiomarvel.base

abstract class BaseMapper<T, K> {
    abstract fun transformTo(source: T): K

    fun transformToList(source: List<T>): List<K> {
        return source.map { src -> transformTo(src) }
    }
}