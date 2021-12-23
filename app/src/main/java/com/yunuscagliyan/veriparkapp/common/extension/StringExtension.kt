package com.yunuscagliyan.veriparkapp.common.extension

import android.util.Base64
import timber.log.Timber
import java.lang.Exception
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


fun String.toEncrypted(
    aesKey: String,
    aesIV: String
): String {
    try {
        Timber.d("AesKey:${aesKey}")
        Timber.d("AesIV:${aesIV}")

        val keyByte = Base64.decode(aesKey, Base64.DEFAULT)
        val ivByte = Base64.decode(aesIV, Base64.DEFAULT)

        Timber.e("Ky:${String(keyByte)} -----aesIV:${String(ivByte)}")

        val chip = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        Timber.e("Block Size:${chip.blockSize}")
        val spec = IvParameterSpec(ivByte)

        val key = SecretKeySpec(keyByte, "AES")

        chip.init(Cipher.ENCRYPT_MODE, key, spec)

        val chipResult=chip.doFinal(this.toByteArray())
        Timber.d("Chip Result:${String(chipResult)}")

        val tokenEncrypted=Base64.encode(chipResult,Base64.DEFAULT)
        val encrypted=tokenEncrypted.toString(Charsets.UTF_8)


        Timber.d("Encryted:${encrypted}")
        return encrypted

    } catch (e: Exception) {
        Timber.e("Encrypted:${e}")
    }
    return ""
}