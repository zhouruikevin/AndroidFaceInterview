package com.ellen.androidface

import android.app.Activity
import android.content.Context
import android.telecom.Call
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import kotlinx.coroutines.*
import kotlin.coroutines.resume


fun EditText.addTextChangedListener(init: (TextWatcherEmptyImpl.() -> Unit)) = this.addTextChangedListener(
        TextWatcherEmptyImpl().apply { init() }
)

class TextWatcherEmptyImpl(var beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
                           var onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
                           var afterTextChanged: ((Editable?) -> Unit)? = null) : TextWatcher {


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged?.invoke(s)
    }

    fun beforeTextChanged(listener: ((CharSequence?, Int, Int, Int) -> Unit)) {
        beforeTextChanged = listener
    }

    fun onTextChanged(listener: ((CharSequence?, Int, Int, Int) -> Unit)) {
        onTextChanged = listener
    }

    fun afterTextChanged(listener: ((Editable?) -> Unit)) {
        afterTextChanged = listener
        runBlocking { }
    }

}


suspend fun await(editText: EditText): String = kotlin.coroutines.suspendCoroutine { cont ->
    editText.addTextChangedListener {
        onTextChanged { s, start, before, after ->
            cont.resume("$s,$start,$before,$after")
        }
    }
}

fun test(context: Context) {
    val editText = EditText(context)
    runBlocking {
        val value = await(editText)
        launch(context = Dispatchers.Main) {

        }
    }

    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
    editText.addTextChangedListener {
        onTextChanged { s, start, before, after -> Log.d("wtf", "$s,$start,$before,$after") }
    }
}