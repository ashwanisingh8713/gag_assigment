package com.gag.assignment.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gag.assignment.app.ProductApplication;

/**
 * Created by ashwanisingh on 26/06/22.
 */

@Database(entities = {CartTable.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ProductDB extends RoomDatabase {

    private static volatile ProductDB INSTANCE;

    public abstract CartDao productDao();

    public static ProductDB getInstance() {
        if (INSTANCE == null) {
            synchronized (ProductDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ProductApplication.getContext(), ProductDB.class, "GAG.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
