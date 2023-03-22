package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.to_do_list.databinding.ActivityAddToDoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddToDoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        firestore = Firebase.firestore


        GoBack()
        AddToDo()
    }

    private fun GoBack(){
        binding.BtnGoBack.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun AddToDo(){
        binding.BtnAdd.setOnClickListener {

            if(auth.currentUser!=null){
                val aTodoMap = hashMapOf<String, Any>()
                aTodoMap.put("todo",binding.editTextAddToDo.text.toString())

                firestore.collection("ToDos").add(aTodoMap).addOnSuccessListener {
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}