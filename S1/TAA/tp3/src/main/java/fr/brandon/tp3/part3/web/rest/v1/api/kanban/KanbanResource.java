package fr.brandon.tp3.part3.web.rest.v1.api.kanban;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.repository.kanban.KanbanRepository;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;
import fr.brandon.tp3.part3.service.dto.kanban.converter.api.KanbanToKanbanDTO;
import fr.brandon.tp3.part3.service.dto.kanban.converter.implementation.KanbanToKanbanDTOImpl;

@RestController
@RequestMapping("/api/v1/kanban")
public class KanbanResource
{
    private final KanbanRepository kanbanRepository;

    public KanbanResource(KanbanRepository kanbanRepository)
    {
        super();
        this.kanbanRepository = kanbanRepository;
    }

    @GetMapping("/get/{id}")
    @ResponseBody // Return Kanban formated to JSON
    public KanbanDTO getKanbanById(@PathVariable("id") Long id)
    {
        KanbanToKanbanDTO kanbanToKanbanDTO = new KanbanToKanbanDTOImpl();
        Optional<Kanban> kanban = kanbanRepository.findById(id);

        if (kanban.isPresent())
        {
            return kanbanToKanbanDTO.convert(kanban.get());
        }
        return new KanbanDTO();
    }

    //@GetMapping("/add")
    //@ResponseBody
    //public String addKanban()
    @PostMapping("/add")
    @ResponseBody
    public String addKanban(@RequestBody KanbanDTO kanbanDTO)
    {
        Kanban kanban = new Kanban();
        //kanban.setId(kanbanDTO.getId());
        kanban.setName(kanbanDTO.getName());
        //Pas de DTO pour Section, un cas pour l'exemple suffit
        kanbanRepository.save(kanban);
        return "Kanban added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKanbanById(@PathVariable("id") Long id)
    {
        kanbanRepository.deleteById(id);
        return "Kanban removed";
    }
}
