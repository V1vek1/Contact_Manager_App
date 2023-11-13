package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contactmanager.databinding.ActivityCallBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Call : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityCallBinding
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Contact")

        binding.btnAdd.setOnClickListener {
            val name=binding.etName1.text.toString()
            val mail=binding.etmail2.text.toString()
            val phone=binding.etphone.text.toString()

            val contact= Contact(name,mail,phone)
            database.child(phone).setValue(contact).addOnSuccessListener {
                binding.etName1.text?.clear()
                binding.etmail2.text?.clear()
                binding.etphone.text?.clear()

                showDialog()
            }

        }

    }

    private fun showDialog() {
        dialog= Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))

        val buttonok=dialog.findViewById<Button>(R.id.btnOk)

        buttonok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
