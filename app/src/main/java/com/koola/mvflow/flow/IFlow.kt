package com.koola.mvflow.flow

import com.koola.mvflow.flow.impl.Interaction
import kotlinx.coroutines.flow.StateFlow

interface IFlow<T> {
    val onFlowValue: StateFlow<T>
    fun onInteraction(eventClick: Interaction)
    fun onNavigation(navigator: StateResult)
}

interface StateResult {
    data object Error : StateResult
    data object Success : StateResult
}

sealed interface State<T> {
    data object OnError : State<Nothing>
    data class Content<T>(val content: T?) : State<T>
}

data class DefaultContentSuccess(
    val idUser: Int,
    val nameUser: String
)

data class SecondaryContentSuccess(
    val idUser: Int,
    val nameUser: String
)
