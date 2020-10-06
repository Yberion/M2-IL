package fr.brandon.tp3.part3.domain.kanban;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kanban implements Serializable
{
    private static final long serialVersionUID = -7571173987535309015L;
    private long id;
    private List<Section> sections;
    private String name;

    public Kanban()
    {
        super();
        this.sections = new ArrayList<>();
        this.name = "My Kanban";
    }

    public Kanban(String name)
    {
        super();
        this.sections = new ArrayList<>();
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "kanban")
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
