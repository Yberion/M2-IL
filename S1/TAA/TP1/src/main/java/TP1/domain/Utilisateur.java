package TP1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur
{
    private long id;
    private String name;
    
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
}
