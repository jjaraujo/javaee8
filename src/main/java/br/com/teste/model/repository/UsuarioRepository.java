package br.com.teste.model.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.teste.model.entity.UsuarioEntity;
import br.com.teste.util.enuns.PerfilAcessoConverter;
import br.com.teste.util.enuns.SituacaoConverter;
import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;


public class UsuarioRepository extends EntityCrudRepository<UsuarioEntity, String> {
	
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAllByFilter(String nome, String situacao,
			Integer perfil, Integer offset, Integer limite){
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT u FROM UsuarioEntity u WHERE 1=1 ");
		
		addFilter(nome, situacao, perfil, queryString);
				
		Query query = entityManager.createQuery(queryString.toString());
		if(offset != null) {
			query.setFirstResult(offset);
		}		
		
		if(limite != null) {
			query.setMaxResults(limite);
		}
		addValuesFilter(nome, situacao, perfil, query);
		
		return query.getResultList();
		
	}
	
	public Long countByFilter(String nome, String situacao, Integer perfil) {
		

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT count(u) as cont FROM UsuarioEntity u WHERE 1=1 ");
		
		addFilter(nome, situacao, perfil, queryString);
				
		TypedQuery<Long> query = entityManager.createQuery(queryString.toString(), Long.class);
		
		addValuesFilter(nome, situacao, perfil, query);
		
		return query.getSingleResult();
	}

	@Override
	public UsuarioEntity findById(String id) throws BusinessException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioEntity> query = criteriaBuilder.createQuery(UsuarioEntity.class);
		Root<UsuarioEntity> root = query.from(UsuarioEntity.class);
		Path<String> oid = root.<String>get("cpf");
		Predicate idEqual = criteriaBuilder.equal(oid,id);
		
		query.where(idEqual);
		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessException("msg.exception.entity.buscar.noResult",CausesException.NO_RESULT, id);
		}
	}

	
	private void addValuesFilter(String nome, String situacao, Integer perfil, Query query) {
		if(nome != null) {
			query.setParameter("nome", "%"+nome+"%");
		}
		
		if(situacao != null) {
			query.setParameter("situacao", new SituacaoConverter().convertToEntityAttribute(situacao));
		}
		
		if(perfil != null) {
			query.setParameter("perfil", new PerfilAcessoConverter().convertToEntityAttribute(perfil));
		}
	}
	
	private void addFilter(String nome, String situacao, Integer perfil, StringBuilder query) {
		if(nome != null) {
			query.append(" AND nome like :nome");
		}
		
		if(situacao != null) {
			query.append(" AND situacao = :situacao");
		}
		
		if(perfil != null) {
			query.append(" AND perfil = :perfil");
		}
	}
}

