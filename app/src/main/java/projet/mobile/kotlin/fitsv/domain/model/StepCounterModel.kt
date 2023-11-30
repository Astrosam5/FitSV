package projet.mobile.kotlin.fitsv.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stepCounter")
data class StepCounterModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val steps: Int
)
