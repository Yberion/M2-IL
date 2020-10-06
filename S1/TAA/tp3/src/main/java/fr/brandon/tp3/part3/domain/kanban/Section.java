package fr.brandon.tp3.part3.domain.kanban;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Section implements Serializable
{
    private static final long serialVersionUID = 6849713998981795362L;
    private long id;
    private String name;
    private Kanban kanban;
    private List<Carte> cartes;

    public Section()
    {
        super();
        this.cartes = new ArrayList<>();
    }

    public Section(String name, Kanban kanban)
    {
        super();
        this.name = name;
        this.kanban = kanban;
        this.cartes = new ArrayList<>();
    }

    public Section(String name, Kanban kanban, List<Carte> cartes)
    {
        super();
        this.name = name;
        this.kanban = kanban;
        this.cartes = cartes;
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

    @ManyToOne
    public Kanban getKanban()
    {
        return kanban;
    }

    public void setKanban(Kanban kanban)
    {
        this.kanban = kanban;
    }

    @OneToMany(mappedBy = "section")
    public List<Carte> getCartes()
    {
        return cartes;
    }

    public void setCartes(List<Carte> cartes)
    {
        this.cartes = cartes;
    }

    public void addCarte(Carte carte)
    {
        this.cartes.add(carte);
    }

    public void removeCarte(Carte carte)
    {
        this.cartes.remove(carte);
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
        Section other = (Section) obj;
        return Objects.equals(name, other.name);
    }
}