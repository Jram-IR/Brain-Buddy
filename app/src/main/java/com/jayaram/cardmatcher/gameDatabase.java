package com.jayaram.cardmatcher;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {gameData.class},version = 2)
public abstract class gameDatabase extends RoomDatabase {

    private static gameDatabase instance;

    public abstract gameDAO gameDAO();

    public static synchronized gameDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    gameDatabase.class,"game_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(instance).execute();
        }
    };

    private static  class  populateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private gameDAO gameDAO;

        private populateDbAsyncTask(gameDatabase db)
        {
            gameDAO = db.gameDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
