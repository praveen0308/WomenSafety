package com.jmm.womensafety.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmm.womensafety.databinding.ContactRowViewBinding
import com.jmm.womensafety.models.ContactModel

class ContactAdapter(private val contactAdapterInterface: ContactAdapterInterface) :RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private val mList = mutableListOf<ContactModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ContactRowViewBinding.inflate(LayoutInflater.from(parent.context),parent,false),contactAdapterInterface)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun setContacts(mList:List<ContactModel>){
        this.mList.clear()
        this.mList.addAll(mList)
        notifyDataSetChanged()
    }
    inner class ContactViewHolder(val binding:ContactRowViewBinding, private val mListener:ContactAdapterInterface):RecyclerView.ViewHolder(binding.root){
        init {

            binding.ivCall.setOnClickListener {
                mListener.onCallItemClick(mList[adapterPosition])
            }
        }

        fun bind(item:ContactModel){
            binding.apply {
                tvName.text = item.Name
                tvContactNo.text = item.MobileNo


            }
        }

    }

    interface ContactAdapterInterface{
        fun onCallItemClick(item: ContactModel)

    }


}