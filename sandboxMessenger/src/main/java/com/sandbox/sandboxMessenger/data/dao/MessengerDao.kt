package com.sandbox.sandboxMessenger.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sandbox.sandboxMessenger.data.response.MessengerResponse

@Dao
interface MessengerDao {

    @Insert
    suspend fun insert(data: MessengerResponse)
    @Update
    suspend fun update(data: MessengerResponse)
    @Query("SELECT * FROM messenger")
    fun getAllData(): List<MessengerResponse>

    @Query("SELECT * FROM messenger WHERE messenger.id==0")
    fun getData(): MessengerResponse


    @Delete
    suspend fun delete(friend: MessengerResponse)

}