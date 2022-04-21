package com.example.room_database12;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainRepository {

    private MainDao mainDao;
    private MainDatabase mainDatabase;
    private LiveData<List<MainEntity>> listLiveDataMainEntity;

    public MainRepository(Application application) {
        mainDatabase = MainDatabase.getDatabase(application);
        mainDao = mainDatabase.mainDao();
        listLiveDataMainEntity = mainDao.getAllMainEntity();
    }

    public LiveData<List<MainEntity>> getAllMainEntity() {
        return mainDao.getAllMainEntity();
    }

    public void insertMainEntity(final MainEntity mainEntity){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mainDatabase.mainDao().insertMainEntity(mainEntity);
                return null;
            }
        }.execute();
    }
    public void updateMainEntity(final MainEntity mainEntity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mainDatabase.mainDao().updateMainEntity(mainEntity);
                return null;
            }
        }.execute();
    }

    public void deleteMainEntity(final MainEntity mainEntity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mainDatabase.mainDao().deleteMainEntity(mainEntity);
                return null;
            }
        }.execute();
    }

}
