package com.example.composeexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComposeViewModel : ViewModel() {
    var movieListResponse:List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getMovieList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
             apiService.getMovies().enqueue(object : Callback<Movie>{
              override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                  val responseBody = response.body()

                  val list = ArrayList<Movie>()
                  var ownerImage: String = "text1"
                  var ownerName: String = "test2"

                  try {
                      val responseArray = JSONArray(response.body())
                      for (i in 0 until responseArray.length()) {
                          val jsonObject: JSONObject = responseArray.getJSONObject(i)
                          if (i == 0) {
                              val ownerJson = jsonObject.getJSONObject("owner")
                              ownerImage = ownerJson.getString("avatar_url")
                              ownerName = ownerJson.getString("login")
                          }
                          val name = if (jsonObject.getString("name") == "null") {
                              "------"
                          } else {
                              jsonObject.getString("name")
                          }
                          val language = if (jsonObject.getString("language") == "null") {
                              "----"
                          } else {
                              jsonObject.getString("language")
                          }
                          list.add(Movie(name, ownerImage, ownerName, language))
                          movieListResponse = list
                      }
                  } catch (e: JSONException) {
                      e.printStackTrace()
                  }
                  movieListResponse = list
              }

              override fun onFailure(call: Call<Movie>, t: Throwable) {
                  TODO("Not yet implemented")
              }

             })
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

data class Movie(val name: String, val imageUrl: String, val desc: String, val category: String)