package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Section;
import fr.brandon.tp3.part3.service.dto.kanban.SectionDTO;

@Mapper(componentModel = "spring", uses = { KanbanMapper.class, CarteMapper.class })
public interface SectionMapper
{
    SectionMapper MAPPER = Mappers.getMapper(SectionMapper.class);

    Section toSection(SectionDTO sectionDTO);

    @InheritInverseConfiguration
    SectionDTO toSectionDTO(Section section);
}
