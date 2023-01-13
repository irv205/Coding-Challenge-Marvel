package com.irv205.challengedecember.core.di

import com.irv205.challengedecember.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        MainViewModel(get(),get(qualifier = named("IO")),get(qualifier = named("MAIN")))
    }

}