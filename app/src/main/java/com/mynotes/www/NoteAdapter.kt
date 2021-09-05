package com.mynotes.www

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter(private val context:Context, private val listner:deleteClickListner): RecyclerView.Adapter<NoteViewHolder>() {

    private val notesList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      val noteView = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        val holder =  NoteViewHolder(noteView)
        holder.deleteButton.setOnClickListener {
            listner.delNote(notesList[holder.adapterPosition])
        }
        noteView.setOnClickListener {
            listner.viewNote(notesList[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
      holder.title.text = notesList[position].title
      holder.data.text = notesList[position].data
        holder.date.text = notesList[position].date
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    fun updateList(list:List<Note>){
        notesList.clear()
        notesList.addAll(list)

        notifyDataSetChanged()
    }
}
class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   var deleteButton = itemView.findViewById<ImageView>(R.id.delete)
    var title = itemView.findViewById<TextView>(R.id.nTitle)
    var data = itemView.findViewById<TextView>(R.id.nData)
    var date = itemView.findViewById<TextView>(R.id.nDate)
}
interface deleteClickListner{
    fun delNote(note:Note)
    fun viewNote(note:Note)
}