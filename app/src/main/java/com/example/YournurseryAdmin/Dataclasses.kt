package com.example.YournurseryAdmin

data class response<T>(val string: T)



data class plantdata(
    val productname:String?,
    val productdescription:String?,
    val productprice:Long?,
    val about: aboutsec,
    val ownerid : String


)
data class aboutsec(
    val details: String,
    val productname: String?
)
data class user(
    val email:String,
    val password:String
)
data class ordersresponse(
    val productname: String,
    var numberofproduct : Long,
    val productdescription : String,
    val image:String,
    val fullname :String,
    val mobile :String,
    val address : String,
    val state : String,
    val city : String,
    val postal : String

)