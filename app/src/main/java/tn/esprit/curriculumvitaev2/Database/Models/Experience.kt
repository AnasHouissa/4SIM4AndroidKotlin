package tn.esprit.curriculumvitaev2.Database.Models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experiences")
data class Experience(
    val image: Uri,
    val compnName: String,
    val compAdd: String,
    val startD: String,
    val endD: String,
    val descr: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
)
