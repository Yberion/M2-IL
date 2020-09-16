package TP2.jaxrs.dto.kanban;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Kanban")
public class KanbanDTO
{
    private long id;
    // Should be List<SectionDTO>
    private List<String> sections;
    private String name;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public List<String> getSections()
    {
        return sections;
    }

    public void setSections(List<String> sections)
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
}
