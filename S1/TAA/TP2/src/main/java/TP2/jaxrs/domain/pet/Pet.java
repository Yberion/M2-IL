package TP2.jaxrs.domain.pet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.models.tags.Tag;

@XmlRootElement(name = "Pet")
public class Pet
{
    private long id;
    private String name;
    private List<Tag> tags = new ArrayList<>();

    @XmlElement(name = "id")
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }
}