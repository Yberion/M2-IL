package TP1.dao.generic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import TP1.EntityManagerHelper;

// K = clé primaire
// T = Class
public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T>
{
    private Class<T> clazz;
    protected EntityManager entityManager;

    public AbstractJpaDao(Class<T> clazz)
    {
        this.entityManager = EntityManagerHelper.getEntityManager();
        this.clazz = clazz;
    }

    public void setClazz(Class<T> clazzToSet)
    {
        this.clazz = clazzToSet;
    }

    public T findOne(K id)
    {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll()
    {
        return entityManager.createQuery("select e from " + clazz.getName() + " as e", clazz).getResultList();
    }

    public void save(T entity)
    {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }
    
    public void saveMany(final List<T> entities)
    {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        
        for (T entity : entities)
        {
            entityManager.persist(entity);
        }
        
        transaction.commit();
    }

    public T update(final T entity)
    {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        T res = entityManager.merge(entity);
        transaction.commit();
        return res;
    }

    public void delete(T entity)
    {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
    }

    public void deleteById(K entityId)
    {
        T entity = findOne(entityId);
        delete(entity);
    }
}