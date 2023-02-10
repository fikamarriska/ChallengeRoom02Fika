package com.example.challengeroom02fika

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom02fika.Room.tbbuku
import kotlinx.android.synthetic.main.activity_buku_adapter.view.*

class BukuAdapter (private val buku: ArrayList<tbbuku>, private val listener: OnAdapterlistener): RecyclerView.Adapter<BukuAdapter.BukuViewholder>(){

    class BukuViewholder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewholder {
        return BukuViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_buku_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewholder, position: Int) {
        val bukuu = buku [position]
        holder.view.T_judul.text = bukuu.judul
        holder.view.T_Kategori.text = bukuu.kategori
        holder.view.CVbuku.setOnClickListener{
            listener.onClik(bukuu)
        }
        holder.view.imageEdit.setOnClickListener{
            listener.onUpdate(bukuu)
        }
        holder.view.imageDelete.setOnClickListener{
            listener.onDelete(bukuu)
        }

    }

    override fun getItemCount() = buku.size


    fun setData(list: List<tbbuku>) {
        buku.clear()
        buku.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterlistener {
        fun onClik(tbbuku: tbbuku)
        fun onUpdate(tbbuku: tbbuku)
        fun onDelete(tbbuku: tbbuku)

    }


}
