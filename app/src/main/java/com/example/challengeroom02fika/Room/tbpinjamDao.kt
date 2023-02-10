package com.example.challengeroom02fika.Room

import androidx.room.*

@Dao
interface tbpinjamDao {
    @Insert
    fun addtbpinjam (Tbpinjam: tbpinjam)
    @Update
    fun updatetbpinjam (Tbpinjam: tbpinjam)
    @Delete
    fun deletetbpinjam (Tbpinjam: tbpinjam)
    @Query(" SELECT * FROM tbpinjam")
    fun tmpilsemuaa(): List<tbpinjam>
    @Query("SELECT * FROM tbpinjam WHERE id_bukuPinjam=:tbpinjam_idbuku")
    fun tmpilsemua(tbpinjam_idbuku: Int): List<tbpinjam>
}