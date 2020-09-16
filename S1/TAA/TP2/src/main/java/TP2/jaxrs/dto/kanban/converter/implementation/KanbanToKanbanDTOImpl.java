package TP2.jaxrs.dto.kanban.converter.implementation;

import java.util.ArrayList;
import java.util.List;

import TP2.jaxrs.dto.kanban.KanbanDTO;
import TP2.jaxrs.dto.kanban.converter.api.KanbanToKanbanDTO;
import TP2.jpa.kanban.domain.Kanban;
import TP2.jpa.kanban.domain.Section;

public class KanbanToKanbanDTOImpl implements KanbanToKanbanDTO
{

    @Override
    public KanbanDTO convert(Kanban kanban)
    {
        KanbanDTO kanbanDTO = new KanbanDTO();
        
        kanbanDTO.setId(kanban.getId());

        // Should be List<SectionDTO>
        List<String> sections = new ArrayList<>();
        
        for (Section section : kanban.getSections())
        {
            // Should be :
            // SectionToSectionDTO sectionToSectionDTO = new SectionToSectionDTOImpl();
            // sections.add(sectionToSectionDTO.convert(section));
            
            sections.add(section.getName());
        }
        
        kanbanDTO.setName(kanban.getName());
        
        return kanbanDTO;
    }
}
