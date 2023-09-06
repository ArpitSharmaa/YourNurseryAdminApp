package com.example.YournurseryAdmin

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.apitest.R
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

lateinit var  pref: SharedPreferences
class LoginFrag : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        arguments?.let {

        }
        if (::pref.isInitialized) {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    val token = pref.getString("jwt", "")
                    try {
                        val z = services.authenticate("Bearer $token")
                        if (z.isSuccessful) {
                            if (z.code() == HttpStatusCode.OK.value) {
                                withContext(Dispatchers.Main){
                                    findNavController().navigate(R.id.action_loginFrag_to_addproducts)
                                }

                            }
                        }
                    }catch (ex:java.lang.Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val login = view.findViewById<Button>(R.id.login)
        val email= view.findViewById<TextView>(R.id.email)
        val pass  = view.findViewById<TextView>(R.id.pass)
        val register = view.findViewById<TextView>(R.id.register)
        val progress = view.findViewById<ProgressBar>(R.id.progressBar)
        progress.visibility = View.INVISIBLE
        register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFrag_to_register2)
        }
        login.setOnClickListener {
            if (email.text.isBlank() || pass.text.isBlank()) {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT)
                    .show()
            } else if(isValidEmail(email.text.toString())){
                login(email.text.toString(), pass.text.toString(), progress)
                progress.visibility = View.VISIBLE
            }else{
                email.setError("invalid")
            }
        }

    }

    private fun login(email: String, pass: String,progress: ProgressBar) {
      GlobalScope.launch {
            withContext(Dispatchers.IO){

                    val response = services.loginuser(user(email,pass))
                    if (response.isSuccessful) {
                        if (response.code() == 200){
                            withContext(Dispatchers.Main){
                                progress.visibility = View.INVISIBLE
                                pref.edit().putString("jwt",response.body()!!.string ).apply()
                                findNavController().navigate(R.id.action_loginFrag_to_addproducts)
                            }
                    }
//                    if (ex.code() == HttpStatusCode.Conflict.value) {
//                        withContext(Dispatchers.Main) {
//                            progress.visibility = View.INVISIBLE
//                            Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }else if (ex.code() == 200){
//                        withContext(Dispatchers.Main){
//                            progress.visibility = View.INVISIBLE
//                            findNavController().navigate(R.id.action_loginFrag_to_addproducts)
//                        }
//
//                    }else if (ex.code() == HttpStatusCode.BadRequest.value){
//                        withContext(Dispatchers.Main){
//                            progress.visibility = View.INVISIBLE
//                            Toast.makeText(context, "User Dont Exist", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }

                }else if (response.code()== HttpStatusCode.Conflict.value){
                    withContext(Dispatchers.Main){
                        progress.visibility = View.INVISIBLE
                        Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show()
                    }
                    }else if (response.code() == HttpStatusCode.BadRequest.value){
                        withContext(Dispatchers.Main){
                            progress.visibility = View.INVISIBLE
                            Toast.makeText(context, "Unauthorized user please register", Toast.LENGTH_SHORT).show()

                        }

                    }
            }
        }


    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^([\\w\\d\\.-]+)@yournursery\\.([a-z]{2,})$")
        return emailRegex.matches(email)
    }
}