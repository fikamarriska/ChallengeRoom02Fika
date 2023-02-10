package com.example.challengeroom02fika.Room

import androidx.room.*

@Dao
interface tbbukuDao {
    @Insert
    fun addtbbuku (tbbuku: tbbuku)
    @Update
    fun updatetbbuku (tbbuku: tbbuku)
    @Delete
    fun deletetbbuku (tbbuku: tbbuku)
    @Query(" SELECT * FROM tbbuku")
    fun tampilsemuaa(): List<tbbuku>
    @Query("SELECT * FROM tbbuku WHERE id_buku=:tbbuku_idbuku")
    fun tampilsemua(tbbuku_idbuku: Int): List<tbbuku>

}