package com.koola.mvflow.flow

import com.koola.mvflow.flow.impl.Interaction

sealed class SecondaryInteraction : Interaction {
    data object SeeProfile : SecondaryInteraction()
    data object SeeResults : SecondaryInteraction()
}
