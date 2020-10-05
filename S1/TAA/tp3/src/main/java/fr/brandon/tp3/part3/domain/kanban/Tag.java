package fr.brandon.tp3.part3.domain.kanban;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1852567379215943195L;
    private long id;
    private String name;
    private List<Fiche> fiches;
    
    public Tag()
    {
        super();
    }

    public Tag(String name)
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

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }
        Tag other = (Tag) obj;
        return Objects.equals(name, other.name);
    }
}
