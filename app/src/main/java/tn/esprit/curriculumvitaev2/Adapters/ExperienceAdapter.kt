package tn.esprit.curriculumvitaev2.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.Models.Experience
import tn.esprit.curriculumvitaev2.R

class ExperienceAdapter:RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder>() {

    inner class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val diffCallback = object : DiffUtil.ItemCallback<Experience>(){
        override fun areItemsTheSame(oldItem: Experience, newItem: Experience): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Experience, newItem: Experience): Boolean {
            return oldItem == newItem
        }
    }
    val diff =AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        return ExperienceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.experience_item,parent,false))
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        val exp = diff.currentList[position]
        holder.itemView.findViewById<ImageView>(R.id.ivExpItem).setImageResource(exp.image)
        holder.itemView.findViewById<TextView>(R.id.tvExpItemCompaName).text=exp.compnName
        holder.itemView.findViewById<TextView>(R.id.tvExpItemCompaAdd).text=exp.compAdd
        holder.itemView.findViewById<TextView>(R.id.tvExpItemStartDate).text=exp.startD
        holder.itemView.findViewById<TextView>(R.id.tvExpItemEndDate).text=exp.endD
        holder.itemView.findViewById<TextView>(R.id.tvExpItemDesc).text=exp.descr
    }

    override fun getItemCount(): Int {
       return diff.currentList.size
    }
}

