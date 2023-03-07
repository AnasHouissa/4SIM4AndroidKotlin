package tn.esprit.curriculumvitaev2.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tn.esprit.curriculumvitaev2.Database.Converters.Converters
import tn.esprit.curriculumvitaev2.Database.Models.Education
import tn.esprit.curriculumvitaev2.Database.Models.Experience
import tn.esprit.loldatastorage.dao.EducationDAO
import tn.esprit.loldatastorage.dao.ExperienceDAO

@androidx.room.Database(entities = [Education::class,Experience::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database :RoomDatabase() {

    abstract fun getEduDao(): EducationDAO
    abstract fun getExpDao(): ExperienceDAO

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getDatabase(context: Context): Database {
            if (instance == null) {
                synchronized(this) {
                    instance = createDatabase(context)
                }
            }
            return instance!!
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "cv_db.db"
        ).allowMainThreadQueries().build()
    }
}


