/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlobranco.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brais
 * @param <T>
 */
public abstract class AbstractFacade<T> implements InterfaceRemote<T> {
    
    private static final Logger LOG = Logger.getLogger(AbstractFacade.class.getName());

    private static final long serialVersionUID = 1L;

    private Class<T> entityClass;
    
    @PersistenceContext(unitName = "test_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        LOG.log(Level.INFO, "Inserting object: {0}", entity);
        getEntityManager().persist(entity);
    }

    @Override
    public void edit(T entity) {
        LOG.log(Level.INFO, "Editing object: {0}", entity);
        getEntityManager().merge(entity);
    }

    @Override
    public void remove(T entity) {
        LOG.log(Level.INFO, "Removing object: {0}", entity);
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
