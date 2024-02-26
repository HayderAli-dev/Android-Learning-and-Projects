package com.example.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.crypto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var data:ArrayList<Model>
    private lateinit var Rvadapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        apiData
        data=ArrayList<Model>()
        Rvadapter= RvAdapter(this,data)
        binding.rv.layoutManager=LinearLayoutManager(this)
        binding.rv.adapter=Rvadapter
        setContentView(binding.root)
    }
    val apiData:Unit
        get() {
            val url="https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"
            val queue=Volley.newRequestQueue(this)
            val jsonObjectRequest:JsonObjectRequest=
                object:JsonObjectRequest(Method.GET,url,null,Response.Listener {
                    response -> try {
                    val dataArray=response.getJSONArray("data")
                    for (i in 0 until dataArray.length()){
                        val dataObject=dataArray.getJSONObject(i)
                        val symbol=dataObject.getString("symbol")
                        val name=dataObject.getString("name")
                        val quote=dataObject.getJSONObject("quote")
                        val USD=quote.getJSONObject("USD")
                        val price=USD.getDouble("price")
                        data.add(Model(name,symbol,price))
                    }
                    Rvadapter.notifyDataSetChanged()
                    } catch (e:Exception){
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                    }

                },Response.ErrorListener {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                })
                {
                    override fun getHeaders(): Map<String, String> {
                        val headers=HashMap<String,String>()
                        headers["X-CMC_PRO_API_KEY"]="c599aa11-d2f8-4110-8519-a3c1ab46d5fc"
                        return headers
                    }
                }
            queue.add(jsonObjectRequest)
        }
}