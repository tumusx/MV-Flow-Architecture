# MV-Flow Architecture Documentation

## Overview

MV-Flow is an architectural pattern for separating screen flows within the `ViewModel` to manage multiple interaction flows that can be shared across various screens. This architecture allows for flexible state handling and event-based user interactions in Android applications, leveraging Kotlin `StateFlow` and `sealed` classes.

## Key Concepts

1. **ViewModel with Flow**: The `ViewModel` handles state and interactions by delegating them to an interface `IFlow<T>`. This interface defines a contract for managing the screen state (`StateFlow`) and user interactions (`Interaction`).

2. **IFlow Interface**: This interface separates the concerns of interaction handling from the `ViewModel`, allowing different implementations of flows for different screens.

3. **Interaction**: Interactions are defined as events triggered by the user, such as clicks or screen close actions. These interactions are handled by the `Flow` to update the screen state.

4. **State Management**: `State<T>` represents the state of the screen, such as `OnError` or `Content<T>`. This allows for clear representation of screen states that are updated based on user interactions.

5. **Flow Implementations**: Different flows (e.g., `DefaultFlow`, `SecondaryFlow`) handle interactions and update the state accordingly. These flows can be plugged into the `ViewModel`, allowing for easy customization and reusability across different screens.
