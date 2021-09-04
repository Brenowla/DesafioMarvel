package com.example.desafiomarvel.extensions

import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> = callbackFlow {
    this@textChanges.doOnTextChanged { text, _, _, _ ->
        this.offer(text)
    }
    awaitClose { this@textChanges.doOnTextChanged { _, _, _, _ -> null } }
}
