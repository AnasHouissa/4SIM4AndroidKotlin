package tn.esprit.curriculumvitaev2.Adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.Database.Database
import tn.esprit.curriculumvitaev2.Database.Models.Experience
import tn.esprit.curriculumvitaev2.R

class ExperienceAdapter(database:Database):RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder>() {
    private val db = database
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
        holder.itemView.findViewById<ImageView>(R.id.ivExpItem).setImageURI(exp.image)
        holder.itemView.findViewById<TextView>(R.id.tvExpItemCompaName).text=exp.compnName
        holder.itemView.findViewById<TextView>(R.id.tvExpItemCompaAdd).text=exp.compAdd
        holder.itemView.findViewById<TextView>(R.id.tvExpItemStartDate).text=exp.startD
        holder.itemView.findViewById<TextView>(R.id.tvExpItemEndDate).text=exp.endD
        holder.itemView.findViewById<TextView>(R.id.tvExpItemDesc).text=exp.descr
        holder.itemView.findViewById<ImageView>(R.id.ivExpDelete).setOnClickListener {
            displayDeleteDialog(holder.itemView.context,exp)
        }
    }

    override fun getItemCount(): Int {
       return diff.currentList.size
    }

    fun deleteItem(exp: Experience) {
        db.getExpDao().deleteExperience(exp)
        val newList = diff.currentList.toMutableList()
        newList.remove(exp)
        diff.submitList(newList)
    }
    fun displayDeleteDialog(context: Context,exp: Experience){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure you want to delete the Experience?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            deleteItem(exp)
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->

        }

        builder.show()
    }
}

