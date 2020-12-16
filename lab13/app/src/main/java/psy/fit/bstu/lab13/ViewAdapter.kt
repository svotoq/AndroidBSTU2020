package psy.fit.bstu.lab13

import IBaseModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewAdapter(_posts: List<IBaseModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var posts: List<IBaseModel> = _posts

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rows,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView.findViewById(R.id.placeName) as TextView
        view.text = posts[position].toString()
    }

    override fun getItemCount(): Int = posts.size
}