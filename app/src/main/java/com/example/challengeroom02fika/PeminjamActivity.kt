package com.example.challengeroom02fika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengeroom02fika.Room.dbperpustakaan
import com.example.challengeroom02fika.Room.tbpinjam
import kotlinx.android.synthetic.main.activity_peminjam.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeminjamActivity : AppCompatActivity() {

    val db by lazy { dbperpustakaan(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peminjam)
        simpandata()

    }

    fun simpandata() {
        btn_simpanPinjam.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.TbpinjamDao().addtbpinjam(
                    tbpinjam(
                        ETidbukuPinjam.text.toString().toInt(),
                        ETnamaPeminjam.text.toString(),
                        ETjudulPinjam.text.toString(),
                        ETtanggalPinjam.text.toString(),
                        ETtanggalPembalian.text.toString(),
                        ETjumlahbuku.text.toString()
                    )
                )
                finish()


            }

        }
    }
}