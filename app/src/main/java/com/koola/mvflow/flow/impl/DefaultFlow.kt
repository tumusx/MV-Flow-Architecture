package com.koola.mvflow.flow.impl

import com.koola.mvflow.flow.DefaultContentSuccess
import com.koola.mvflow.flow.DefaultInteraction
import com.koola.mvflow.flow.IFlow
import com.koola.mvflow.flow.SecondaryContentSuccess
import com.koola.mvflow.flow.SecondaryInteraction
import com.koola.mvflow.flow.State
import com.koola.mvflow.flow.StateResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface Interaction {
    data class OnEventClick(val id: String) : Interaction
}

class DefaultRepository(private val value: String) {
    val state = MutableStateFlow("")

    fun onLoadData() {
        state.value = value
    }
}

class DefaultFlow : IFlow<State<DefaultContentSuccess>> {
    private var number = 0
    private val _state = MutableStateFlow(State.Content<DefaultContentSuccess>(null))

    override val onFlowValue: StateFlow<State<DefaultContentSuccess>>
        get() = _state.asStateFlow()


    override fun onInteraction(eventClick: Interaction) {
        when (eventClick) {
            is DefaultInteraction.CloseScreen -> {
                _state.update { State.Content(DefaultContentSuccess(123, "José")) }
            }
        }
    }

    override fun onNavigation(navigator: StateResult) {
        when (navigator) {
            is StateResult.Error -> _state.update {
                State.Content(
                    DefaultContentSuccess(
                        123,
                        "Indo para tela de Erro"
                    )
                )
            }

            is StateResult.Success -> _state.update {
                State.Content(
                    DefaultContentSuccess(
                        123,
                        "Indo para tela de Sucesso"
                    )
                )
            }
        }
    }
}


class SecondaryFlow : IFlow<State<SecondaryContentSuccess>> {
    private var number = 0
    private val _state = MutableStateFlow(State.Content<SecondaryContentSuccess>(null))

    override val onFlowValue: StateFlow<State<SecondaryContentSuccess>>
        get() = _state.asStateFlow()


    override fun onInteraction(eventClick: Interaction) {
        number += 20
        when (eventClick) {

            is SecondaryInteraction.SeeResults -> {
                _state.update { State.Content(SecondaryContentSuccess(123, "$number Ribeiro")) }
            }

            is SecondaryInteraction.SeeProfile -> {
                _state.update { State.Content(SecondaryContentSuccess(123, "Jose")) }
            }

        }
    }

    override fun onNavigation(navigator: StateResult) {
        when (navigator) {
            is StateResult.Error -> _state.update {
                State.Content(
                    SecondaryContentSuccess(
                        123,
                        "Indo para tela de Erro"
                    )
                )
            }

            is StateResult.Success -> _state.update {
                State.Content(
                    SecondaryContentSuccess(
                        123,
                        "Indo para tela de Sucesso"
                    )
                )
            }
        }
    }
}