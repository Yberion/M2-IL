package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.brandon.tp3.part3.domain.kanban.Carte;
import fr.brandon.tp3.part3.service.dto.kanban.CarteDTO;

@Mapper(componentModel = "spring", uses = { SectionMapper.class,
        TagMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarteMapper
{
    @Mapping(target = "id", ignore = true)
    Carte toCarte(CarteDTO carte);

    @InheritInverseConfiguration(name = "toCarte")
    CarteDTO toCarteDTO(Carte carte);

    @Mapping(target = "id", ignore = true)
    Carte updateCarteFromDTO(CarteDTO carteDTO, @MappingTarget Carte carte);
}
