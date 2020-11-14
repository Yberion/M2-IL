package fr.brandon.tp3.part3.web.rest.v1.api.kanban;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.domain.kanban.Section;
import fr.brandon.tp3.part3.repository.kanban.KanbanRepository;
import fr.brandon.tp3.part3.service.dto.kanban.KanbanDTO;
import fr.brandon.tp3.part3.service.mapper.kanban.KanbanMapper;

@RestController
@RequestMapping("/api/v1/kanban")
public class KanbanResource
{
    private final KanbanRepository kanbanRepository;
    private final KanbanMapper kanbanMapper;

    public KanbanResource(KanbanRepository kanbanRepository, KanbanMapper kanbanMapper)
    {
        super();
        this.kanbanRepository = kanbanRepository;
        this.kanbanMapper = kanbanMapper;
    }

    @GetMapping("/get/{id}")
    @ResponseBody // Return Kanban formated to JSON
    public KanbanDTO getKanbanById(@PathVariable("id") Long id)
    {
        Optional<Kanban> kanban = kanbanRepository.findById(id);

        if (kanban.isPresent())
        {
            return kanbanMapper.toKanbanDTO(kanban.get());
        }
        return new KanbanDTO();
    }
    
    @GetMapping("/get/")
    @ResponseBody // Return Kanban formated to JSON
    public List<KanbanDTO> getAllKanban()
    {
        List<Kanban> kanban = kanbanRepository.findAll();

        if (!kanban.isEmpty())
        {
            return kanbanMapper.toKanbanDTO(kanban);
        }
        return new ArrayList<>();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addKanban(@RequestBody KanbanDTO kanbanDTO)
    {
        Kanban kanban = kanbanMapper.toKanban(kanbanDTO);

        if (kanban.getSections() != null)
        {

            for (Section section : kanban.getSections())
            {
                section.setKanban(kanban);
            }
        }
        kanbanRepository.save(kanban);
        return "Kanban added";
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public KanbanDTO updateKanbanById(@PathVariable("id") Long id, @RequestBody KanbanDTO kanbanDTO)
    {
        Optional<Kanban> kanban = kanbanRepository.findById(id);

        if (kanban.isPresent())
        {
            kanbanRepository.save(kanbanMapper.updateKanbanFromDTO(kanbanDTO, kanban.get()));
            return kanbanMapper.toKanbanDTO(kanban.get());
        }
        return new KanbanDTO();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKanbanById(@PathVariable("id") Long id)
    {
        Optional<Kanban> kanban = kanbanRepository.findById(id);

        if (kanban.isPresent())
        {
            kanbanRepository.deleteById(id);
            return "Kanban removed";
        }
        return "Kanban not found";
    }
}
