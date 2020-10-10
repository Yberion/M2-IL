package fr.brandon.tp3.part3.service.dto.kanban.converter.api;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;

// Not used anymore
public interface KanbanToKanbanDTO
{
    KanbanDTO convert(Kanban kanban);
}
