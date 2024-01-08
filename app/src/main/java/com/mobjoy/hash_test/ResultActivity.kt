package com.mobjoy.hash_test

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobjoy.hash_test.databinding.ActivityResultBinding
import org.aviran.cookiebar2.CookieBar
import java.net.CookieHandler

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

          saveHash()


    }

    private fun saveHash() {
        val sharedPreferences = getSharedPreferences("hash", Context.MODE_PRIVATE)
        val hash = sharedPreferences.getString("hash", "")
        binding.Result.text = hash

        binding.btnCopy.setOnClickListener {
            copyToClipboard(hash.toString())
            showCookieBar()
        }
    }





    private fun copyToClipboard(textToCopy: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label", textToCopy)
        clipboardManager.setPrimaryClip(clipData)

    }
    private fun showCookieBar(){
        CookieBar.build(this)
            .setTitle("Copied")
            .setBackgroundColor(R.color.light_blue)
            .setTitleColor(R.color.white)
            .setDuration(2000)
            .setEnableAutoDismiss(true)
            .setCookiePosition(CookieBar.TOP)
            .show()
    }

}