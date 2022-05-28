package com.jmm.womensafety.repository

import com.jmm.womensafety.local.ContactDao
import com.jmm.womensafety.models.ContactModel
import javax.inject.Inject


class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
){

    fun getAllContacts() = contactDao.getAllContacts()

    suspend fun addNewContact(contactModel: ContactModel){
        contactDao.saveContact(contactModel)
    }

    suspend fun updateContact(contactModel: ContactModel){
        contactDao.updateContact(contactModel)
    }

    suspend fun deleteContact(contactModel: ContactModel){
        contactDao.delete(contactModel)
    }
}