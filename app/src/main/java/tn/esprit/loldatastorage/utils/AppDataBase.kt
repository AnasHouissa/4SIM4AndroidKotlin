package tn.esprit.loldatastorage.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.esprit.leagueoflegendrecyclerview.data.Champion
import tn.esprit.loldatastorage.dao.ChampionDao


@Database(
    entities = [Champion::class],
    version = 1)
abstract class AppDataBase :RoomDatabase() {

    abstract fun getChampionDao(): ChampionDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance=createDatabase(context)
                }
            }
            return instance!!
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "champs_db.db"
        ).allowMainThreadQueries().build()
    }
}


