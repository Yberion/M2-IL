package TP1.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kanban
{
    private long id;
    private List<Section> sections;
    private String name;

    public Kanban()
    {
        super();
        this.sections = new ArrayList<>();
        this.name = "My Kanban";
        
        createDefaultSections();
    }

    public Kanban(String name)
    {
        super();
        this.sections = new ArrayList<>();
        this.name = name;
        
        createDefaultSections();
    }
    
    private void createDefaultSections()
    {
        Section enAttente = new Section("En attente", this);
        Section enCours = new Section("En cours", this);
        Section realise = new Section("Réalisé", this);
        
        //enAttente.setKanban(this);
        //enCours.setKanban(this);
        //realise.setKanban(this);
        
        this.sections.add(enAttente);
        this.sections.add(enCours);
        this.sections.add(realise);
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

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "kanban")
    public List<Section> getSections()
    {
        return sections;
    }

    public void setSections(List<Section> sections)
    {
        this.sections = sections;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public void addSection(String name)
    {
        this.sections.add(new Section(name, this));
    }
    
    public void removeSection(String name)
    {
        this.sections.remove(new Section(name, this));
    }
}
