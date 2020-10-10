package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.brandon.tp3.part3.domain.kanban.Utilisateur;
import fr.brandon.tp3.part3.service.dto.kanban.UtilisateurDTO;

@Mapper(componentModel = "spring", uses = { FicheMapper.class })
public interface UtilisateurMapper
{
    UtilisateurMapper MAPPER = Mappers.getMapper(UtilisateurMapper.class);

    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);

    @InheritInverseConfiguration
    UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur);
}
