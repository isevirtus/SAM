package br.edu.ufcg.embedded.sam.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Generic crud service.
 *
 * @param <T> Type of the entity that this class will work with.
 */
public abstract class CrudService<T> {

    public T createOrUpdate(T entity) {
        entity = getRepository().save(entity);
        return entity;
    }

    void delete(T entity) {
        getRepository().delete(entity);
    }

    public boolean removeById(Integer id) {
        if (getRepository().exists(id)) {
            getRepository().delete(id);
            return true;
        }
        return false;
    }

    public T find(Integer id) {
        return getRepository().findOne(id);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    protected abstract JpaRepository<T, Integer> getRepository();
}
