package TP2.kanban;

import java.util.List;

import TP2.kanban.dao.KanbanDAO;
import TP2.kanban.dao.SectionDAO;

public class JpaTest
{
    public static void main(String[] args)
    {
        KanbanDAO kanbanDAO = new KanbanDAO();
        SectionDAO sectionDAO = new SectionDAO();

        try
        {
            List<Long> kanbanIds = kanbanDAO.createKanbans();
            
            for (Long id : kanbanIds)
            {
                sectionDAO.createDefaultSections(id);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        kanbanDAO.displayKanbans();
        
        EntityManagerHelper.closeEntityManager();
        EntityManagerHelper.closeEntityManagerFactory();
    }
}
