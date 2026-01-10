package efecun.dev.yemektarifleriuygulamasi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import efecun.dev.yemektarifleriuygulamasi.model.Tarif

@Database(entities = [Tarif::class], version = 1, exportSchema = false)
abstract class TarifDatabase : RoomDatabase() {
    abstract fun tarifDao(): TarifDAO
}