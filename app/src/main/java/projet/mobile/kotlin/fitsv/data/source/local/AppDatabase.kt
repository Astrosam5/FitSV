/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import projet.mobile.kotlin.fitsv.domain.model.TypeConverterObjectID
import projet.mobile.kotlin.fitsv.domain.model.UserModel

/**
 * Class AppDatabase
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@TypeConverters(TypeConverterObjectID::class)
@Database(entities = [UserModel::class, StepCounterModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var DB_INSTANCE: AppDatabase? = null

        fun getAppDBInstance(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "FITSV_DB")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
    abstract fun userDao(): UserDao

    abstract fun stepCounterDao(): StepCounterDao
}