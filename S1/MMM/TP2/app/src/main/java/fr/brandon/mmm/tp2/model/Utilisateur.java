package fr.brandon.mmm.tp2.model;

public class Utilisateur
{
    private long id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String ville;

    public Utilisateur()
    {
    }

    public Utilisateur(String nom, String prenom, String dateNaissance, String ville)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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
