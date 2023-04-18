package org.android.go.sopt.data

import androidx.annotation.DrawableRes

data class Dog(
    val name: String,
    @DrawableRes val image: Int,
    val size: String
)