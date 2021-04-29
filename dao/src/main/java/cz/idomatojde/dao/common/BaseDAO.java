package cz.idomatojde.dao.common;

import cz.idomatojde.entity.base.IEntity;

import java.util.List;

/**
 * Base interface for all DAOs
 *
 * @param <TEntity> Any entity
 * @author Michal Hazdra
 */
public interface BaseDAO<TEntity extends IEntity> {


    /**
     * Common functionality to persist a new entity
     *
     * @param entity the entity to persist
     * @return the ID of the newly created entity
     */
    long create(TEntity entity);


    /**
     * Common functionality to find all entities
     *
     * @return the list of all entities in the database
     */
    List<TEntity> findAll();


    /**
     * Common functionality to obtain an entity by its id
     *
     * @param id the entity identifier
     * @return the entity
     */
    TEntity getById(Long id);

    /**
     * Common functionality to delete an entity from the database
     *
     * @param entity the entity to delete
     */
    void delete(TEntity entity);
}
