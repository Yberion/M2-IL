package TP2.jpa.kanban.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Kanban")
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
    @XmlElement(name = "id")
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "kanban")
    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "section")
    public List<Section> getSections()
    {
        return sections;
    }

    public void setSections(List<Section> sections)
    {
        this.sections = sections;
    }

    @XmlElement(name = "name")
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
