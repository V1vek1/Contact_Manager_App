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

        //Yah bhi maine "view Binding" ki help se xml se design liya
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()  //App se action bar ko hide kiya

        //User ka data Users se lekar ko firebase ke User me save kiya
        database = FirebaseDatabase.getInstance().getReference("Users")

        //Agar koi 'Signup' button per click kare to yeh karo
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

        //yaha "TextView" signin per click karne se "tvSignIn" class ko Call kardo
        binding.tvSignIn.setOnClickListener {
            intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
    }

}
