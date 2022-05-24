package com.maylinda.bacatulis_file.model
// File NoteRepository ini berfungsi untuk mendeklarasikan fungsi menambah,mengambil untuk dibaca,serta menghapus data catatan beserta tipe data yang diizikan pada masing-masing fungsi.
interface NoteRepository {
    fun addNote(note: Note)
    fun getNote(fileName: String): Note
    fun deleteNote(fileName: String): Boolean
}