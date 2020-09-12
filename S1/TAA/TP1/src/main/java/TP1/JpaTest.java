package TP1;

import java.util.List;

import TP1.domain.Kanban;

public class JpaTest
{
    public static void main(String[] args)
    {
        EntityManagerHelper.beginTransaction();

        try
        {
            createKanban();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        EntityManagerHelper.commit();

        listKanbans();
        
        EntityManagerHelper.closeEntityManager();
        EntityManagerHelper.closeEntityManagerFactory();
    }

    private static void createKanban()
    {
        int numOfKanban = EntityManagerHelper.getEntityManager().createQuery("Select a From Kanban a", Kanban.class).getResultList().size();

        if (numOfKanban == 0)
        {
            Kanban kanban = new Kanban("TAA");
            EntityManagerHelper.getEntityManager().persist(kanban);
        }
    }

    private static void listKanbans()
    {
        List<Kanban> kanbans = EntityManagerHelper.getEntityManager().createQuery("Select a From Kanban a", Kanban.class).getResultList();
        System.out.println("num of kanbans:" + kanbans.size());

        for (Kanban next : kanbans)
        {
            System.out.println("next kanban: " + next.getName());
        }
    }
}
