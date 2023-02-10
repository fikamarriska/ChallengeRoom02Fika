package com.example.challengeroom02fika.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbpinjam (
    @PrimaryKey
    val id_bukuPinjam : Int,
    val nama_peminjam: String,
    val judul_pinjam : String,
    val tanggal_pinjam : String,
    val tanggal_pengembalian : String,
    val jumlah_buku : String
        )