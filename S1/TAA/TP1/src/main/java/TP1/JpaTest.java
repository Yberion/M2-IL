package TP1;

import java.util.List;

import TP1.dao.KanbanDAO;
import TP1.dao.SectionDAO;

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
