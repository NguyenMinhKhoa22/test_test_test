package com.example.room_database12;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    MainRepository mainRepository;
    LiveData<List<MainEntity>> mainEntityList;

    public ViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository(application);
        mainEntityList = mainRepository.getAllMainEntity();
    }

    public LiveData<List<MainEntity>> getAllMainEntity() {
        return  mainRepository.getAllMainEntity();
    }

    public void insertMainEntity(MainEntity mainEntity) {
        mainRepository.insertMainEntity(mainEntity);
    }

    public void updateMainEntity(MainEntity mainEntity) {
        mainRepository.updateMainEntity(mainEntity);
    }

    public void deleteMainEntity(MainEntity mainEntity) {
        mainRepository.deleteMainEntity(mainEntity);
    }


}
