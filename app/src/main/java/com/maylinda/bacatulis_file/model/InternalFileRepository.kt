package com.maylinda.bacatulis_file.model
//Pada model->InternalFileRepository ini  terdapat fungsi untuk menambahkan,mengambil serta menghapus catatan yang ada pada penyimpanan internal.
import android.content.Context
import java.io.File

class InternalFileRepository(var context: Context) :
    NoteRepository {

    private fun noteFile(fileName: String): File = File(noteDirectory(), fileName)
    private fun noteDirectory(): String = context.filesDir.absolutePath

//Fungsi addNote berguna untuk menambahkan catatan yang telah ditulis kedalam penyimpanan internal
    override fun addNote(note: Note) {
        context.openFileOutput(note.fileName, Context.MODE_PRIVATE).use { output ->
            output.write(note.noteText.toByteArray())
        }
    }
//Fungsi getNote berguna untuk mengambil dan menampilkan file yang sebelumnya telah dibuat dan disimpan pada penyimpanan internal melalui fungsi addNote.
    override fun getNote(fileName: String): Note {
        val note = Note(fileName, "")
        context.openFileInput(fileName).use { stream ->
            val text = stream.bufferedReader().use {
                it.readText()
            }
            note.noteText = text
        }
        return note
    }
//Fungsi deleteNote berguna untuk menghapus file dari penyimpanan internal dimana file ini yang semula telah disimpan menggunakan fungsi addNote
    override fun deleteNote(fileName: String): Boolean {
        return noteFile(fileName).delete()
    }
}

