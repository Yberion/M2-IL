package fr.brandon.tp3.part3.service.dto.kanban;

import lombok.Data;

@Data
public class CarteDTO
{
    private long id;
    private SectionDTO section;
    private FicheDTO fiche;
}
