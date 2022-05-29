package com.jmm.womensafety.screens

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jmm.womensafety.databinding.FragmentHomeBinding
import com.jmm.womensafety.util.BaseFragment
import java.util.*


class Home : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var dialog: ProgressDialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(requireContext())
        dialog.setTitle("Sending Location")
        dialog.setMessage("Please wait...")
        dialog.isIndeterminate = true

        binding.apply {
            cardNearByPoliceStation.setOnClickListener {
                val map = "http://maps.google.co.in/maps?q=police station near me"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                startActivity(intent)
            }
            cardHarassment.setOnClickListener {  }
            cardSendLocation.setOnClickListener {
                dialog.show()
                val t = Timer()
                t.schedule(object : TimerTask() {
                    override fun run() {
                        dialog.dismiss() // when the task active then close the dialog
                        activity!!.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Sent successfully !!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        t.cancel() // also just top the timer thread, otherwise, you may receive a crash report

                    }
                }, 3000)

            }
        }
    }

    override fun subscribeObservers() {

    }

}