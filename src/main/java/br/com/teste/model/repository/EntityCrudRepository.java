package br.com.teste.model.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import br.com.teste.model.entity.BaseEntity;
import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;


public abstract class EntityCrudRepository<T extends BaseEntity, I>{
	
	@Inject
	protected EntityManager entityManager;
	Logger LOGGER = Logger.getLogger(EntityCrudRepository.class.getName());
	
	public void insert(T entity) throws BusinessException {
		if(entity == null) {
			throw new BusinessException("msg.exception.entity.incluir.nulo",CausesException.OTHER);
		}
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		}catch (PersistenceException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException("msg.exception.entity.incluir.banco", e,entity.getClass().getName());
		}
	}
	
	public BaseEntity update(BaseEntity entity) throws BusinessException {
		if(entity == null) {
			throw new BusinessException("msg.exception.entity.editar.nulo", CausesException.OTHER);
		}
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new BusinessException("msg.exception.entity.editar.banco", CausesException.OTHER);
		}
	}
	
	public boolean delete(T entity) throws BusinessException {
		if(entity == null) {
			throw new BusinessException("msg.exception.entity.excluir.nulo", CausesException.OTHER);
		}
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			return true;
		}catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			LOGGER.error(e.getMessage());
			throw new BusinessException("msg.exception.entity.excluir.nulo", CausesException.OTHER);
		}
	}
	
	public List<T> findAll(Class<T> classType) throws BusinessException{
		
		if(classType == null) {
			throw new BusinessException("msg.exception.entity.buscar.classTypeNulo", CausesException.OTHER);
		}
		
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classType);
		query.select(query.from(classType));
		List<T> lista = entityManager.createQuery(query).getResultList();
		return lista;
	}
	
	public abstract T findById(I id)  throws BusinessException;
}
