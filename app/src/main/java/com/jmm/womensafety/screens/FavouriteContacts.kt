package com.jmm.womensafety.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmm.womensafety.AddEditContact
import com.jmm.womensafety.R
import com.jmm.womensafety.adapters.Contact2Adapter
import com.jmm.womensafety.databinding.FragmentFavouriteContactsBinding
import com.jmm.womensafety.models.ContactModel
import com.jmm.womensafety.util.BaseFragment
import com.jmm.womensafety.viewmodel.FavouriteContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteContacts :
    BaseFragment<FragmentFavouriteContactsBinding>(FragmentFavouriteContactsBinding::inflate),
    Contact2Adapter.Contact2AdapterInterface {

    private val viewModel by viewModels<FavouriteContactsViewModel>()
    private lateinit var contactAdapter: Contact2Adapter
    private var selectedMobileNo = ""

    private var currentMode = 0

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

        binding.apply {
            btnEdit.setOnClickListener {
                viewModel.mode.postValue(1-currentMode)
            }
            btnAddContact.setOnClickListener {
                val intent = Intent(requireActivity(), AddEditContact::class.java)
                intent.putExtra("action", "add")
                startActivity(intent)

            }
        }
    }

    private fun setupRvContacts() {
        contactAdapter = Contact2Adapter(this)
        binding.rvContacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

//        populateEmergencyContacts()
    }

    override fun subscribeObservers() {
        viewModel.mode.observe(viewLifecycleOwner){
            currentMode = it
            if (it==0){
                binding.btnEdit.setImageResource(R.drawable.ic_baseline_edit_24)
            }else{
                binding.btnEdit.setImageResource(R.drawable.ic_baseline_close_24)
            }
            if (contactAdapter!=null){
                contactAdapter.setMode(it)
            }
        }
        viewModel.contacts.observe(viewLifecycleOwner) {
            binding.groupNoRecords.isVisible = it.isEmpty()
            binding.btnEdit.isVisible = it.isNotEmpty()
            contactAdapter.setContacts(it)

        }
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

    override fun onDeleteClick(item: ContactModel) {
        viewModel.deleteContact(item)
        showToast("Deleted successfully !!!")
    }

    override fun onEditClick(item: ContactModel) {
        val intent = Intent(requireActivity(), AddEditContact::class.java)
        intent.putExtra("action", "edit")
        intent.putExtra("contact",item)
        startActivity(intent)
    }

}