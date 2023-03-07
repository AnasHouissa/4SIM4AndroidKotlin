package tn.esprit.curriculumvitaev2.Adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.Database.Database
import tn.esprit.curriculumvitaev2.Database.Models.Education
import tn.esprit.curriculumvitaev2.Database.Models.Experience
import tn.esprit.curriculumvitaev2.R

class EducationAdapter(database: Database) : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {
    inner class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private val db = database


    val diffCallback = object : DiffUtil.ItemCallback<Education>() {
        override fun areItemsTheSame(oldItem: Education, newItem: Education): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Education, newItem: Education): Boolean {
            return oldItem == newItem
        }
    }
    val diff = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        return EducationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.education_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val education = diff.currentList[position]
        holder.itemView.findViewById<ImageView>(R.id.ivEduItem).setImageURI(education.image)
        holder.itemView.findViewById<TextView>(R.id.ivEduCompAdd).text=education.compAdd
        holder.itemView.findViewById<TextView>(R.id.ivEduCompName).text=education.compnName
        holder.itemView.findViewById<TextView>(R.id.ivEduStartDate).text=education.startD
        holder.itemView.findViewById<TextView>(R.id.ivEduEndDate).text=education.endD
        holder.itemView.findViewById<ImageView>(R.id.ivEduDelete).setOnClickListener {
            displayDeleteDialog(holder.itemView.context,education)
        }
    }
    fun displayDeleteDialog(context: Context, education: Education){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure you want to delete the Education?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            deleteItem(education)
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->

        }

        builder.show()
    }
    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    fun deleteItem(edu: Education) {
        db.getEduDao().deleteEducation(edu)
        val newList = diff.currentList.toMutableList()
        newList.remove(edu)
        diff.submitList(newList)
    }
}