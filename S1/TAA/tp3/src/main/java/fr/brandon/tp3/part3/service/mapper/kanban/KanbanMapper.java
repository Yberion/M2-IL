package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;

@Mapper(componentModel = "spring", uses = { SectionMapper.class })
public interface KanbanMapper
{
    KanbanMapper MAPPER = Mappers.getMapper(KanbanMapper.class);

    Kanban toKanban(KanbanDTO kanbanDTO);

    @InheritInverseConfiguration
    KanbanDTO toKanbanDTO(Kanban kanban);
}
