package fr.brandon.tp3.part3.domain.kanban;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Carte implements Serializable
{
    private static final long serialVersionUID = 4289243947415248657L;
    private long id;
    private Section section;
    private Fiche fiche;

    public Carte()
    {
        super();
    }

    public Carte(Section section, Fiche fiche)
    {
        super();
        this.section = section;
        this.fiche = fiche;
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

    @OneToOne
    public Section getSection()
    {
        return section;
    }

    public void setSection(Section section)
    {
        this.section = section;
    }

    @OneToOne
    public Fiche getFiche()
    {
        return fiche;
    }

    public void setFiche(Fiche fiche)
    {
        this.fiche = fiche;
    }
}
