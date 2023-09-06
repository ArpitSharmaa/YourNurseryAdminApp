package com.example.YournurseryAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apitest.R

class ordersadaptor(val list: List<ordersresponse>,val context:Context,val emailll:String): RecyclerView.Adapter<ordersadaptor.viewholder>() {
    class viewholder(view:View):RecyclerView.ViewHolder(view) {
        val  productname = view.findViewById<TextView>(R.id.product)
        val  toatal = view.findViewById<TextView>(R.id.number)
        val  email = view.findViewById<TextView>(R.id.emailcontact)
        val type =  view.findViewById<TextView>(R.id.type)
        val placeer = view.findViewById<TextView>(R.id.placedby)
        val  adress = view.findViewById<TextView>(R.id.adress)
        val  city = view.findViewById<TextView>(R.id.city)
        val  state = view.findViewById<TextView>(R.id.state)
        val postal =  view.findViewById<TextView>(R.id.postal)
        val mobile = view.findViewById<TextView>(R.id.mobile)
        val image = view.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val lay = LayoutInflater.from(parent.context).inflate(R.layout.orderlistitem,parent,false)
        return viewholder(lay)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        with(holder){
            productname.text= list.get(position).productname
            toatal.text = list.get(position).numberofproduct.toString()
            email.text = emailll
            type.text=list.get(position).productdescription
            placeer.text= list.get(position).fullname
            adress.text = list.get(position).address
            city.text= list.get(position).city
            state.text= list.get(position).state
            postal.text= list.get(position).postal
            mobile.text= list.get(position).mobile
            Glide.with(context).load(list.get(position).image).into(image)

        }
    }
}