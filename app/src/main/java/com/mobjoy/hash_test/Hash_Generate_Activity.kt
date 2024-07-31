package com.mobjoy.hash_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.mobjoy.hash_test.databinding.ActivityHashGenerateBinding

class Hash_Generate_Activity : AppCompatActivity() {
    lateinit var viewBinding : ActivityHashGenerateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHashGenerateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(this  , R.layout.drop_down_item , hashAlgorithms)
        viewBinding.autoCompleteTV.setAdapter(arrayAdapter)
        viewBinding.generate.setOnClickListener {
            generateHashing()
        }
    }
    private fun generateHashing(){
        if(viewBinding.planeText.text.isNullOrBlank()){
            showSnackBar("Field is empty !")
        }

        val algorithm = viewBinding.autoCompleteTV.text.toString()
        val plainText = viewBinding.planeText.text.toString()
        val generate = Algorithms().getHash(plainText ,algorithm)
        viewBinding.Result.setText(generate)









    }






    private fun showSnackBar(msg : String){
        val snackBar = Snackbar.make(
            viewBinding.root,
            msg ,
            Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("Ok"){}
        snackBar.show()
    }
}