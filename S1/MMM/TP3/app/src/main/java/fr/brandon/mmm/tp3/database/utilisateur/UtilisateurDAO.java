package fr.brandon.mmm.tp3.database.utilisateur;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.brandon.mmm.tp3.model.Utilisateur;

@Dao
public interface UtilisateurDAO
{
    @Query("SELECT * FROM user_table ")
    LiveData<List<Utilisateur>> getAll();

    @Query("SELECT * FROM user_table WHERE uid IN (:userIds)")
    LiveData<List<Utilisateur>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    Utilisateur findByName(String first, String last);

    @Insert
    void insertAll(Utilisateur... users);

    @Insert
    void insert(Utilisateur user);

    @Delete
    void delete(Utilisateur user);

    @Delete
    void deleteAll(Utilisateur... users);
}
