package com.example.mahasiswa_app_nyanyangsuryana.view.presenter

import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem

interface MainView {

    fun onSuccess(msg : String, data : List<DataItem>?)
    fun onError(msg : String)
    fun onDelete(msg : String)

}