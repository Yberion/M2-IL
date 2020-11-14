package fr.brandon.tp3.part3.service.mapper.kanban;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;

// nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS -> to check if the field is not null and then set it to the new value (for example on an update)
// nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE -> if the field is null we ask to not set it to null
@Mapper(componentModel = "spring", uses = {
        SectionMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface KanbanMapper
{
    @Mapping(target = "id", ignore = true)
    Kanban toKanban(KanbanDTO kanbanDTO);

    @InheritInverseConfiguration(name = "toKanban")
    KanbanDTO toKanbanDTO(Kanban kanban);

    List<KanbanDTO> toKanbanDTO(List<Kanban> kanban);

    @Mapping(target = "id", ignore = true)
    Kanban updateKanbanFromDTO(KanbanDTO kanbanDTO, @MappingTarget Kanban kanban);
}
