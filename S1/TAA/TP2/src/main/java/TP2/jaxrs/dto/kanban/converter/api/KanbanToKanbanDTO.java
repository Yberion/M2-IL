package TP2.jaxrs.dto.kanban.converter.api;

import TP2.jaxrs.dto.kanban.KanbanDTO;
import TP2.jpa.kanban.domain.Kanban;

public interface KanbanToKanbanDTO
{
    KanbanDTO convert(Kanban kanban);
}
