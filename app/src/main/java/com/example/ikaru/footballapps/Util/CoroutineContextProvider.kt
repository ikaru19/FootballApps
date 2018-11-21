package com.example.ikaru.footballapps.Util

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}