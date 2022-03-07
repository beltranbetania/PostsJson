package com.beltranbetania.postsjson.domain.model

import com.beltranbetania.postsjson.data.model.UserModel


data class User (val id:Int, val name:String, val email:String, val phone:String, val website:String)

fun UserModel.toDomain() = User(id, name, email,phone, website)
