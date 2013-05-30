package eu.cointelligence.controller.dao;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		Query q = em.createQuery("SELECT t from " + type.getName() + " as t");
		
		
		return (List<T>) q.getResultList();

    }
    
    @Override
    public List<T> filter(String fieldName, Object value) {
    	CriteriaBuilder cb = this.em.getCriteriaBuilder();
    	CriteriaQuery criteriaQuery = cb.createQuery();
    	Root<T> tRoot = criteriaQuery.from(this.type);
    	
    	criteriaQuery.select(tRoot);
    	
    	Predicate predicate = cb.equal(tRoot.get(fieldName), value);
    	criteriaQuery.where(predicate);
    	
    	Query q  = this.em.createQuery(criteriaQuery);
    	
    	return q.getResultList();
    }
}