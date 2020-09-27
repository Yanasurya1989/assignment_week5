package com.example.mahasiswa_app_nyanyangsuryana.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem
import com.example.mahasiswa_app_nyanyangsuryana.R
import com.example.mahasiswa_app_nyanyangsuryana.presenter.CrudPresenter
import com.example.mahasiswa_app_nyanyangsuryana.presenter.CrudView
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity(), CrudView {

    private var presenter : CrudPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        supportActionBar?.title = "Input"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = CrudPresenter(this)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if(getData != null){
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNohp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btn1.text = "Update"
        }

        when (btn1.text) {
            "Update" -> {

                btn1.setOnClickListener {
                    getData?.idMahasiswa?.let { it1 -> presenter?.updateData(it1, etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString())}
                }

            }else -> {
            btn1.setOnClickListener {
                presenter?.inputData(etNama.text.toString(), etNohp.text.toString(), etAlamat.text.toString())
            }
        }
        }

        btn2.setOnClickListener {
            finish()
        }
    }

    override fun onSuccessInput(response: ResponseActoin) {
        toast("Data berhasil disimpan")
        finish()
    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onSuccessUpdate(response: ResponseActoin) {
        toast("Data berhasil diupdate")
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun toast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

//    private fun inputData(mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?){
//        val input = NetworkModule.service().insertData(mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
//        input.enqueue(object : Callback<ResponseActoin>{
//
//            override fun onResponse(
//                call: Call<ResponseActoin>,
//                response: Response<ResponseActoin>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseActoin>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
//
//    private fun updateData(id: String?, mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?){
//        val update = NetworkModule.service().updatetData( id ?: "", mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
//        update.enqueue(object : Callback<ResponseActoin>{
//
//            override fun onResponse(
//                call: Call<ResponseActoin>,
//                response: Response<ResponseActoin>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseActoin>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
}