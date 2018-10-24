package com.example.aleksandr.tmbook.model

data class ChatChannel(val userIds: MutableList<String>){
constructor(): this(mutableListOf())
}
