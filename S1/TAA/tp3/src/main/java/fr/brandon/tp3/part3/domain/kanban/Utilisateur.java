package fr.brandon.tp3.part3.domain.kanban;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Utilisateur implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3909013690985445541L;
    private long id;
    private String name;
    private List<Fiche> fiches;
    
    public Utilisateur()
    {
        super();
    }
    
    public Utilisateur(String name)
    {
        super();
        this.name = name;
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
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    @ManyToMany
    public List<Fiche> getFiches()
    {
        return fiches;
    }

    public void setFiches(List<Fiche> fiches)
    {
        this.fiches = fiches;
    }
}
