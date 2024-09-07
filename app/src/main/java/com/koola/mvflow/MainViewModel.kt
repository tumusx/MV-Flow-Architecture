package com.koola.mvflow

import androidx.lifecycle.ViewModel
import com.koola.mvflow.flow.IFlow
import com.koola.mvflow.flow.Interaction

class MainViewModel<T>(private val iFlow: IFlow<T>) : ViewModel() {
    val state = iFlow.onFlowValue

    fun onHandleUser(interaction: Interaction){
        iFlow.onInteraction(interaction)
    }
}