package com.mynotes.save.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Define entities and database version
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    // Singleton instance of the database
    private static NoteDatabase instance;

    // Abstract method to get the DAO
    public abstract NoteDao noteDao();

    // Get the singleton instance of the database
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration() // Handle schema changes by recreating database
                    .addCallback(roomCallback) // Add callback for initial population
                    .build();
        }
        return instance;
    }

    // Callback to populate the database on creation (optional, for testing/initial data)
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Execute AsyncTask to populate initial data
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // AsyncTask to perform database operations in the background
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Optional: Insert some initial notes when the database is created
            // noteDao.insert(new Note("Title 1", "Description 1"));
            // noteDao.insert(new Note("Title 2", "Description 2"));
            // noteDao.insert(new Note("Title 3", "Description 3"));
            return null;
        }
    }
}