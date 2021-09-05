package com.mynotes.www

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.DateFormat
import java.util.*

class createNotes : AppCompatActivity() {

    private lateinit var save:Button
    private lateinit var title:EditText
    private lateinit var data:EditText
    private lateinit var date:TextView
    private lateinit var viewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.create_notes)
        save = findViewById(R.id.button)
        title = findViewById(R.id.noteTitle)
        data = findViewById(R.id.noteData)
        date = findViewById(R.id.noteDate)

        date.text = DateFormat.getDateTimeInstance().format(Date())

        val intent = intent
        val flag = intent.getBooleanExtra("edit",false)
        var tNote:Note? = null
        if(flag){
            title.setText(intent.getStringExtra("title"))
            data.setText(intent.getStringExtra("data"))
            date.text = intent.getStringExtra("date")
            tNote = Note(title.text.toString(),data.text.toString(),date.text.toString())
            tNote.id = intent.getIntExtra("id",0)
        }

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        save.setOnClickListener{
            saveNote(tNote)
        }
    }
    private fun saveNote(tNote:Note?){
        val note = Note(title.text.toString(),data.text.toString(),date.text.toString())
        if(tNote!=null){
            viewModel.deleteNote(tNote)
        }
        viewModel.insertNote(note)
        finish()
    }
}