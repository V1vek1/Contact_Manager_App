package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contactmanager.databinding.ActivityCallBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//Yeh Contact save karne wala Activity h
class Call : AppCompatActivity() {

    //Here i created 2 private and 1 public "Lateinit: variable
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityCallBinding
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Yha ye xml file se design le rha h
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //yah maine btaya ki user se data lo or usko Contact me save karo Firebase database per
        database = FirebaseDatabase.getInstance().getReference("Contact")

        //Yha  maine "View Binding" ki help se, aasani se btaya ki Button per Click karne per kya task karna h
        binding.btnAdd.setOnClickListener {
            val name=binding.etName1.text.toString()
            val mail=binding.etmail2.text.toString()
            val phone=binding.etphone.text.toString()


            //Yha maine "User" ka data liya or usko Firebase me "contact" name ka Child Class bnakar usme save kiya h
            //Agar yeh sab sahi se work karta h to niche wale "function" yani ko call kare
            val contact= Contact(name,mail,phone)
            database.child(phone).setValue(contact).addOnSuccessListener {
                binding.etName1.text?.clear()
                binding.etmail2.text?.clear()
                binding.etphone.text?.clear()

                //Yha maine Private Function jiska name h showDialog ko Call kiya h
                showDialog()
            }

        }

    }

    //Yha miane ek Private Function bnaya jiska name h "showDialog"
    //Or hum jante h ki Dialog ek prakar ka Alert/popup show karte h to hamne wahi bnaya h
    private fun showDialog() {

        dialog= Dialog(this)  //Yha maine Dialog ko uska Path assign kiya ki kaha se call karna h
        dialog.setContentView(R.layout.custom_dialog)  //Dialog ko uska Layout set karke diya
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))  //yha maine Dialog ke window ka color or size diya

        //Yha maine btaya ki jab Dialog ke "ok" wale Button per User click kare to kta karna h
        val buttonok=dialog.findViewById<Button>(R.id.btnOk)

        buttonok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
