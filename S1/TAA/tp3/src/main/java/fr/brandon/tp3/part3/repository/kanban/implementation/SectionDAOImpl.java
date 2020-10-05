package fr.brandon.tp3.part3.repository.kanban.implementation;

import fr.brandon.tp3.part3.domain.kanban.Section;
import fr.brandon.tp3.part3.repository.kanban.api.SectionDAO;

public final class SectionDAOImpl implements SectionDAO
{
    public void createDefaultSections(long kanbanId)
    {
        Kanban kanban = this.entityManager.find(Kanban.class, kanbanId);

        Section enAttente = new Section("En attente", kanban);
        Section enCours = new Section("En cours", kanban);
        Section realise = new Section("Réalisé", kanban);

        kanban.getSections().add(enAttente);
        kanban.getSections().add(enCours);
        kanban.getSections().add(realise);
        
        this.saveMany(kanban.getSections());
    }
}
