package com.irv205.challengedecember.presentation

sealed class mainViewState {
    object Home : mainViewState()
    object Detail : mainViewState()
    class Error(val message: String) : mainViewState()
}