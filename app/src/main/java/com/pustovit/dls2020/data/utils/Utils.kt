package com.pustovit.dls2020.data.utils


fun Array<String>.toItemsString(): String {
    val sb = StringBuilder()
    this.forEach { sb.append(it).append("\n") }
    return sb.trim().toString()
}