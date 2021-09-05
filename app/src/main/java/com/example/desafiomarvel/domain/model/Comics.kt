package com.example.desafiomarvel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comics (
    val uri: String,
    val name: String
):Parcelable