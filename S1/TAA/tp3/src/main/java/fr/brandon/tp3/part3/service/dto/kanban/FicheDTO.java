package fr.brandon.tp3.part3.service.dto.kanban;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class FicheDTO
{
    private long id;
    private String libelle;
    private Date dateButoire;
    private List<UtilisateurDTO> utilisateurs;
    // En jour
    private int tempsEstimation;
    private List<TagDTO> tags;
    private String lieu;
    private String url;
    private String hashGit;
    private String note;
    private CarteDTO carte;
}
