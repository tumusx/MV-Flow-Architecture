package com.koola.mvflow.flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface IFlow<T> {
    val onFlowValue: StateFlow<T>
    fun onInteraction(eventClick: Interaction)
}

sealed class DefaultInteraction : Interaction {
    data object CloseScreen : DefaultInteraction()
}

sealed interface Interaction {
    data class EventClick(val action: String) : Interaction
}

sealed interface State<T> {
    data object OnError : State<Nothing>
    data class Content<T>(val content: T?) : State<T>
}

class DefaultFlow : IFlow<State<DefaultContentSuccess>> {
    private val _state = MutableStateFlow(State.Content<DefaultContentSuccess>(null))

    override val onFlowValue: StateFlow<State<DefaultContentSuccess>>
        get() = _state.asStateFlow()

    override fun onInteraction(eventClick: Interaction) {
        when (eventClick) {
            is DefaultInteraction.CloseScreen -> {
                _state.update { State.Content(DefaultContentSuccess(123, "José")) }
            }

            is Interaction.EventClick -> {
                _state.update { State.Content(DefaultContentSuccess(1, "Silva")) }
            }
        }
    }
}


class SecondaryFlow : IFlow<State<SecondaryContentSuccess>> {
    private val _state = MutableStateFlow(State.Content<SecondaryContentSuccess>(null))

    override val onFlowValue: StateFlow<State<SecondaryContentSuccess>>
        get() = _state.asStateFlow()

    override fun onInteraction(eventClick: Interaction) {
        when (eventClick) {
            is DefaultInteraction.CloseScreen -> {
                _state.update { State.Content(SecondaryContentSuccess(123, "Ribeiro")) }
            }

            is Interaction.EventClick -> {
                _state.update { State.Content(SecondaryContentSuccess(1, "Alves")) }
            }
        }
    }
}

data class DefaultContentSuccess(
    val idUser: Int,
    val nameUser: String
)

data class SecondaryContentSuccess(
    val idUser: Int,
    val nameUser: String
)
