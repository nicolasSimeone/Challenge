package com.famar.fampaudiosource.challenge.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.repositories.UserRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val userRepository: UserRepository):ViewModel() {

    fun saveUser(email:String?, picture:String?, name:String?, phone:String? ){
        viewModelScope.launch {
            userRepository.insertUserSavedDb(email, picture, name, phone)
        }
    }
}