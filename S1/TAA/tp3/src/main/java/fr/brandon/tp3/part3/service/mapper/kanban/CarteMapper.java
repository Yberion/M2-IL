package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Carte;
import fr.brandon.tp3.part3.service.dto.kanban.CarteDTO;

@Mapper(componentModel = "spring", uses = { SectionMapper.class, TagMapper.class })
public interface CarteMapper
{
    CarteMapper MAPPER = Mappers.getMapper(CarteMapper.class);

    Carte toCarte(CarteDTO carte);

    @InheritInverseConfiguration
    CarteDTO toCarteDTO(Carte carte);
}
