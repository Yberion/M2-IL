package fr.brandon.mmm.tp3.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.brandon.mmm.tp3.database.utilisateur.UtilisateurDAO;
import fr.brandon.mmm.tp3.model.Utilisateur;

@Database(entities = {Utilisateur.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase
{
    private static AppDataBase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized AppDataBase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "note_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }

        return instance;
    }

    public abstract UtilisateurDAO utilisateurDAO();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private UtilisateurDAO utilisateurDAO;

        private PopulateDbAsyncTask(AppDataBase db)
        {
            this.utilisateurDAO = db.utilisateurDAO();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            Utilisateur u1 = new Utilisateur("Nom1", "Prénom1", "01/01/1901", "Ville1");
            Utilisateur u2 = new Utilisateur("Nom2", "Prénom2", "01/01/1902", "Ville2");
            Utilisateur u3 = new Utilisateur("Nom3", "Prénom3", "01/01/1903", "Ville3");

            this.utilisateurDAO.insert(u1);
            this.utilisateurDAO.insert(u2);
            this.utilisateurDAO.insert(u3);

            return null;
        }
    }
}
