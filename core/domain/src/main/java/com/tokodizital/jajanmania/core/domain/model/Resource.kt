package com.tokodizital.jajanmania.core.domain.model

sealed interface Resource<out T> {
    object Empty : Resource<Nothing>
    object Loading : Resource<Nothing>
    class Success<out T> (val data: T): Resource<T>
    class Error<out T> (val message: String, val data: T? = null): Resource<T>

}