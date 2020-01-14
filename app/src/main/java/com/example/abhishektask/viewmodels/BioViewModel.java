package com.example.abhishektask.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.abhishektask.repositories.BioRepository;
import com.example.abhishektask.database.Bio;

import java.util.List;

public class BioViewModel extends AndroidViewModel {

    private BioRepository repository;
    private LiveData<List<Bio>> allBios;

    public BioViewModel(@NonNull Application application) {
        super(application);
        repository = new BioRepository(application);
        allBios = repository.getAllBio();
    }

    public void insert(Bio bio) {
        repository.insert(bio);
    }

    public void update(Bio bio) {
        repository.insert(bio);
    }

    public void delete(Bio bio) {
        repository.insert(bio);
    }

    public void deleteAllBios(Bio bio) {
        repository.insert(bio);
    }

    public LiveData<List<Bio>> getAllBios() {
        return allBios;
    }
}
