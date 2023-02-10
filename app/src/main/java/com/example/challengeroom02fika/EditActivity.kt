package com.example.challengeroom02fika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom02fika.Room.Constant
import com.example.challengeroom02fika.Room.dbperpustakaan
import com.example.challengeroom02fika.Room.tbbuku
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { dbperpustakaan(this) }
    private var tbbukuIdbuku: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        simpandata()
        setupView()
       tbbukuIdbuku = intent.getIntExtra("intent_id_buku", 0)
        Toast.makeText(this,tbbukuIdbuku.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_simpan.visibility = View.GONE
                btn_update.visibility = View.GONE
                ETidbukuPinjam.visibility = View.GONE
                tampilbuku()
            }

            Constant.TYPE_UPDATE -> {
                btn_simpan.visibility = View.GONE
                ETidbukuPinjam.visibility = View.GONE
                tampilbuku()
            }

        }
    }

    fun simpandata(){
        btn_simpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.TbbukuDao().addtbbuku(
                    tbbuku(ETidbukuPinjam.text.toString().toInt(),
                        ETnamaPeminjam.text.toString(),
                        ETjudulPinjam.text.toString(),
                        ETtanggalPinjam.text.toString(),
                        ETtanggalPembalian.text.toString(),
                        ETjumlahbuku.text.toString())
                )
                finish()
            }
        }

        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.TbbukuDao().updatetbbuku(
                    tbbuku(tbbukuIdbuku,
                        ETnamaPeminjam.text.toString(),
                        ETjudulPinjam.text.toString(),
                        ETtanggalPinjam.text.toString(),
                        ETtanggalPembalian.text.toString(),
                        ETjumlahbuku.text.toString())
                )
                finish()
            }
        }
    }

    fun tampilbuku(){
        tbbukuIdbuku = intent.getIntExtra("intent_id_buku", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val buk = db.TbbukuDao().tampilsemua(tbbukuIdbuku)[0]
            //ETnis.setText(siswa.nis)
            ETnamaPeminjam.setText(buk.kategori)
            ETjudulPinjam.setText(buk.judul)
            ETtanggalPinjam.setText(buk.pengarang)
            ETtanggalPembalian.setText(buk.penerbit)
            ETjumlahbuku.setText(buk.jml_buku)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}