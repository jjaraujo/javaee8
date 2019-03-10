package br.com.teste.model.service;

import java.io.Serializable;
import java.util.List;

import org.jvnet.hk2.annotations.Service;

import br.com.teste.model.entity.BaseEntity;
import br.com.teste.model.entity.Versioned;
import br.com.teste.model.repository.EntityCrudRepository;
import br.com.teste.util.exceptions.BusinessException;

@Service
public class EntityService<T extends BaseEntity, I> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected EntityCrudRepository<T, I> repository;
	
	private Versioned entity;
	
	public EntityService(Versioned entity, EntityCrudRepository<T, I> repository) {
		this.entity = entity;
		this.repository = repository;
	}
	
	public void insert(T entity) throws BusinessException {
		repository.insert(entity);
	}
	
	public BaseEntity update(BaseEntity entity) throws BusinessException {
		return repository.update(entity);
	}
	
	public boolean delete(T entity) throws BusinessException {
		return repository.delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws BusinessException{
		return repository.findAll((Class<T>) entity.getClass());
	}
	
	public T findById(I id) throws BusinessException {
		return repository.findById(id);
	}
}
