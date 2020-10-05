package fr.brandon.tp3.part3.repository.kanban.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.brandon.tp3.part3.domain.kanban.Kanban;

@Repository
public interface KanbanDAO extends JpaRepository<Long, Kanban>
{
    List<Long> createKanbans();
    
    List<Kanban> findAllByName(String name);
    
    void deleteByName(String name);

    void displayKanbans();
}
