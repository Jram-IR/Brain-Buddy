package com.jayaram.cardmatcher;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class gameDataRepo {
    private final LiveData<List<gameData>> allGameData;
    private final DAO_BackgroundAccess dao_backgroundAccess;
    public gameDataRepo(Application application)
    {
        gameDatabase game_Database = gameDatabase.getInstance(application);
        gameDAO gameDAO = game_Database.gameDAO(); 
        dao_backgroundAccess = new DAO_BackgroundAccess(gameDAO);
        allGameData = gameDAO.getAllData();
    }

    public void insert(gameData gameData)
    {
       dao_backgroundAccess.insert(gameData);
    }

    public void delete(gameData gameData)
    {
         dao_backgroundAccess.delete(gameData);
    }

    public void deleteAllData()
    {
        dao_backgroundAccess.deleteAllData();
    }

    public LiveData<List<gameData>> getAllGameData()
    {

        return allGameData;
    }



    public static class DAO_BackgroundAccess {
        private final gameDAO gameDao;
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        DAO_BackgroundAccess(gameDAO gameDao) {
            this.gameDao = gameDao;
        }



        public void insert(gameData gameData) {
            executorService.execute(() -> gameDao.insert(gameData));
        }

        public void delete(gameData gameData)
        {
            executorService.execute(() -> gameDao.delete(gameData));
        }
        public void update(gameData gameData)
        {
            executorService.execute(() -> gameDao.update(gameData));
        }

        public void deleteAllData()
        {
            executorService.execute(gameDao::deleteAllData);
        }
    }
}
