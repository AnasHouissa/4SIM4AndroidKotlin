package tn.esprit.curriculumvitaev2.Database.Converters

import androidx.room.TypeConverter
import android.net.Uri

class Converters {

    @TypeConverter
    fun fromUri(uri: Uri): String { // if u get srouce object give me the name
        return uri.toString()
    }

    @TypeConverter
    fun toUri(uri: String): Uri {
        return Uri.parse(uri)
    }
}