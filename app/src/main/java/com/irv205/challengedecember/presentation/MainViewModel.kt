package com.irv205.challengedecember.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irv205.challengedecember.di.IODispatcher
import com.irv205.challengedecember.di.MainDisplatcher
import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MarvelRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDisplatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _list = mutableStateListOf<Hero>()
    val list : SnapshotStateList<Hero> get() = _list

    private val _selectedHero = mutableStateOf<Hero?>(null)
    val selectedHero : State<Hero?> get() = _selectedHero

    fun setHero(hero: Hero){
        _selectedHero.value = hero
    }

    init {
        getHeroesList()
    }

    private fun getHeroesList(){

        viewModelScope.launch(ioDispatcher) {
            when(val result = repository.getHeroes()){
                is DomainResponse.OnFailure -> {
                    withContext(mainDispatcher){
                        Log.e("ERRORRRRR", result.message)
                    }
                }
                is DomainResponse.Success -> {
                    withContext(mainDispatcher){
                        _list.clear()
                        _list.addAll(result.data)

                    }
                }
            }
        }
    }

}