package br.com.teste.model.service;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import br.com.teste.model.entity.FuncaoUsuarioEntity;
import br.com.teste.model.repository.FuncaoUsuarioRepository;

@Service
public class FuncaoUsuarioService extends EntityService<FuncaoUsuarioEntity, Integer> {

	private static final long serialVersionUID = 1L;
	
	private FuncaoUsuarioRepository funcaoUsuarioRepository;

	@Inject
	public FuncaoUsuarioService(FuncaoUsuarioEntity entity, FuncaoUsuarioRepository repository) {
		super(entity, repository);
		this.funcaoUsuarioRepository = repository;
	}

	
}
