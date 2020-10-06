package fr.brandon.tp3.part3.repository.kanban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.brandon.tp3.part3.domain.kanban.Carte;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Long>
{
}
