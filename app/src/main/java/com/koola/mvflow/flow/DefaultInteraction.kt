package com.koola.mvflow.flow

import com.koola.mvflow.flow.impl.Interaction

sealed class DefaultInteraction : Interaction{
    data object CloseScreen : DefaultInteraction()
}