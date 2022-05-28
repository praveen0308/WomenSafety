package com.jmm.womensafety.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jmm.womensafety.models.ContactModel
import com.jmm.womensafety.repository.ContactRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteContactsViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    val mode = MutableLiveData(0)

    val contacts = contactRepository.getAllContacts().asLiveData()

    fun deleteContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contactModel)
        }
    }
}