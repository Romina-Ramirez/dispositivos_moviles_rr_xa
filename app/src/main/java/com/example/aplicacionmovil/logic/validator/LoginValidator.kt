package com.example.aplicacionmovil.logic.validator

import com.example.aplicacionmovil.data.entities.LoginUser

class LoginValidator {

    fun checkLogin(name:String, pass:String):Boolean {
        val admin = LoginUser()
        return (admin.name == name && admin.pass == pass)
    }
}