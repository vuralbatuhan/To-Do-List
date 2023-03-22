package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.adapter.RecyclerAdapter
import com.example.to_do_list.databinding.ActivityHomeBinding
import com.example.to_do_list.model.ATodo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var aTodoArrayList : ArrayList<ATodo>
    private lateinit var recyclerAdapter : RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = Firebase.firestore

        aTodoArrayList = ArrayList<ATodo>()

        AddToDo()
        getData()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(aTodoArrayList)
        binding.recyclerView.adapter = recyclerAdapter

    }

    private fun AddToDo(){
        binding.ImgAddToDo.setOnClickListener {
            val intent = Intent(this,AddToDoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData(){
        db.collection("ToDos").orderBy("todo",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if(value != null){
                    if(!value.isEmpty){

                        val documents = value.documents

                        aTodoArrayList.clear()

                        for(document in documents){
                            val todo = document.get("todo") as String

                            val aTodo = ATodo(todo)
                            aTodoArrayList.add(aTodo)
                        }

                        recyclerAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }

}