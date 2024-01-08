package com.mobjoy.hash_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mobjoy.hash_test.databinding.ActivityHashGenerateBinding

class HashGenerateActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHashGenerateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHashGenerateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initViews()
    }

    private fun initViews() {
        initAdapter()
        callButton()
    }

    private fun initAdapter() {
        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, hashAlgorithms)
        viewBinding.autoCompleteTV.setAdapter(arrayAdapter)
    }

    private fun callButton() {
        viewBinding.generate.setOnClickListener {
            generateHashing()
        }
    }

    private fun generateHashing() {
        if (viewBinding.planeText.text.isNullOrBlank()) {
            showSnackBar("Field is empty !")
        } else {

            navigateToResultActivity()
            shareHashToResultActivity()
        }

    }

    private fun shareHashToResultActivity() {
        val algorithm = viewBinding.autoCompleteTV.text.toString()
        val plainText = viewBinding.planeText.text.toString()
        val generate = Algorithms().getHash(plainText, algorithm)
        val sharedPref = getSharedPreferences("hash", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("hash", generate)
        editor.apply()

    }

    private fun navigateToResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }


    private fun showSnackBar(msg: String) {
        val snackBar = Snackbar.make(
            viewBinding.root,
            msg,
            Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("Ok") {}
        snackBar.show()
    }

}