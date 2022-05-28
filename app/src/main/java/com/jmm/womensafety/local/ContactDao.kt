package com.jmm.womensafety.local

import androidx.room.*
import com.jmm.womensafety.models.ContactModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): Flow<List<ContactModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contactEntities: ContactModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContacts(contactEntities: List<ContactModel>)

    @Update
    suspend fun updateContact(contactEntity: ContactModel)

    @Query("DELETE FROM Contacts")
    suspend fun deleteAllContacts()

    @Delete
    suspend fun delete(contactEntity: ContactModel)
}
