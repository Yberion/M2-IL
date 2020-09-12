package TP1.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Fiche
{
    /*
     * Un libellé
     * La date butoire
     * un utilisateur à qui cette tâche est affectée
     * Le temps dont vous estimez avoir besoin en minutes
     * un ensemble de tags
     * un lieu
     * une url
     * il est également possible d'ajouter une note explicative.
     */
    private long id;
    private String libelle;
    private Date dateButoire;
    private Utilisateur utilisateur;
    // En jour
    private int tempsEstimation;
    private List<Tag> tags;
    private String lieu;
    private String url;
    private String hashGit;
    private String note;
    private Carte carte;

    public Fiche()
    {
        super();
        this.tags = new ArrayList<>();
    }
    
    public Fiche(String libelle, Date dateButoire, Utilisateur utilisateur, int tempsEstimation, List<Tag> tags,
            String lieu, String url, Carte carte)
    {
        super();
        this.libelle = libelle;
        this.dateButoire = dateButoire;
        this.utilisateur = utilisateur;
        this.tempsEstimation = tempsEstimation;
        this.tags = tags;
        this.lieu = lieu;
        this.url = url;
        this.note = "";
        this.carte = carte;
    }

    @Id
    @GeneratedValue
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getLibelle()
    {
        return libelle;
    }

    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
    }

    public Date getDateButoire()
    {
        return dateButoire;
    }

    public void setDateButoire(Date dateButoire)
    {
        this.dateButoire = dateButoire;
    }

    @OneToOne
    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public int getTempsEstimation()
    {
        return tempsEstimation;
    }

    public void setTempsEstimation(int tempsEstimation)
    {
        this.tempsEstimation = tempsEstimation;
    }
    
    @ManyToMany
    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    public String getLieu()
    {
        return lieu;
    }

    public void setLieu(String lieu)
    {
        this.lieu = lieu;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getHashGit()
    {
        return hashGit;
    }

    public void setHashGit(String hashGit)
    {
        this.hashGit = hashGit;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    @OneToOne
    public Carte getCarte()
    {
        return carte;
    }

    public void setCarte(Carte carte)
    {
        this.carte = carte;
    }
}
