package com.example.aula2.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aula2.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {
    @Insert
    suspend fun insert(example: Car)

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getById(id: Int): Flow<Car>

    @Delete
    suspend fun delete(example: Car)
}