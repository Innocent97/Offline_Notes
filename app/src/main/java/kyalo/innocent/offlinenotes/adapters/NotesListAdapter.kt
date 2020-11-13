package kyalo.innocent.offlinenotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kyalo.innocent.offlinenotes.BR
import kyalo.innocent.offlinenotes.R
import kyalo.innocent.offlinenotes.databinding.LayoutItemBinding
import kyalo.innocent.offlinenotes.ui.home.HomeFragmentDirections
import kyalo.innocent.roomdb.db.Note

class NotesListAdapter(private val notesList : List<Note>)
    : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding: LayoutItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item, parent, false)

        return  NoteViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //get the current note to be displayed
        val displayedNote: Note = notesList[position]

        holder.noteBinding!!.setVariable(BR.noteItem, displayedNote)
        holder.noteBinding!!.executePendingBindings()
      /*  holder.tvNoteTitle.text = notesList.get(position).title
        holder.tvNoteContent.text = notesList.get(position).note*/

        // add on note click listener
        holder.itemView.setOnClickListener{
            val action = HomeFragmentDirections.actionCreateNote(notesList[position])
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int = notesList.size

    // Note View Holder class
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteBinding : LayoutItemBinding?

        init {
            noteBinding = DataBindingUtil.bind(itemView)
        }

        var tvNoteTitle : TextView = itemView.findViewById(R.id.tv_note_title)
        var tvNoteContent : TextView = itemView.findViewById(R.id.tv_note_content)
    }
}