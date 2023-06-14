package com.example.rickapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Any.results: Any
    get() = Unit

private fun Nothing?.body() {
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rick = findViewById<RecyclerView>(R.id.rv_character)

        ApiConfig.getService().getRick().enqueue(/* callback = */ object : Callback<ResponseRick> {
            override fun onResponse(call: Call<ResponseRick>, response: Response<ResponseRick>) {
                if (response.isSuccessful) {
                    val response = null
                    val responseRick = response.body()
                    val dataRick = responseRick?.results
                    val rickAdapter = RickAdapter(dataRick as List<ResultsItem>)
                    rick.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        rickAdapter.notifyDataSetChanged()
                        adapter = rickAdapter
                    }
                }

                fun onFailure(call: Call<ResponseRick>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }

            override fun onFailure(call: Call<ResponseRick>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}