package com.example.myapplication.Network

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.gitUser

import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiCall {

    companion object {

        var data: List<gitUser> by mutableStateOf(listOf())


        fun getsdata(): List<gitUser> {




            val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()


            val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)


            val call: Call<List<gitUser>> =
                service.get("Bearer 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", "{user}", "1")




            call.enqueue(object : Callback<List<gitUser>> {


                override fun onResponse(
                    myCall: Call<List<gitUser>>,
                    response: Response<List<gitUser>>
                ) {

                    val dataincompose = response.body()!!

                    val tag = "lll"
                    Log.d(tag, dataincompose.toString())


                }

                override fun onFailure(call: Call<List<gitUser>>, t: Throwable) {

                }
            })

            return data
        }

    }
}