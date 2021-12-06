package com.example.registrationauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var Password : EditText
    private lateinit var email : EditText
    private lateinit var Password_repeat : EditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        Buttonsubmit()
    }

    private fun init(){

        Password = findViewById(R.id.Password)
        email = findViewById(R.id.email)
        Password_repeat = findViewById(R.id.Password_repeat)
        button = findViewById(R.id.button)

    }
    private fun Buttonsubmit(){

        button.setOnClickListener {

            val email = email.text.toString()
            val Password = Password.text.toString()
            val Password_repeat = Password_repeat.text.toString()

            if(email.isEmpty() || Password.isEmpty() || Password_repeat.isEmpty()) {
                Toast.makeText(this, "email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(Password.length < 9 || !(email.contains("@")) || Password != Password_repeat){
                Toast.makeText(this,"type information correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(!(Password.matches(".*[123456789].*".toRegex()))){
                Toast.makeText(this,"password is too weak",Toast.LENGTH_SHORT).show()
            }else if(!(Password.matches(".*[!#$%^&*].*".toRegex()))){
                Toast.makeText(this,"password is too weak",Toast.LENGTH_SHORT).show()
            }else
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, Password)
                    .addOnCompleteListener{ jemali ->
                        if(jemali.isSuccessful) {
                            Toast.makeText(this,"you have succefuslly submited informaiton",Toast.LENGTH_SHORT).show()
                    }else
                            Toast.makeText(this,"try again",Toast.LENGTH_SHORT).show()
            }
    }
}
}
