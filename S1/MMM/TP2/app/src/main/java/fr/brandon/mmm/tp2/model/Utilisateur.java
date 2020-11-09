package fr.brandon.mmm.tp2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class Utilisateur
{
    @PrimaryKey(autoGenerate = true)
    private long uid;
    @ColumnInfo(name = "last_name")
    private String nom;
    @ColumnInfo(name = "first_name")
    private String prenom;
    @ColumnInfo(name = "birth_date")
    private String dateNaissance;
    @ColumnInfo(name = "city")
    private String ville;

    public Utilisateur(String nom, String prenom, String dateNaissance, String ville)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
    }

    public long getUid()
    {
        return uid;
    }

    public void setUid(long uid)
    {
        this.uid = uid;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getDateNaissance()
    {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }
}
