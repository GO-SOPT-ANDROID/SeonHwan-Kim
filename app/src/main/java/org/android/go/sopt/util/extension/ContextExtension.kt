package org.android.go.sopt.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showShortToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.hideKeyboard(view: View){
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // hideSoftInputFromWindow는 첫번째 인자로 windowToken을 받아야함. windowToken은 View에서 사용?
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showShortSnackbar(view: View, message: String){
    // Snackbar.make는 첫번째 인자로 View를 받아야한다. 그래서 this(context)를 쓰면 에러남
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}