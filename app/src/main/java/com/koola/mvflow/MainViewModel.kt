package com.koola.mvflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koola.mvflow.flow.IFlow
import com.koola.mvflow.flow.StateResult
import com.koola.mvflow.flow.impl.DefaultRepository
import com.koola.mvflow.flow.impl.Interaction
import kotlinx.coroutines.launch

class MainViewModel<T>(private val iFlow: IFlow<T>, private val repository: DefaultRepository) :
    ViewModel() {
    val state = iFlow.onFlowValue

    fun onHandleUser(interaction: Interaction) {
        iFlow.onInteraction(interaction)
    }

    fun onLoadData() {
        viewModelScope.launch {
            repository.onLoadData()
            repository.state.collect {
                val result = if (it == "ERROR") {
                    StateResult.Error
                } else {
                    StateResult.Success
                }
                iFlow.onNavigation(result)
            }
        }
    }
}