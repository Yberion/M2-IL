package fr.brandon.tp3.part3.service.dto.kanban;

import java.util.List;

import lombok.Data;

@Data
public class UtilisateurDTO
{
    private long id;
    private String name;
    private List<FicheDTO> fiches;
}
