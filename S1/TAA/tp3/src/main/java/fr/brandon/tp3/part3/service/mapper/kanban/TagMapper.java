package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Tag;
import fr.brandon.tp3.part3.service.dto.kanban.TagDTO;

@Mapper(componentModel = "spring", uses = { FicheMapper.class })
public interface TagMapper
{
    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);

    Tag toTag(TagDTO tagDTO);

    @InheritInverseConfiguration
    TagDTO toTagDTO(Tag tag);
}
