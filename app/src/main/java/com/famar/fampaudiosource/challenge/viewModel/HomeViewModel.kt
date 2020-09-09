package com.famar.fampaudiosource.challenge.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.model.results
import com.famar.fampaudiosource.challenge.repositories.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository):ViewModel() {

    private val _user = MutableLiveData<results>()
    val user : LiveData<results>
    get() = _user


    private val _userSaved = MutableLiveData<List<UserSaved>>()
    val userSaved: LiveData<List<UserSaved>>
    get() = _userSaved


    fun getAllUsers(){
        viewModelScope.launch {
            _user.value = userRepository.getUsers()
        }
    }

    fun getSavedUsers(){
        viewModelScope.launch {
            _userSaved.value = userRepository.getUsersResultsDb()
        }
    }
}