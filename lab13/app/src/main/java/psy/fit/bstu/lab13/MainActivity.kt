package psy.fit.bstu.lab13

import IBaseModel
import Post
import Comments
import Todo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import psy.fit.bstu.lab13.retrofit.CommonClient
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var dataRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var initText: TextView
    private val link = "https://jsonplaceholder.typicode.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initializeElements()
    }

    private fun initializeElements() {
        radioGroup = findViewById(R.id.radioApisGroup)
        dataRecyclerView = findViewById(R.id.dataRecycleView)
        progressBar = findViewById(R.id.progressbar)
        initText = findViewById(R.id.startLabel)

        this.radioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            if (initText.visibility == View.VISIBLE) {
                initText.visibility = View.INVISIBLE
            }

            when (radioGroup.checkedRadioButtonId) {
                R.id.marvelApi -> {
                    this.renderHeroes()
                }
                R.id.postsApi -> {
                    this.renderPosts()
                }
                R.id.catsApi -> {
                    this.renderTodos()
                }
            }
        }
    }

    private fun renderHeroes() {
        progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            val response = CommonClient(link).genericRetrofitService.getComments().awaitResponse()

            if (response.isSuccessful) {
                val data: List<IBaseModel> = response.body()!!

                withContext(Dispatchers.Main) {
                    render(data as List<Comments>)                }
            }
        }
    }

    private fun renderPosts() {
        progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            val response = CommonClient(link).genericRetrofitService.getPosts().awaitResponse()

            if (response.isSuccessful) {
                val data: List<IBaseModel> = response.body()!!

                withContext(Dispatchers.Main) {
                    render(data as List<Post>)
                }
            }
        }
    }

    private fun renderTodos() {
        progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            val response = CommonClient(link).genericRetrofitService.getTodos().awaitResponse()

            if (response.isSuccessful) {
                val data: List<IBaseModel> = response.body()!!

                withContext(Dispatchers.Main) {
                    render(data as List<Todo>)
                }
            }
        }
    }

    private fun render(data: List<IBaseModel>) {
        progressBar.visibility = View.GONE
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dataRecyclerView.layoutManager = layoutManager
        dataRecyclerView.adapter = ViewAdapter(data)
    }
}