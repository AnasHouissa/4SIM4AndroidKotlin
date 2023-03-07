package tn.esprit.loldatastorage.dao

import androidx.room.*
import tn.esprit.curriculumvitaev2.Database.Models.Education


@Dao
interface EducationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun upsertEducation (education: Education): Long

    @Delete
    fun deleteEducation (education: Education)


    @Query("Select * from educations")
    fun getAllEducations(): List<Education>
}