package fr.brandon.tp3.part3.web.rest.v1.api.kanban;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.repository.kanban.api.KanbanDAO;
import fr.brandon.tp3.part3.service.kanban.KanbanDTO;
import fr.brandon.tp3.part3.service.kanban.converter.api.KanbanToKanbanDTO;
import fr.brandon.tp3.part3.service.kanban.converter.implementation.KanbanToKanbanDTOImpl;

@RestController
@RequestMapping("/api/v1/kanban")
public class KanbanResource
{
    private final KanbanDAO kanbanDAO;

    public KanbanResource(KanbanDAO kanbanDAO) {
		super();
		this.kanbanDAO = kanbanDAO;
	}

	@GetMapping("/get/{id}")
    @ResponseBody // Return Kanban formated to JSON
    public KanbanDTO getKanbanById(@PathVariable("id") Long id)
    {
        KanbanToKanbanDTO kanbanToKanbanDTO = new KanbanToKanbanDTOImpl();
        return kanbanToKanbanDTO.convert(kanbanDAO.findById(id).get()); 
    }

	
	//@GetMapping("/add")
    //@ResponseBody
    //public String addKanban()
    @PostMapping("/add")
    @ResponseBody
    public String addKanban(@RequestBody KanbanDTO kanban)
    {
        // Creer un nouveau Kanban ici et le peupler ici ? -> oui
    	
        kanbanDAO.save(new Kanban());
        return "Kanban added";
    }

    @DeleteMapping("/remove/{id}")
    public String removeKanbanById(@PathVariable("id") Long id)
    {
        kanbanDAO.deleteById(id);
        return "Kanban removed";
    }
}
