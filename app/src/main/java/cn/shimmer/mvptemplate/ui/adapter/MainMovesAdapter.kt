package cn.shimmer.mvptemplate.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.shimmer.appcore.utils.GlideUtil
import cn.shimmer.mvptemplate.R
import cn.shimmer.mvptemplate.bean.Trailers

class MainMovesAdapter(private val trailers: MutableList<Trailers>) :
    RecyclerView.Adapter<MainMovesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moves_item_img: ImageView = itemView.findViewById<ImageView>(R.id.moves_item_img)
        val moves_item_title: TextView = itemView.findViewById<TextView>(R.id.moves_item_title)
        val moves_item_time: TextView = itemView.findViewById<TextView>(R.id.moves_item_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_moves_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = trailers[position]
        GlideUtil.loadingImage(bean.coverImg, holder.moves_item_img)
        holder.moves_item_title.text = "电影名称：${bean.movieName}"
        holder.moves_item_time.text = "电影时长：${bean.videoLength}分钟"
    }
}