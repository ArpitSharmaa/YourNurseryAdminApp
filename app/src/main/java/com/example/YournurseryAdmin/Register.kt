package com.example.YournurseryAdmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.apitest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Register : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<TextView>(R.id.emailtext)
        val pas = view.findViewById<EditText>(R.id.editTextTextPassword)
        val pas2 = view.findViewById<EditText>(R.id.editTextTextPassword2)
        val register = view.findViewById<Button>(R.id.button)
        register.setOnClickListener {
            if (email.text.isBlank()|| pas.text.isBlank()||pas2.text.isBlank()){
                Toast.makeText(context, "Please enter all the required fields", Toast.LENGTH_SHORT).show()
            }else if(isValidEmail(email.text.toString())){
                if (pas.text.toString() ==pas2.text.toString()){
                    registeruser(email.text.toString(),pas.text.toString())
                    findNavController().navigate(R.id.action_register2_to_loginFrag)
                }else{
                    Toast.makeText(context, "Please check the password", Toast.LENGTH_SHORT).show()
                }
            }else{
                email.setError("invalid")
                Toast.makeText(context, "e-mail is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registeruser(email: String, pass: String) {
            GlobalScope.launch {
                withContext(Dispatchers.IO){
                    services.endteruser(user(email,pass))
                }
            }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^([\\w\\d\\.-]+)@yournursery\\.([a-z]{2,})$")
        return emailRegex.matches(email)
    }

}