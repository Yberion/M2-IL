package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.brandon.tp3.part3.domain.kanban.Fiche;
import fr.brandon.tp3.part3.service.dto.kanban.FicheDTO;

@Mapper(componentModel = "spring", uses = {
        UtilisateurMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FicheMapper
{
    @Mapping(target = "id", ignore = true)
    Fiche toFiche(FicheDTO ficheDTO);

    @InheritInverseConfiguration(name = "toFiche")
    FicheDTO toFicheDTO(Fiche fiche);

    @Mapping(target = "id", ignore = true)
    Fiche updateFicheFromDTO(FicheDTO ficheDTO, @MappingTarget Fiche fiche);
}
