package fr.brandon.mmm.tp1.fragment;

import androidx.lifecycle.ViewModel;

public class FragmentInfoViewModel extends ViewModel
{
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String ville;
    private String departement;
    private String numero;
    private boolean phoneCreated;

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

    public String getDepartement()
    {
        return departement;
    }

    public void setDepartement(String departement)
    {
        this.departement = departement;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public boolean isPhoneCreated()
    {
        return phoneCreated;
    }

    public void setPhoneCreated(boolean phoneCreated)
    {
        this.phoneCreated = phoneCreated;
    }
}