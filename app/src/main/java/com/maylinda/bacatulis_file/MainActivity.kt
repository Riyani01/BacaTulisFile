package com.maylinda.bacatulis_file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maylinda.bacatulis_file.databinding.ActivityMainBinding
import com.maylinda.bacatulis_file.model.InternalFileRepository
import com.maylinda.bacatulis_file.model.Note
import com.maylinda.bacatulis_file.model.NoteRepository

class MainActivity : AppCompatActivity() {
//Pada mainactivity ini menggunakan metode binding
    private val repo: NoteRepository by lazy { InternalFileRepository(this) }
    private lateinit var binding: ActivityMainBinding
// Yakni pertama harus memanggil binding dengan tujuan ke Activitymain
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
// Dengan metode binding ini ketika button pada btnWrite di klik maka akan menambahkan data ke addNote. Ini akan dijalankan ketika pada editFileName tidak kosong,
// dimana fungsi note diatur untuk menjadikan inputan dari editFileName serta editTeksCatatan diubah dari yang semula berbentuk text menjadi string.
// Jika terdapat kekeliruan dalam pengisian lalu user mengklik save maka akan muncul pernyataan "File Write Failed" hal ini adanya syarat didalam catch.
// Jika editFileName kosong kemudian user mengklik button btnWwrite maka secara default akan muncul pernyataan "Please provide a Filename", hal ini disebabkan karena adanya syarat menggunakan else.


        binding.btnWrite.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    repo.addNote(
                        Note(
                            binding.editFileName.text.toString(),
                            binding.editTeksCatatan.text.toString()
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(this, "File Write Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    // Dengan metode binding ini ketika button pada btnRead di klik maka akan menampilkan data dari file yang tersimpan di penyimpanan internal. Ini akan dijalankan ketika pada editFileName tidak kosong,
// Jika terdapat kekeliruan dalam pengisian lalu user mengklik read maka akan muncul pernyataan "File Write Failed" hal ini adanya syarat didalam catch.
// Jika editFileName kosong kemudian user mengklik button btnRead maka secara default akan muncul pernyataan "Please provide a Filename", hal ini disebabkan karena adanya syarat menggunakan else.

    binding.btnRead.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    val note = repo.getNote(binding.editFileName.text.toString())
                    binding.editTeksCatatan.setText(note.noteText)
                } catch (e: Exception) {
                    Toast.makeText(this, "File Read Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    // Dengan metode binding ini ketika button pada btnDelet di klik maka akan menghapus data dari file yang tersimpan di penyimpanan internal. Ini akan dijalankan ketika pada editFileName tidak kosong,
// Jika terdapat kekeliruan dalam pengisian lalu user mengklik delete maka akan muncul pernyataan "File Could Not Be Deleted" hal ini adanya syarat didalam else.
// Jika editFileName kosong kemudian user mengklik button btnDelete maka secara default akan muncul pernyataan "Please provide a Filename", hal ini disebabkan karena adanya syarat menggunakan else.

        binding.btnDelete.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    if (repo.deleteNote(binding.editFileName.text.toString())) {
                        Toast.makeText(this, "File Deleted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "File Could Not Be Deleted", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "File Delete Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    }
}
