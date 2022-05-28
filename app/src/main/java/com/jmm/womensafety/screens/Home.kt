package com.jmm.womensafety.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.jmm.womensafety.databinding.FragmentHomeBinding
import com.jmm.womensafety.util.BaseFragment
import java.util.*


class Home : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cardNearByPoliceStation.setOnClickListener {
                val map = "http://maps.google.co.in/maps?q=police station near me"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                startActivity(intent)
            }
            cardHarassment.setOnClickListener {  }
            cardSendLocation.setOnClickListener {  }
        }
    }

    override fun subscribeObservers() {

    }

}