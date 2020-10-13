package fr.brandon.tp3.part3.service.dto.kanban;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SectionDTO
{
    private long id;
    private String name;
    private long kanbanId;
    private List<CarteDTO> cartes;

    public SectionDTO()
    {
        this.cartes = new ArrayList<>();
    }
}
