package com.example.YournurseryAdmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apitest.R

val services = ApiServices.service
val string :List<String> = listOf("Summer Plant","Winter Plant","Desert Plant","Indoor Plants")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

}
}