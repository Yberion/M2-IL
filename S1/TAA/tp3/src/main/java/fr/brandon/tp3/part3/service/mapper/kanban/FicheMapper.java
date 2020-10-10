package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Fiche;
import fr.brandon.tp3.part3.service.dto.kanban.FicheDTO;

@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface FicheMapper
{
    FicheMapper MAPPER = Mappers.getMapper(FicheMapper.class);

    Fiche toFiche(FicheDTO ficheDTO);

    @InheritInverseConfiguration
    FicheDTO toFicheDTO(Fiche fiche);
}
