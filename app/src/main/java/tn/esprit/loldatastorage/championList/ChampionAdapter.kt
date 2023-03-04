package tn.esprit.leagueoflegendrecyclerview.championList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.leagueoflegendrecyclerview.data.Champion
import tn.esprit.leagueoflegendrecyclerview.data.*
import tn.esprit.loldatastorage.DetailActivity
import tn.esprit.loldatastorage.R
import tn.esprit.loldatastorage.utils.AppDataBase

class ChampionAdapter(database: AppDataBase) : RecyclerView.Adapter<ChampionViewHolder>() {

    private val db = database
    private var recyclerView: RecyclerView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.champion_single_item, parent, false)

        return ChampionViewHolder(view)
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val champion = differ.currentList[position]
        val name = champion.champName
        val role = champion.champRole

        holder.championPic.setImageResource(champion.champPic)
        holder.championName.text = name
        holder.championRole.text = role

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra(PICTURE, champion.champPic)
                putExtra(NAME, name)
                putExtra(ROLE, role)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            deleteItem(champion)
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView=recyclerView
    }

    override fun getItemCount() = differ.currentList.size

    fun addItem(champ: Champion) {
        db.getChampionDao().upsertChamp(champ)
        val newList = differ.currentList.toMutableList()
        newList.add(champ)
        differ.submitList(newList)
        recyclerView?.postDelayed({
            recyclerView?.scrollToPosition(differ.currentList.size - 1)
        }, 100)

    }

    fun deleteItem(champ: Champion) {
        db.getChampionDao().deleteChamp(champ)
        val newList = differ.currentList.toMutableList()
        newList.remove(champ)
        differ.submitList(newList)
    }
}