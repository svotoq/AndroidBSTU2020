package psy.fit.bstu.lab13.retrofit

import Post
import Superhero
import User
import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("/demos/marvel")
    fun getSuperheroes(): Call<MutableList<Superhero>>

    @GET("/posts")
    fun getPosts(): Call<MutableList<Post>>

    @GET("/users")
    fun getUsers(): Call<MutableList<User>>
}