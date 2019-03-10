package br.com.teste.model.repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.teste.model.entity.FuncaoUsuarioEntity;
import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;


public class FuncaoUsuarioRepository extends EntityCrudRepository<FuncaoUsuarioEntity, Integer>{

	@Override
	public FuncaoUsuarioEntity findById(Integer id) throws BusinessException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FuncaoUsuarioEntity> query = criteriaBuilder.createQuery(FuncaoUsuarioEntity.class);
		Root<FuncaoUsuarioEntity> root = query.from(FuncaoUsuarioEntity.class);
		Path<Integer> oid = root.<Integer>get("id");
		Predicate idEqual = criteriaBuilder.equal(oid,id);
		
		query.where(idEqual);
		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessException("msg.exception.entity.buscar.noResult",CausesException.NO_RESULT, String.valueOf(id));
		}
	}
}
