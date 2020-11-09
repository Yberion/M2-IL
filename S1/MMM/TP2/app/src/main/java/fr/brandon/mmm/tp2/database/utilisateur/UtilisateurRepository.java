package fr.brandon.mmm.tp2.database.utilisateur;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.brandon.mmm.tp2.database.AppDataBase;
import fr.brandon.mmm.tp2.model.Utilisateur;

public class UtilisateurRepository
{
    private UtilisateurDAO utilisateurDAO;
    private LiveData<List<Utilisateur>> allUsers;

    public UtilisateurRepository(Application application)
    {
        AppDataBase database = AppDataBase.getInstance(application);
        utilisateurDAO = database.utilisateurDAO();
        allUsers = utilisateurDAO.getAll();
    }

    public void insert(Utilisateur utilisateur)
    {
        new InsertUserAsyncTask(utilisateurDAO).execute(utilisateur);
    }

    public void delete(Utilisateur utilisateur)
    {
        new DeleteUserAsyncTask(utilisateurDAO).execute(utilisateur);
    }

    public void deleteAllUsers()
    {
        new DeleteAllUsersAsyncTask(utilisateurDAO).execute();
    }

    public LiveData<List<Utilisateur>> getAllUsers()
    {
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<Utilisateur, Void, Void>
    {
        private UtilisateurDAO utilisateurDAO;

        private InsertUserAsyncTask(UtilisateurDAO utilisateurDAO)
        {
            this.utilisateurDAO = utilisateurDAO;
        }

        @Override
        protected Void doInBackground(Utilisateur... users)
        {
            this.utilisateurDAO.insert(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<Utilisateur, Void, Void>
    {
        private UtilisateurDAO utilisateurDAO;

        private DeleteUserAsyncTask(UtilisateurDAO utilisateurDAO)
        {
            this.utilisateurDAO = utilisateurDAO;
        }

        @Override
        protected Void doInBackground(Utilisateur... users)
        {
            utilisateurDAO.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private UtilisateurDAO utilisateurDAO;

        private DeleteAllUsersAsyncTask(UtilisateurDAO utilisateurDAO)
        {
            this.utilisateurDAO = utilisateurDAO;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            utilisateurDAO.deleteAll();
            return null;
        }
    }
}
