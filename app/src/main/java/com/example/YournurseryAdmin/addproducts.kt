package com.example.YournurseryAdmin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apitest.R
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.ByteArrayOutputStream
import kotlin.properties.Delegates


class addproducts : Fragment() {

    val token = pref.getString("jwt",null)
    lateinit var user: String
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
        return inflater.inflate(R.layout.fragment_addproducts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO){

            val res =services.getid("Bearer $token")
            withContext(Dispatchers.Main){
                user = res.body()!!.string
                Toast.makeText(context, "Welcome User:${res.body()?.string}", Toast.LENGTH_SHORT).show()
            }
        }
        val butt = view.findViewById<Button>(R.id.button2)
        butt.setOnClickListener {
            val vundle = Bundle()
            vundle.putString("owner",user)
            findNavController().navigate(R.id.action_addproducts_to_orders,vundle)
        }
        val nametext = view.findViewById<EditText>(R.id.plantname)
        val adapter = ArrayAdapter(requireContext(),R.layout.listview, string)
//        adapter.setDropDownViewResource(R.layout.dropdownlayout)
        var desctext : String = ""
        val autocompplete = view.findViewById<Spinner>(R.id.dropdown)
        autocompplete.adapter = adapter
        val plantpricetext = view.findViewById<EditText>(R.id.Plantprice)
        val about= view.findViewById<EditText>(R.id.about)
        var byteArray1: MultipartBody.Part? = null
        var byteArray2: MultipartBody.Part? = null
        var byteArray3: MultipartBody.Part? = null
        var byteArray4: MultipartBody.Part? = null
        var byteArray5: MultipartBody.Part? = null
        val button = view.findViewById<ImageButton>(R.id.Postbutton)
        val img1 = view.findViewById<ImageButton>(R.id.imageButton1)
        val img2 = view.findViewById<ImageButton>(R.id.imageButton2)
        val img3 = view.findViewById<ImageButton>(R.id.imageButton3)
        val img4 = view.findViewById<ImageButton>(R.id.imageButton4)
        val img5 = view.findViewById<ImageButton>(R.id.imageButton5)
        autocompplete.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    desctext = p0.getItemAtPosition(p2).toString()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val contract1 = registerForActivityResult(ActivityResultContracts.GetContent()){
            if (it != null) {
                val inputStream = context?.contentResolver?.openInputStream(it)
                val bytes = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead by Delegates.notNull<Int>()
                while ((inputStream?.read(buffer))?.also { bytesRead = it } != -1) {
                    bytes.write(buffer, 0, bytesRead)
                }
                val imagbyte = bytes.toByteArray()
                val request = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imagbyte)
                byteArray1 =
                    MultipartBody.Part.createFormData("images", "image.jpg", request)
//                val imagePart = MultipartBody.Part.createFormData("image", file.name, imageBody)
                Toast.makeText(context, "Image Added", Toast.LENGTH_SHORT).show()

            }
        }
        img1.setOnClickListener {
            contract1.launch("image/*")

        }
//
        val contract2 = registerForActivityResult(ActivityResultContracts.GetContent()){
            if (it != null) {
                val inputStream = context?.contentResolver?.openInputStream(it)
                val bytes = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead by Delegates.notNull<Int>()
                while ((inputStream?.read(buffer))?.also { bytesRead = it } != -1) {
                    bytes.write(buffer, 0, bytesRead)
                }
                val imagbyte = bytes.toByteArray()
                val request = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imagbyte)
                byteArray2 =
                    MultipartBody.Part.createFormData("images", "image.jpg", request)
                Toast.makeText(context, "Image Added", Toast.LENGTH_SHORT).show()
            }
        }
        img2.setOnClickListener {
            contract2.launch("image/*")

        }

        val contract3 = registerForActivityResult(ActivityResultContracts.GetContent()){
            if (it != null) {
                val inputStream = context?.contentResolver?.openInputStream(it)
                val bytes = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead by Delegates.notNull<Int>()
                while ((inputStream?.read(buffer))?.also { bytesRead = it } != -1) {
                    bytes.write(buffer, 0, bytesRead)
                }
                val imagbyte = bytes.toByteArray()
                val request = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imagbyte)
                byteArray3 =
                    MultipartBody.Part.createFormData("images", "image.jpg", request)
                Toast.makeText(context, "Image Added", Toast.LENGTH_SHORT).show()
            }
        }
        img3.setOnClickListener {
            contract3.launch("image/*")

        }

        val contract4 = registerForActivityResult(ActivityResultContracts.GetContent()){
            if (it != null) {
                val inputStream = context?.contentResolver?.openInputStream(it)
                val bytes = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead by Delegates.notNull<Int>()
                while ((inputStream?.read(buffer))?.also { bytesRead = it } != -1) {
                    bytes.write(buffer, 0, bytesRead)
                }
                val imagbyte = bytes.toByteArray()
                val request = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imagbyte)
                byteArray4 =
                    MultipartBody.Part.createFormData("images", "image.jpg", request)
                Toast.makeText(context, "Image Added", Toast.LENGTH_SHORT).show()

            }
        }
        img4.setOnClickListener {
            contract4.launch("image/*")

        }

        val contract5 = registerForActivityResult(ActivityResultContracts.GetContent()){
            if (it != null) {
                val inputStream = context?.contentResolver?.openInputStream(it)
                val bytes = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead by Delegates.notNull<Int>()
                while ((inputStream?.read(buffer))?.also { bytesRead = it } != -1) {
                    bytes.write(buffer, 0, bytesRead)
                }
                val imagbyte = bytes.toByteArray()
                val request = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imagbyte)
                byteArray5 =
                    MultipartBody.Part.createFormData("images", "image.jpg", request)
                Toast.makeText(context, "Image Added", Toast.LENGTH_SHORT).show()

            }
        }
        img5.setOnClickListener {
            contract5.launch("image/*")

        }
        button.setOnClickListener {
            if (nametext.text.isBlank()|| desctext.isBlank() || plantpricetext.text.isBlank()||about.text.isBlank()){
                Toast.makeText(context, "PLEASE LOOK AND INSERT ALL THE VALUES", Toast.LENGTH_SHORT).show()
            }else{
                Log.e("hi", "onViewCreated: $user", )
                val data = Gson().toJson(plantdata(nametext.text.toString(), desctext,plantpricetext.text.toString().toLong(),aboutsec(about.text.toString(),
                    nametext.text.toString()),user))
                    val data2= data.toRequestBody("application/json".toMediaTypeOrNull())
                Log.e("hi", "onViewCreated: $data", )
                postdata(data2, listOf( byteArray1,byteArray2,byteArray3,byteArray4,byteArray5))


            }
        }




    }

    fun postdata(plantdata: RequestBody, multipartBody: List<MultipartBody.Part?>){
        var response:response<String>? = null
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                Log.e("hi", "postdata: $plantdata", )
                response = call(plantdata,multipartBody).body()
                Toast.makeText(context, response?.string ?: "No response", Toast.LENGTH_SHORT).show()


            }
        }


    }
    suspend fun call(plantdata: RequestBody, part: List<MultipartBody.Part?>): Response<response<String>> {
        val z =  GlobalScope.async(Dispatchers.IO) {
            services.enterproducts(plantdata,part)

        }
        return z.await()
    }
    }
