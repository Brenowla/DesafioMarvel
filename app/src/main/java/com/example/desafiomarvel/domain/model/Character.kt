package com.example.desafiomarvel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val img: String,
    val comics: List<Comics>
): Parcelable