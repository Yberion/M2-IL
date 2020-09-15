package TP2.jpa.kanban.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<K, T extends Serializable>
{
    T findOne(final K id);

    List<T> findAll();

    void save(final T entity);
    
    void saveMany(final List<T> entities);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final K entityId);
}