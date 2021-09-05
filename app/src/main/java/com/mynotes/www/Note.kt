package com.mynotes.www

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name = "title")var title:String, @ColumnInfo(name = "data") var data:String,@ColumnInfo(name = "date")var date: String){
    @PrimaryKey(autoGenerate = true) var id=0

}