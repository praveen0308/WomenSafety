package com.jmm.womensafety

import android.os.Bundle
import androidx.activity.viewModels
import com.jmm.womensafety.databinding.ActivityAddEditContactBinding
import com.jmm.womensafety.models.ContactModel
import com.jmm.womensafety.util.BaseActivity
import com.jmm.womensafety.viewmodel.AddEditContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditContact : BaseActivity<ActivityAddEditContactBinding>(ActivityAddEditContactBinding::inflate) {
    private var action = "add"

    private lateinit var contactModel: ContactModel

    private val viewModel by viewModels<AddEditContactViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        action = intent.getStringExtra("action").toString()


        binding.apply {

            toolbarAddEditContact.setNavigationOnClickListener {
                showToast("Cancelled !!!")
            }
            toolbarAddEditContact.title = if (action=="add") "Add Contact" else "Update Contact"

            if (action=="edit"){
                contactModel = intent.getParcelableExtra("contact")!!

                etName.setText(contactModel.Name)
                etMobileNo.setText(contactModel.MobileNo)
            }

            btnSubmit.setOnClickListener {
                if (action=="add"){
                    viewModel.addContact(ContactModel(Name = etName.text.toString(), MobileNo = etMobileNo.text.toString()))
                    showToast("Added successfully !!!")
                    finish()
                }else{
                    contactModel.Name = etName.text.toString()
                    contactModel.MobileNo = etMobileNo.text.toString()
                    viewModel.updateContact(contactModel)
                    showToast("Updated successfully !!!")
                    finish()
                }
            }
        }
    }

    override fun subscribeObservers() {


    }
}