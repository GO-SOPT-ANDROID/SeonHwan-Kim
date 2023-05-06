package org.android.go.sopt.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String? = "",
    val password: String? = "",
    val name: String? = "",
    val specialty: String? = "",
) : Parcelable