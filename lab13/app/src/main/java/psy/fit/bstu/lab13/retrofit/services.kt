package psy.fit.bstu.lab13.retrofit

import Post
import Comments
import Todo
import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("/comments")
    fun getComments(): Call<MutableList<Comments>>

    @GET("/posts")
    fun getPosts(): Call<MutableList<Post>>

    @GET("/todos")
    fun getTodos(): Call<MutableList<Todo>>
}