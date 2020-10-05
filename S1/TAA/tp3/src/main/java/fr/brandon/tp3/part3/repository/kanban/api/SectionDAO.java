package fr.brandon.tp3.part3.repository.kanban.api;

import fr.brandon.tp3.part3.domain.kanban.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionDAO extends JpaRepository<String, Section>
{
    void createDefaultSections(long kanbanId);
}
