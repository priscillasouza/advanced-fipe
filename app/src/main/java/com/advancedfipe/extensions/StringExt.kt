package com.advancedfipe.extensions

fun String.removeMoney(): String {
    return this.replace("R$", "").replace(".", "").replace(",", "")
}