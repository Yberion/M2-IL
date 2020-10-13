package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import fr.brandon.tp3.part3.domain.kanban.Section;
import fr.brandon.tp3.part3.service.dto.kanban.SectionDTO;

@Mapper(componentModel = "spring", uses = {
        CarteMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SectionMapper
{
    @Mapping(target = "id", ignore = true)
    Section toSection(SectionDTO sectionDTO);

    @InheritInverseConfiguration(name = "toSection")
    @Mapping(source = "kanban.id", target = "kanbanId")
    SectionDTO toSectionDTO(Section section);

    @Mapping(target = "id", ignore = true)
    Section updateSectionFromDTO(SectionDTO sectionDTO, @MappingTarget Section section);
}
