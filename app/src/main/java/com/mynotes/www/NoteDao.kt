package com.mynotes.www

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("SELECT * from notesTable order by date DESC")
    fun getAllNotes():LiveData<List<Note>>

}