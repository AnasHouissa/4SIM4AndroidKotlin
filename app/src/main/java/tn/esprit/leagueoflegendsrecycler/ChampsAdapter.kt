package tn.esprit.leagueoflegendsrecycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChampsAdapter: RecyclerView.Adapter<ChampsAdapter.ChampsViewHolder>() {
    inner class ChampsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val differCallBack = object : DiffUtil.ItemCallback<Champ>() {
        override fun areItemsTheSame(oldItem: Champ, newItem: Champ): Boolean {
            return oldItem.name == newItem.name

        }

        override fun areContentsTheSame(oldItem: Champ, newItem: Champ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampsViewHolder {
        return ChampsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.champ_item,parent,false))
    }

    override fun onBindViewHolder(holder: ChampsViewHolder, position: Int) {
        val champ = differ.currentList[position]
        val ivChampImage = holder.itemView.findViewById<ImageView>(R.id.ivChamp)
        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvRole = holder.itemView.findViewById<TextView>(R.id.tvRole)

        tvName.text = "Name: ${champ.name}"
        tvRole.text = "Role: ${champ.role}"
        ivChampImage.setImageResource(champ.image)

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context,ItemDetailsActivity::class.java)
            i.putExtra("name",tvName.text)
            i.putExtra("desc",tvRole.text)
            i.putExtra("img",champ.image)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
       return  differ.currentList.size
    }
}