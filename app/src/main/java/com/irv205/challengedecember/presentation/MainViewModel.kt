package com.irv205.challengedecember.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irv205.challengedecember.core.di.IODispatcher
import com.irv205.challengedecember.core.di.MainDisplatcher
import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
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

    private val _listComics = mutableStateListOf<Comics>()
    val listComics : SnapshotStateList<Comics> get() = _listComics

    private val _listSeries = mutableStateListOf<Series>()
    val listSeries : SnapshotStateList<Series> get() = _listSeries

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
            when(val result = repository.getHeroes(100)){
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


    fun getComicsList(id : Int){

        viewModelScope.launch(ioDispatcher) {
            when(val result = repository.getComics(id)){
                is DomainResponse.OnFailure -> {
                    withContext(mainDispatcher){
                        Log.e("ERRORRRRR", result.message)
                    }
                }
                is DomainResponse.Success -> {


                    withContext(mainDispatcher){
                        _listComics.clear()
                        _listComics.addAll(result.data)
                        _listComics.addAll(result.data)

                    }
                }
            }
        }
    }

    fun getSeriesList(id: Int){

        viewModelScope.launch(ioDispatcher) {
            when(val result = repository.getSeries(id)){
                is DomainResponse.OnFailure -> {
                    withContext(mainDispatcher){
                        Log.e("ERRORRRRR", result.message)
                    }
                }
                is DomainResponse.Success -> {
                    withContext(mainDispatcher){
                        _listSeries.clear()
                        _listSeries.addAll(result.data)

                    }
                }
            }
        }
    }

}