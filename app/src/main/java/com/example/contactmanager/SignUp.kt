package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnSignUp.setOnClickListener {

            val name=binding.etName.text.toString()
            val mail=binding.etmail.text.toString()
            val uniqueId=binding.etunique.text.toString()


            val users= Users(name,mail,uniqueId)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(users).addOnSuccessListener {
                binding.etName.text?.clear()
                binding.etmail.text?.clear()
                binding.etunique.text?.clear()

                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvSignIn.setOnClickListener {
            intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
    }

}
