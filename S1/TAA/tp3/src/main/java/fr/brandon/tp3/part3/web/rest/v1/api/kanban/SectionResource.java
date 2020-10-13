package fr.brandon.tp3.part3.web.rest.v1.api.kanban;

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
import fr.brandon.tp3.part3.repository.kanban.SectionRepository;
import fr.brandon.tp3.part3.service.dto.kanban.SectionDTO;
import fr.brandon.tp3.part3.service.mapper.kanban.SectionMapper;

@RestController
@RequestMapping("/api/v1/section")
public class SectionResource
{
    private final SectionRepository sectionRepository;
    private final KanbanRepository kanbanRepository;
    private final SectionMapper sectionMapper;

    public SectionResource(SectionRepository sectionRepository, KanbanRepository kanbanRepository,
            SectionMapper sectionMapper)
    {
        super();
        this.sectionRepository = sectionRepository;
        this.kanbanRepository = kanbanRepository;
        this.sectionMapper = sectionMapper;
    }

    @GetMapping("/get/{id}")
    @ResponseBody // Return Section formated to JSON
    public SectionDTO getSectionById(@PathVariable("id") Long id)
    {
        Optional<Section> section = sectionRepository.findById(id);

        if (section.isPresent())
        {
            return sectionMapper.toSectionDTO(section.get());
        }
        return new SectionDTO();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addSection(@RequestBody SectionDTO sectionDTO)
    {
        Section section = sectionMapper.toSection(sectionDTO);
        Optional<Kanban> kanban = kanbanRepository.findById(sectionDTO.getKanbanId());

        if (kanban.isPresent())
        {
            section.setKanban(kanban.get());
        }
        sectionRepository.save(section);
        return "Section added";
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public SectionDTO updateSectionById(@PathVariable("id") Long id, @RequestBody SectionDTO sectionDTO)
    {
        Optional<Section> section = sectionRepository.findById(id);

        if (section.isPresent())
        {
            Optional<Kanban> kanban = kanbanRepository.findById(sectionDTO.getKanbanId());

            if (kanban.isPresent())
            {
                section.get().setKanban(kanban.get());
            }
            sectionRepository.save(sectionMapper.updateSectionFromDTO(sectionDTO, section.get()));
            return sectionMapper.toSectionDTO(section.get());
        }
        return new SectionDTO();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSectionById(@PathVariable("id") Long id)
    {
        Optional<Section> section = sectionRepository.findById(id);

        if (section.isPresent())
        {
            sectionRepository.deleteById(id);
            return "Section removed";
        }
        return "Section not found";
    }
}
