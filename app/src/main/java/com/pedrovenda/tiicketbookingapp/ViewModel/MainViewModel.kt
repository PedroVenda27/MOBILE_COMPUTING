package com.pedrovenda.tiicketbookingapp.ViewModel

import androidx.lifecycle.LiveData
import com.pedrovenda.tiicketbookingapp.Activities.Domain.FlightModel
import com.pedrovenda.tiicketbookingapp.Activities.Domain.LocationModel
import com.pedrovenda.tiicketbookingapp.Repository.MainRepository

class MainViewModel {

    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from: String, to: String):
            LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}