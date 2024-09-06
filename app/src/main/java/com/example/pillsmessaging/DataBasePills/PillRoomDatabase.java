package com.example.pillsmessaging.DataBasePills;


import static com.example.pillsmessaging.Constants.Constants.VERSION_OF_DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.annotations.NonNull;

@Database(entities = {ItemPill.class}, version = VERSION_OF_DATABASE, exportSchema = false)
/// exportSchema = true используется в промышленной разработке
public abstract class PillRoomDatabase extends RoomDatabase {
    public abstract PillDao pillDao();

    private static final int NUMBER_OF_THREADS = 4;
    private static volatile PillRoomDatabase INSTANCE;
    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PillRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PillRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PillRoomDatabase.class, "pills").build();  // .addCallback(sRoomDatabaseCallback)
                }
            }
        }
        return INSTANCE;
    }

}
