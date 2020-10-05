package fr.brandon.tp3.part3.service.kanban.converter.api;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.service.kanban.KanbanDTO;

public interface KanbanToKanbanDTO
{
    KanbanDTO convert(Kanban kanban);
}
