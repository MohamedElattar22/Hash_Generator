package com.mobjoy.hash_test

import java.security.MessageDigest

class Algorithms {

    fun getHash(plainTxt : String , algorithms: String) : String{
        val bytes = MessageDigest.getInstance(algorithms).digest(plainTxt.toByteArray())
        return toHex(bytes)

    }
    private fun toHex(byteArray : ByteArray) : String{
        return byteArray.joinToString (""){
            "%02x".format(it)
        }
    }
}