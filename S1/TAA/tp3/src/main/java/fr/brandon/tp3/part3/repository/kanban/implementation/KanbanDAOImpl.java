package fr.brandon.tp3.part3.repository.kanban.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.brandon.tp3.part3.domain.kanban.Kanban;
import fr.brandon.tp3.part3.repository.kanban.api.KanbanDAO;

public final class KanbanDAOImpl implements KanbanDAO
{
    public List<Long> createKanbans()
    {
        int numOfKanban = EntityManagerHelper.getEntityManager().createQuery("Select a From Kanban a", Kanban.class).getResultList().size();

        if (numOfKanban == 0)
        {
            List<Kanban> kanbans = new ArrayList<>();
            
            kanbans.add(new Kanban("TAA1"));
            kanbans.add(new Kanban("TAA2"));
            kanbans.add(new Kanban("TAA3"));
            
            saveMany(kanbans);
            
            return kanbans.stream().map(Kanban::getId).collect(Collectors.toList());
        }
        
        return new ArrayList<>();
    }
    
    public List<Kanban> findAllByName(String name)
    {
        return entityManager.createQuery("select e from Kanban as e where e.name = :name", Kanban.class).setParameter("name", name).getResultList();
    }
    
    public void deleteByName(String name)
    {
        for (Kanban kanban : findAllByName(name))
        {
            delete(kanban);
        }
    }

    public void displayKanbans()
    {
        List<Kanban> kanbans = findAll();
        
        System.out.println("num of kanbans: " + kanbans.size());

        for (Kanban next : kanbans)
        {
            System.out.println("next kanban: " + next.getName());
        }
        
        /*
        deleteByName("TAA2");
        
        List<Kanban> kanbans2 = findAll();
        
        System.out.println("num of kanbans: " + kanbans2.size());

        for (Kanban next : kanbans2)
        {
            System.out.println("next kanban: " + next.getName());
        }
        */
    }
}
