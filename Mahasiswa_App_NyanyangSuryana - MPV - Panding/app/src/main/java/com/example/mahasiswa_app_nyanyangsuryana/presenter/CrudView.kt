package com.example.mahasiswa_app_nyanyangsuryana.presenter

import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin

interface CrudView {

    fun onSuccessInput(response : ResponseActoin)
    fun onError(msg : String)
    fun onSuccessUpdate(response: ResponseActoin)
}