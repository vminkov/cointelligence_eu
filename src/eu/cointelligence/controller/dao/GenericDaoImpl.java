package eu.cointelligence.controller.dao;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.cointelligence.model.Transaction;

@Stateless
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext(unitName = "cointelligence")
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
       // queryString.append(this.getQueryClauses(params, null));

        final Query query = this.em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }

    @Override
    public T create(final T t) {
        this.em.persist(t);
        return t;
    } 

    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    @Override
    public T update(final T t) {
        return this.em.merge(t);    
    }
    
    @Override
    public List<T> getAll() {
		Query q = em.createQuery("SELECT t from " + type.getName() + " as t LIMIT 0, 1000");
		
		return (List<T>) q.getResultList();

    }
}