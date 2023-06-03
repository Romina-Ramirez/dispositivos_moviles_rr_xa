package com.example.dispositivosmoviles.logic.validator

import com.example.dispositivosmoviles.data.entities.LoginUser

class LoginValidator {

    fun checkLogin(name:String, pass:String):Boolean {
        val admin = LoginUser()
        return (admin.name == name && admin.pass == pass)
    }
}