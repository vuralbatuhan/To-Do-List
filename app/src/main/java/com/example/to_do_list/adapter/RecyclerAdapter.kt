package com.example.to_do_list.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.databinding.RecyclerRowBinding
import com.example.to_do_list.model.ATodo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerAdapter(private val AtodoList : ArrayList<ATodo>) : RecyclerView.Adapter<RecyclerAdapter.ATodoHolder>() {

    private val bgColor: Array<String> = arrayOf("#999999","#ffd4aa")

    class ATodoHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATodoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ATodoHolder(binding)
    }

    override fun onBindViewHolder(holder: ATodoHolder, position: Int) {



        holder.binding.recyclerLinear.setBackgroundColor(Color.parseColor(bgColor[position % 2]))
        holder.binding.recyclerText.text = AtodoList.get(position).todo


    }

    override fun getItemCount(): Int {
        return AtodoList.count()
    }

}