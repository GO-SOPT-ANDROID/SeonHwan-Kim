package org.android.go.sopt.presentation.main.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.data.Dog

class HomeViewModel: ViewModel() {
    val mockDogList = listOf<Dog>(
        Dog("포메라니안", R.drawable.img_pomeranian, SIZE_SMALL),
        Dog("말티즈", R.drawable.img_maltese, SIZE_SMALL),
        Dog("토이 푸들", R.drawable.img_toy_poodle, SIZE_SMALL),
        Dog("웰시코기", R.drawable.img_welshi_corgi, SIZE_MEDIUM),
        Dog("진돗개", R.drawable.img_jindo, SIZE_MEDIUM),
        Dog("비글", R.drawable.img_beagle, SIZE_MEDIUM),
        Dog("골든 리트리버", R.drawable.img_golden_retriever, SIZE_LARGE),
        Dog("셰퍼드", R.drawable.img_shepherd, SIZE_LARGE)
    )

    companion object {
        private const val SIZE_SMALL = "소형견"
        private const val SIZE_MEDIUM = "중형견"
        private const val SIZE_LARGE = "대형견"
    }
}