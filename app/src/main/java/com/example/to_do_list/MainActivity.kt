package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.to_do_list.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        GoSignUp()
        SignIn()

    }
   private fun GoSignUp(){
       binding.BtnSGnInToSGnUp.setOnClickListener {
           val intent = Intent(this,SignUpActivity::class.java)
           startActivity(intent)
       }
   }

    private fun SignIn(){
        binding.BtnSGnIn.setOnClickListener {
            val email = binding.EtSGnInEmail.text.toString()
            val password = binding.EtSGnInPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this,"CHECK YOUR INFORMATION",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"FILL THE BLANKS",Toast.LENGTH_SHORT).show()
            }
        }
    }


}