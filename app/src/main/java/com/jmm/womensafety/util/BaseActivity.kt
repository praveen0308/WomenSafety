package com.jmm.womensafety.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding



abstract class BaseActivity<B : ViewBinding>(private val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {
    lateinit var binding: B
    private lateinit var progressBarHandler: ProgressBarHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        progressBarHandler = ProgressBarHandler(this)

    }


    abstract fun subscribeObservers()

    protected fun displayLoading(state: Boolean) {
        if (state) progressBarHandler.show() else progressBarHandler.hide()
    }


    protected fun displayRefreshing(loading: Boolean) {

    }

    protected fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    protected fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showAlertDialog(
        message: String?,
        dialogClickListener: DialogInterface.OnClickListener
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }




}