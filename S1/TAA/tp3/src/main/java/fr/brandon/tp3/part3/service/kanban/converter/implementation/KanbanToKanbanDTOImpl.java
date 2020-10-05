package fr.brandon.tp3.part3.service.kanban.converter.implementation;

import java.util.ArrayList;
import java.util.List;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.domain.kanban.Section;
import fr.brandon.tp3.part3.service.kanban.KanbanDTO;
import fr.brandon.tp3.part3.service.kanban.converter.api.KanbanToKanbanDTO;

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
