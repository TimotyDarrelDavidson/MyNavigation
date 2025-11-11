package uts.c14230235.mynavigation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.lifecycle.GeneratedAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BahanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BahanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var dataNama = mutableListOf<String>()
    private var dataKategori = mutableListOf<String>()
    private var dataURL = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _lv= view.findViewById<ListView>(R.id.lv)

        val nama = resources.getStringArray(R.array.namaProduk)
        val kategori = resources.getStringArray(R.array.kategoriProduk)
        val gambar = resources.getStringArray(R.array.gambarProduk)

        val data = mutableListOf<Map<String, String>>()

        for (i in nama.indices) {
            val item = HashMap<String, String>()
            item["nama"] = nama[i]
            item["kategori"] = kategori[i]
            item["gambar"] = gambar[i]
            data.add(item)
        }

        val adapter = SimpleAdapter(
            requireContext(),
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("nama", "kategori"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        _lv.adapter = adapter


        val _addBahan = view.findViewById<ImageView>(R.id.addBahan)
        _addBahan.setOnClickListener {
            showAddBahanDialog(dataNama, dataKategori, data, adapter)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bahan, container, false)
    }

    private fun showAddBahanDialog(
        dataNama: MutableList<String>,
        dataKategori: MutableList<String>,
        data: MutableList<Map<String, String>>,
        adapter: SimpleAdapter,
    ){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add Bahan")

        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40 , 50,10)

        val tvNama = TextView(requireContext())
        tvNama.text = "nama bahan"
        tvNama.setTextSize(16f)

        val etNama = EditText(requireContext())
        etNama.hint = "Tuliskan nama bahan"

        val tvKategori = TextView(requireContext())
        tvKategori.text = "kategori bahan"
        tvKategori.setTextSize(16f)

        val etKategori = EditText(requireContext())
        etKategori.hint = "Tuliskan kategori bahan"

        val tvURL = TextView(requireContext())
        tvURL.text = "URL image"
        tvURL.setTextSize(16f)

        val etURL = EditText(requireContext())
        etURL.hint = "Tuliskan URL image"

        layout.addView(tvNama)
        layout.addView(etNama)
        layout.addView(tvKategori)
        layout.addView(etKategori)
        layout.addView(tvURL)
        layout.addView(etURL)

        builder.setView(layout)

        builder.setPositiveButton("Simpan") { dialog, _ ->
            val nama = etNama.text.toString().trim()
            val kategori = etKategori.text.toString().trim()
            val URL = etURL.text.toString().trim()

            if (nama.isNotEmpty() && kategori.isNotEmpty()) {
                // Tambahkan ke list utama
                dataNama.add(nama)
                dataKategori.add(kategori)
                dataURL.add(URL)

                // Buat map baru untuk SimpleAdapter
                val map = mapOf("nama" to nama, "kategori" to kategori, "URL" to URL)

                // Karena SimpleAdapter menggunakan List<Map<String, String>>,
                // kita perlu akses list data-nya dari adapter
                val dataList = data
                dataList.add(map)

                // Perbarui tampilan
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Batal", null)
        builder.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BahanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BahanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}