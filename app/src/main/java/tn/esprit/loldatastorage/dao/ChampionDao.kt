package tn.esprit.loldatastorage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import tn.esprit.leagueoflegendrecyclerview.data.Champion


@Dao
interface ChampionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun upsertChamp (champ: Champion): Long

    @Delete
    fun deleteChamp (champ: Champion)


    @Query("Select * from champs")
    fun getAllChamps(): List<Champion>
}