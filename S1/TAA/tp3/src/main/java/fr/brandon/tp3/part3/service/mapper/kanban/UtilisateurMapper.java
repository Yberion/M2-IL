package fr.brandon.tp3.part3.service.mapper.kanban;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.brandon.tp3.part3.domain.kanban.Utilisateur;
import fr.brandon.tp3.part3.service.dto.kanban.UtilisateurDTO;

@Mapper(componentModel = "spring", uses = {
        FicheMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UtilisateurMapper
{
    @Mapping(target = "id", ignore = true)
    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);

    @InheritInverseConfiguration(name = "toUtilisateur")
    UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur);

    @Mapping(target = "id", ignore = true)
    Utilisateur updateUtilisateurFromDTO(UtilisateurDTO utilisateurDTO, @MappingTarget Utilisateur utilisateur);
}
