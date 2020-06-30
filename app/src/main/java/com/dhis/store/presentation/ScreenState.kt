package com.dhis.store.presentation

import com.dhis.store.core.entity.Failure

sealed class ScreenState
object ReadyState : ScreenState()
object LoadingState : ScreenState()
data class ErrorState(val failure: Failure) : ScreenState()
