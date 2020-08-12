package com.pustovit.dls2020.domain

data class Feed(
    val id:Int,
    val imgId:Int,
    val title:String,
    val items:List<String>
) {
}