package br.com.teste.model.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import br.com.teste.controller.payload.input.SituacaoInput;
import br.com.teste.model.entity.UsuarioEntity;
import br.com.teste.model.repository.UsuarioRepository;
import br.com.teste.util.ValidarCpf;
import br.com.teste.util.enuns.Situacao;
import br.com.teste.util.enuns.SituacaoConverter;
import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;

@Service
@Named
public class UsuarioService extends EntityService<UsuarioEntity, String>{

	private static final long serialVersionUID = 1L;
	
	@Inject FuncaoUsuarioService funcaoUsuarioService;
	
	private UsuarioRepository usuarioRepository;

	@Inject
	public UsuarioService(UsuarioEntity classType, UsuarioRepository usuarioRepository) {
		super(classType, usuarioRepository);
		this.usuarioRepository = usuarioRepository;
	}

	public List<UsuarioEntity> findAllByFilter(String nome, String situacao,
			Integer perfil, Integer pagina, Integer limite) {
		
		if(pagina != null && limite != null) {
			 pagina = (pagina * limite) - limite;
		}
		
		return usuarioRepository.findAllByFilter(nome, situacao, perfil, pagina, limite);
	}
			
	public void insert(UsuarioEntity entity ) throws BusinessException {
		
		try {
			UsuarioEntity user = usuarioRepository.findById(entity.getId());
			if(user != null)
				throw new BusinessException("msg.MN034", CausesException.ENTITY_DUPLICATED);
			
		} catch (BusinessException e) {
			if(e.getEnumCause() != CausesException.NO_RESULT) {
				throw e;
			}
		}
		if(!ValidarCpf.validar(entity.getCpf())) {
			throw new BusinessException("msg.MN035", CausesException.CPF_INVALIDO);
		}
		usuarioRepository.insert(entity);
	}
	
	public void updateSituacao(String cpf, SituacaoInput input) throws BusinessException {
		try{
			UsuarioEntity usuarioEntity = findById(cpf);
			Situacao situacao = new SituacaoConverter().convertToEntityAttribute(input.getSituacao());
			usuarioEntity.setSituacao(situacao);
			update(usuarioEntity);
		}catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException("msg.exception.entity.update.internal", CausesException.OTHER);
		}
	}
	
	public Long countByFilter(String nome, String situacao, Integer perfil) {
		return usuarioRepository.countByFilter(nome, situacao, perfil);
	}
	
	public void delete(String cpf) throws BusinessException {
		UsuarioEntity entity = findById(cpf);
		repository.delete(entity);
	}
}
