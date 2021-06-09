package br.com.luanadev.slleptrackerapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.luanadev.slleptrackerapplication.data.entity.SleepNightEntity
import br.com.luanadev.slleptrackerapplication.data.dao.SleepDao

@Database(entities = [SleepNightEntity::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    abstract val sleepDao: SleepDao

    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}