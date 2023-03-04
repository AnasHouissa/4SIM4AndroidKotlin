package tn.esprit.leagueoflegendrecyclerview.data

import androidx.room.Entity
import androidx.room.PrimaryKey

const val PICTURE = "PICTURE"
const val NAME = "NAME"
const val ROLE = "ROLE"

@Entity(tableName = "champs")
data class Champion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val champPic: Int,

    val champName: String,

    val champRole: String

){
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Champion) return false
        return id == other.id && champName == other.champName
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + champName.hashCode()
        return result
    }
}