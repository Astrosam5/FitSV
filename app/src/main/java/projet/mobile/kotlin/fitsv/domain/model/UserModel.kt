package projet.mobile.kotlin.fitsv.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "user")
data class UserModel (
    @PrimaryKey val id: ObjectId,
    val name: String,
    val password: String
)


data class ObjectId(
    val oid: String
)

class TypeConverterObjectID{
    @TypeConverter
    fun stringToObjectId(data: String?): ObjectId? {
        if (data == null) return null
        return ObjectId(data)
    }

    @TypeConverter
    fun objectIdToString(data: ObjectId?): String? {
        if (data == null) return null
        return data.oid
    }

}
