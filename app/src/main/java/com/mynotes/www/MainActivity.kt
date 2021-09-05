package com.mynotes.www

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), deleteClickListner {

    private lateinit var toast:Toast
    private lateinit var viewModel: NoteViewModel
    private lateinit var noNotes:TextView
    private lateinit var createButton:FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noNotes = findViewById(R.id.noNote)
        createButton = findViewById(R.id.createNoteButton)
        recyclerView = findViewById(R.id.notesList)
        createButton.setOnClickListener {
            createNote()
        }
        adapter = NoteAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //view Model
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this,{
            if(it!=null){
                adapter.updateList(it)
                noNotes.visibility = View.INVISIBLE
                if(it.isEmpty()){
                    noNotes.visibility = View.VISIBLE
                }
            }
            else{
                noNotes.visibility = View.VISIBLE
            }
    })
        toast = Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT)
    }

    private fun createNote() {
        val  intent = Intent(this,createNotes::class.java)
        intent.putExtra("edit",false)
        startActivity(intent)
    }

    override fun delNote(note: Note) {
        viewModel.deleteNote(note)
        toast.cancel()
        toast = Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun viewNote(note: Note) {
        val title = note.title
        val data = note.data
        val date = note.date
        val intent = Intent(this,createNotes::class.java)
        intent.putExtra("edit",true)
        intent.putExtra("title",title)
        intent.putExtra("data",data)
        intent.putExtra("date",date)
        intent.putExtra("id",note.id)
        startActivity(intent)
    }
}