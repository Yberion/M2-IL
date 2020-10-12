package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;

// nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS -> to check if the field is not null and then set it to the new value (for example on an update)
// nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE -> if the field is null we ask to not set it to null
@Mapper(componentModel = "spring", uses = {
        SectionMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface KanbanMapper
{
    KanbanMapper MAPPER = Mappers.getMapper(KanbanMapper.class);

    Kanban toKanban(KanbanDTO kanbanDTO);

    @InheritInverseConfiguration(name = "toKanban")
    KanbanDTO toKanbanDTO(Kanban kanban);

    @Mapping(target = "id", ignore = true)
    Kanban updateKanbanFromDTO(KanbanDTO kanbanDTO, @MappingTarget Kanban kanban);
}
