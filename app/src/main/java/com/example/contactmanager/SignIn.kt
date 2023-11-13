package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignInBinding
import com.example.contactmanager.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivitySignInBinding

    companion object{
        const val KEY1 ="com.example.day16database.Signin.name"
        const val KEY2 ="com.example.day16database.Signin.mail"
        const val KEY3 ="com.example.day16database.Signin.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.btnSignIn.setOnClickListener {

            val uniqueid = binding.etuniqueid.text.toString()
            if (uniqueid.isNotEmpty()) {
                readData(uniqueid)
            } else {
                Toast.makeText(this, "Please enter userid", Toast.LENGTH_SHORT).show()

            }

        }
    }

        fun readData(uniqueid:String){
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")

            databaseReference.child(uniqueid).get().addOnSuccessListener {


                //if user exist or not
                if(it.exists()){
                    //welcome user in your app, with intent and also pass
                    val name=it.child("name").value
                    val mail=it.child("mail").value
                    val userid=it.child("uniqueId").value

                    val intentWelcome = Intent(this,Call::class.java)
                    intentWelcome.putExtra(KEY1,name.toString())
                    intentWelcome.putExtra(KEY2,mail.toString())
                    intentWelcome.putExtra(KEY3,userid.toString())
                    startActivity(intentWelcome)
                }else{
                    Toast.makeText(this, "User does not exist",Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener{
                Toast.makeText(this, "Failed, Error",Toast.LENGTH_SHORT).show()
            }
        }

    }