package com.jmm.womensafety.viewmodel

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
class AddEditContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {



    val contacts = contactRepository.getAllContacts().asLiveData()

    fun addContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.addNewContact(contactModel)
        }
    }

    fun updateContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.updateContact(contactModel)
        }
    }
}