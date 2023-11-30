package projet.mobile.kotlin.fitsv.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "user")
data class UserModel (
    @PrimaryKey() @ColumnInfo(name = "id") val id: ObjectId,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)


data class ObjectId(
    val oid: String
)

class TypeConverterObjectID{

    private val gson: Gson = Gson()
    @TypeConverter
    fun stringToSomeObjectList(data: String?): ObjectId? {
        if (data == null) return null
        val listType: Type = object : TypeToken<ObjectId?>() {}.type
        return gson.fromJson<ObjectId?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObject: ObjectId?): String? {
        return gson.toJson(someObject)
    }

}
