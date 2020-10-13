package fr.brandon.tp3.part3.service.dto.kanban;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class KanbanDTO
{
    private long id;
    private List<SectionDTO> sections;
    private String name;

    public KanbanDTO()
    {
        this.sections = new ArrayList<>();
    }
}
