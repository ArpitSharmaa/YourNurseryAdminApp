package com.example.YournurseryAdmin

import com.google.gson.GsonBuilder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

val base_url = "http://192.168.29.199" +
        ":8080"

interface ApiServices {
    @Multipart
    @POST("/enterproducts")
    suspend fun enterproducts(@Part("data") plantdata: RequestBody, @Part image: List<MultipartBody.Part?>):Response<response<String>>


    @POST("/register")
    suspend fun endteruser(@Body user: user) :Response<response<String>>

    @POST("/login")
    suspend fun loginuser(@Body user: user):Response<response<String>>

    @GET("authenticate")
    suspend fun authenticate(@Header("Authorization") token:String) : Response<response<String>>

    @GET("/secret")
    suspend fun getid (@Header("Authorization") token:String):Response<response<String>>

    @GET("/orders/{ownerid}")
    suspend fun orders(@Path("ownerid") owner:String):Response<List<ordersresponse>>
    companion object{
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build()
        val service : ApiServices = retrofit.create(ApiServices::class.java)
    }

}