package com.jmm.womensafety.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmm.womensafety.adapters.ContactAdapter
import com.jmm.womensafety.databinding.FragmentEmergencyContactsBinding
import com.jmm.womensafety.models.ContactModel
import com.jmm.womensafety.util.BaseFragment


class EmergencyContacts :
    BaseFragment<FragmentEmergencyContactsBinding>(FragmentEmergencyContactsBinding::inflate),
    ContactAdapter.ContactAdapterInterface {


    private lateinit var contactAdapter: ContactAdapter
    private var selectedMobileNo = ""
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$selectedMobileNo")
                startActivity(callIntent)
            } else {

                showToast("Permission rejected !!!")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvContacts()
    }

    private fun setupRvContacts() {
        contactAdapter = ContactAdapter(this)
        binding.rvContacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

        populateEmergencyContacts()
    }

    private fun populateEmergencyContacts() {
        val emergencyContacts = mutableListOf<ContactModel>()

        emergencyContacts.add(ContactModel(0, "Police", "100"))
        emergencyContacts.add(ContactModel(1, "Women Helpline", "1091"))
        emergencyContacts.add(ContactModel(2, "Women Helpline(Domestic Abuse)", "181"))
        emergencyContacts.add(ContactModel(3, "Tourist Helpline", "1363"))
        emergencyContacts.add(ContactModel(4, "Ambulance", "102"))
        emergencyContacts.add(ContactModel(5, "Delhi Women Protection Cell", "011-24673366"))

        contactAdapter.setContacts(emergencyContacts)
    }

    override fun subscribeObservers() {

    }


    override fun onCallItemClick(item: ContactModel) {
        selectedMobileNo = item.MobileNo
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$selectedMobileNo")
            startActivity(callIntent)
        } else {
            requestPermissionLauncher.launch(
                Manifest.permission.CALL_PHONE

            )
        }

    }
}