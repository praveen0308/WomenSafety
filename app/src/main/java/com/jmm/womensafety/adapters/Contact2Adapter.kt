package com.jmm.womensafety.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.jmm.womensafety.databinding.ContactRowView2Binding
import com.jmm.womensafety.models.ContactModel

class Contact2Adapter(private val contactAdapterInterface: Contact2AdapterInterface) :RecyclerView.Adapter<Contact2Adapter.Contact2ViewHolder>(){

    private val mList = mutableListOf<ContactModel>()

    private var mode = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Contact2ViewHolder {
        return Contact2ViewHolder(ContactRowView2Binding.inflate(LayoutInflater.from(parent.context),parent,false),contactAdapterInterface)
    }

    override fun onBindViewHolder(holder: Contact2ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setMode(mode:Int){
        this.mode = mode
        notifyDataSetChanged()
    }
    fun setContacts(mList:List<ContactModel>){
        this.mList.clear()
        this.mList.addAll(mList)
        notifyDataSetChanged()
    }
    inner class Contact2ViewHolder(val binding: ContactRowView2Binding, private val mListener:Contact2AdapterInterface):RecyclerView.ViewHolder(binding.root){
        init {

            binding.ivCall.setOnClickListener {
                mListener.onCallItemClick(mList[adapterPosition])
            }

            binding.ivDelete.setOnClickListener {
                mListener.onDeleteClick(mList[adapterPosition])
            }
            binding.ivEdit.setOnClickListener {
                mListener.onEditClick(mList[adapterPosition])
            }
        }

        fun bind(item:ContactModel){
            binding.apply {
                tvName.text = item.Name
                ivCall.isVisible = mode==0
                ivDelete.isVisible = mode!=0
                ivEdit.isVisible = mode!=0


            }
        }

    }

    interface Contact2AdapterInterface{
        fun onCallItemClick(item: ContactModel)
        fun onDeleteClick(item: ContactModel)
        fun onEditClick(item: ContactModel)
    }


}