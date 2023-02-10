package com.example.challengeroom02fika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom02fika.Room.Constant
import com.example.challengeroom02fika.Room.dbperpustakaan
import com.example.challengeroom02fika.Room.tbbuku
import com.example.challengeroom02fika.Room.tbpinjam
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbperpustakaan(this) }
    lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
        halPinjam()

    }

    fun halPinjam(){
        btn_inputPinjam.setOnClickListener {
            startActivity(Intent(this, PeminjamActivity::class.java))
        }
    }


    override fun onStart() {
        super.onStart()
        loadData()

    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val buku = db.TbbukuDao().tampilsemuaa()
            Log.d("MainActivity","dbResponse: $buku")
            withContext(Dispatchers.Main){
                bukuAdapter.setData(buku)
            }
        }
    }

    fun halEdit(){
        btn_input.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(Idbuku: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id_buku", Idbuku)
                .putExtra("intent_type", intentType)
        )
    }



        private fun setupRecyclerView(){
        bukuAdapter = BukuAdapter(arrayListOf(), object: BukuAdapter.OnAdapterlistener{
            override fun onClik(tbbuku: tbbuku) {
                intentEdit(tbbuku.id_buku,Constant.TYPE_READ)
            }

            override fun onUpdate(tbbuku: tbbuku) {
                intentEdit(tbbuku.id_buku,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbbuku: tbbuku) {
                deleteDialog(tbbuku)
            }

        }
        )
        //id recyclerview
        listdatabuku.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bukuAdapter
        }
    }

    private fun deleteDialog(tbbuku: tbbuku){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("konfirmasi")
            setMessage("Yakin hapus ${tbbuku.judul}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.TbbukuDao().deletetbbuku(tbbuku)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        alertDialog.show()
    }


}