package TP2.kanban;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerHelper
{
    private static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;
    
    static
    {
        emf = Persistence.createEntityManagerFactory("dev");
        threadLocal = new ThreadLocal<>();
    }

    private EntityManagerHelper()
    {
        throw new IllegalStateException("Utility class");
    }

    public static EntityManager getEntityManager()
    {
        EntityManager em = threadLocal.get();

        if (em == null)
        {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager()
    {
        EntityManager em = threadLocal.get();

        if (em != null)
        {
            em.close();
            threadLocal.remove();
        }
    }

    public static void closeEntityManagerFactory()
    {
        emf.close();
    }

    public static void beginTransaction()
    {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback()
    {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit()
    {
        getEntityManager().getTransaction().commit();
    }
}
