package com.example.YournurseryAdmin

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.apitest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.io.path.Path

class Orders : Fragment() {

    var list = MutableLiveData<List<ordersresponse>>()
    var user :String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getString("owner")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progress = view.findViewById<ProgressBar>(R.id.progressBar2)
        progress.visibility = View.VISIBLE
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        swipe.setOnRefreshListener {
            getorders(progress)
        }
        getorders(progress)
        list.observe(viewLifecycleOwner, Observer {

            if (list.value == null) {
                progress.visibility = View.VISIBLE

            }else{
                swipe.isRefreshing = false
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycle).apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter= ordersadaptor(it,context,user!!)
                }
            }
        })
    }

    private fun getorders(progress: ProgressBar) {

        GlobalScope.launch {
            withContext(Dispatchers.IO){
                 val z = services.orders(user!!).body()
                withContext(Dispatchers.Main){
                    try {
                        list.value = z!!
                    }catch (ex:java.lang.Exception){
                        Toast.makeText(context, "No Orders", Toast.LENGTH_SHORT).show()
                    }
                    progress.visibility = View.INVISIBLE
                }
            }
        }
    }
}