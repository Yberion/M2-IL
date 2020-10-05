package fr.brandon.tp3.part3.repository.kanban.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.brandon.tp3.part3.domain.kanban.Kanban;

@Repository
public interface KanbanDAO extends JpaRepository<Kanban, Long>
{
	@Query("select k from Kanban as k where k.name = :name")
    List<Kanban> findAllByName(@Param("name") String name);
    
    void deleteByName(String name);
}
