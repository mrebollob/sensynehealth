package com.mrebollob.codetest.presentation

import com.mrebollob.codetest.core.entity.Failure

sealed class ScreenState
object ReadyState : ScreenState()
object LoadingState : ScreenState()
data class ErrorState(val failure: Failure) : ScreenState()
