package com.example.challengeroom02fika.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbbuku (
    @PrimaryKey
    val id_buku : Int,
    val kategori : String,
    val judul : String,
    val pengarang : String,
    val penerbit : String,
    val jml_buku : String
)
