package TP2.kanban.dao;

import TP2.kanban.dao.generic.AbstractJpaDao;
import TP2.kanban.metier.Kanban;
import TP2.kanban.metier.Section;

public final class SectionDAO extends AbstractJpaDao<String, Section>
{
    public SectionDAO()
    {
        super(Section.class);
    }
    
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
    
    /*
     * Il faudrait passer en paramètre l'id du kanban, au vu de mon implémentation actuelle
    public Section findByName(String name)
    {
        return entityManager.createQuery("select e from Section as e where e.name = :name", Section.class).setParameter("name", name).getSingleResult();
    }
    
    public void deleteByName(String name)
    {
        delete(findByName(name));
    }
    */
}
