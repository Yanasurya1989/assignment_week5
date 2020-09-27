package com.example.mahasiswa_app_nyanyangsuryana.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mahasiswa_app_nyanyangsuryana.Adapter.DataAdapter
import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.ResponseGetData
import com.example.mahasiswa_app_nyanyangsuryana.R
import com.example.mahasiswa_app_nyanyangsuryana.view.presenter.MainPresenter
import com.example.mahasiswa_app_nyanyangsuryana.view.presenter.MainView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainView {

    private var presenter : MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Mamang's Mvp App "

        fab.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }

        presenter = MainPresenter(this)
        presenter?.showData()
    }

    override fun onSuccess(msg: String, data: List<DataItem>?) {
        val adapter = DataAdapter(data, object : DataAdapter.OnClickListener {
            override fun detail(item: DataItem?) {
                val intent = Intent(applicationContext, InputActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

            override fun hapus(item: DataItem?) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus Data")
                    setMessage("Anda yakin ingin menghapus data ini?")
                    setPositiveButton("Hapus"){dialogInterface, i ->
                        presenter?.deleteData(item?.idMahasiswa ?: "")
                        dialogInterface.dismiss()
                    }
                    setNegativeButton("Batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }
        })
        list.adapter = adapter
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDelete(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        presenter?.showData()
    }
}