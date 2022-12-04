package com.jayaram.cardmatcher;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class gameViewModel  extends AndroidViewModel {

    private final gameDataRepo repo;
    private final LiveData<List<gameData>> allGameData;
    public gameViewModel(@NonNull Application application) {
        super(application);
        repo = new gameDataRepo(application);
        allGameData = repo.getAllGameData();
    }

    public void insert(gameData gameData)
    {
        repo.insert(gameData);
    }

    public void delete(gameData gameData)
    {
        repo.delete(gameData);
    }

    public void deleteAllData()
    {
        repo.deleteAllData();
    }
    public LiveData<List<gameData>> getAllGameData()
    {
        return allGameData;
    }

}
