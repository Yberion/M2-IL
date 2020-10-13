package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.brandon.tp3.part3.domain.kanban.Tag;
import fr.brandon.tp3.part3.service.dto.kanban.TagDTO;

@Mapper(componentModel = "spring", uses = {
        FicheMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagMapper
{
    @Mapping(target = "id", ignore = true)
    Tag toTag(TagDTO tagDTO);

    @InheritInverseConfiguration(name = "toTag")
    TagDTO toTagDTO(Tag tag);

    @Mapping(target = "id", ignore = true)
    Tag updateTagFromDTO(TagDTO tagDTO, @MappingTarget Tag tag);
}
