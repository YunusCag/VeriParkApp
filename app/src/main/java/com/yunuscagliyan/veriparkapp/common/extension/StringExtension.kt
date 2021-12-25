package com.yunuscagliyan.veriparkapp.common.extension

import android.util.Base64
import timber.log.Timber
import java.lang.Exception
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


fun String.toEncrypted(
    aesKey: String,
    aesIV: String
): String {
    try {

        val keyByte = Base64.decode(aesKey, Base64.DEFAULT)
        val ivByte = Base64.decode(aesIV, Base64.DEFAULT)


        val chip = Cipher.getInstance("AES/CBC/PKCS7PADDING")
        val spec = IvParameterSpec(ivByte)

        val key = SecretKeySpec(keyByte, "AES")

        chip.init(Cipher.ENCRYPT_MODE, key, spec)

        val chipResult=chip.doFinal(this.toByteArray())

        val tokenEncrypted=Base64.encode(chipResult,Base64.DEFAULT)
        val encrypted=tokenEncrypted.toString(Charsets.UTF_8)


        Timber.d("Encryted:${encrypted}")
        return encrypted

    } catch (e: Exception) {
        Timber.e("Encrypted:${e}")
    }
    return ""
}

fun String.toDecrypted(
    aesKey: String,
    aesIV: String
): String {
    try {
        val keyByte = Base64.decode(aesKey, Base64.DEFAULT)
        val ivByte = Base64.decode(aesIV, Base64.DEFAULT)
        val current=Base64.decode(this, Base64.DEFAULT)

        val chip = Cipher.getInstance("AES/CBC/PKCS7PADDING")
        val spec = IvParameterSpec(ivByte)

        val key = SecretKeySpec(keyByte, "AES")

        chip.init(Cipher.DECRYPT_MODE, key, spec)

        val chipResult=chip.doFinal(current)
        Timber.d("Chip Result:${String(chipResult)}")


        return String(chipResult)

    } catch (e: Exception) {
        Timber.e("Decrypted:${e}")
    }
    return ""
}