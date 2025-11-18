package uts.c14230235.mynavigation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapterRecView (private val data: List<dcBahan>) : RecyclerView.Adapter<adapterRecView.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val bahan = data[position]
        holder._namaBahan.text = bahan.nama
        holder._kategoriBahan.text = bahan.kategori
        Log.d("TEST", bahan.URL)
        Picasso.get()
            .load(bahan.URL)
            .resize(100,100)
            .into(holder._URIBahan)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val _namaBahan = view.findViewById< TextView>(R.id.nama)
        val _kategoriBahan = view.findViewById< TextView>(R.id.kategori)
        val _URIBahan = view.findViewById<ImageView>(R.id.URI)

    }
}