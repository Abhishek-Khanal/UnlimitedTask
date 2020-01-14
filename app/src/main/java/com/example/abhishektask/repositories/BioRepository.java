package com.example.abhishektask.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.abhishektask.database.Bio;
import com.example.abhishektask.database.BioDao;
import com.example.abhishektask.database.BioDatabase;

import java.util.List;

public class BioRepository {

    private BioDao bioDao;

    private LiveData<List<Bio>> allBio;

    public BioRepository(Application application) {
        BioDatabase database = BioDatabase.getInstance(application);
        bioDao = database.bioDao();
        allBio = bioDao.getAllBio();

    }

    public void insert(Bio bio) {
        new InsertBioAsyncTask(bioDao).execute(bio);

    }

    public void update(Bio bio) {
        new UpdateBioAsyncTask(bioDao).execute(bio);
    }

    public void delete(Bio bio) {
        new DeleteBioAsyncTask(bioDao).execute(bio);

    }

    public void deleteAllBio(Bio bio) {
        new DeleteAllBioAsyncTask(bioDao).execute();
    }

    public LiveData<List<Bio>> getAllBio() {
        return allBio;
    }

    private static class InsertBioAsyncTask extends AsyncTask<Bio, Void, Void> {

        private BioDao bioDao;

        private InsertBioAsyncTask(BioDao bioDao) {
            this.bioDao = bioDao;
        }

        @Override
        protected Void doInBackground(Bio... bios) {
            bioDao.insert(bios[0]);
            return null;
        }
    }

    private static class UpdateBioAsyncTask extends AsyncTask<Bio, Void, Void> {

        private BioDao bioDao;

        private UpdateBioAsyncTask(BioDao bioDao) {
            this.bioDao = bioDao;
        }

        @Override
        protected Void doInBackground(Bio... bios) {
            bioDao.update(bios[0]);
            return null;
        }
    }

    private static class DeleteBioAsyncTask extends AsyncTask<Bio, Void, Void> {

        private BioDao bioDao;

        private DeleteBioAsyncTask(BioDao bioDao) {
            this.bioDao = bioDao;
        }

        @Override
        protected Void doInBackground(Bio... bios) {
            bioDao.delete(bios[0]);
            return null;
        }
    }

    private static class DeleteAllBioAsyncTask extends AsyncTask<Void, Void, Void> {

        private BioDao bioDao;

        private DeleteAllBioAsyncTask(BioDao bioDao) {
            this.bioDao = bioDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bioDao.deleteAllBio();
            return null;
        }
    }


}
