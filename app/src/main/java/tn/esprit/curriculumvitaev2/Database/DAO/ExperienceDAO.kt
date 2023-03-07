package tn.esprit.loldatastorage.dao

import androidx.room.*
import tn.esprit.curriculumvitaev2.Database.Models.Experience


@Dao
interface ExperienceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun upsertExperience (exp: Experience): Long

    @Delete
    fun deleteExperience (exp: Experience)


    @Query("Select * from experiences")
    fun getAllExperiences(): List<Experience>
}