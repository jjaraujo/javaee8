package br.com.teste.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.logging.Logger;

import br.com.teste.controller.payload.input.SituacaoInput;
import br.com.teste.controller.payload.output.CodigosResposta;
import br.com.teste.controller.payload.output.Output;
import br.com.teste.controller.payload.output.usuario.UsuarioFilterOutput;
import br.com.teste.model.entity.UsuarioEntity;
import br.com.teste.model.repository.EntityCrudRepository;
import br.com.teste.model.service.UsuarioService;
import br.com.teste.util.ReadProperties;
import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;

@Path("sistema")
@Named
@RequestScoped
public class UsuarioController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject private UsuarioService usuarioService; 
	@Inject private ReadProperties readProperties;
	
	Logger LOGGER = Logger.getLogger(EntityCrudRepository.class.getName());
	
	@Path("usuarios")
	@GET
	@Produces("application/json; charset=UTF-8")
	public Output buscaUsuarios(@QueryParam("nome")  String nome, 
			@QueryParam("situacao") String situacao, @QueryParam("perfil") Integer perfil,
			@QueryParam("pagina") Integer pagina, @QueryParam("limite") Integer limite){
		
		if(nome == null && situacao == null && perfil == null && pagina == null && limite == null) {
			try {
				List<UsuarioEntity> list = usuarioService.findAll();
				return new UsuarioFilterOutput.Builder()
						.setTotal(list.size())
						.setCodResposta(CodigosResposta.SUCESSO)
						.setDados(list)
						.builder();
				
			} catch (BusinessException e) {
				return new Output(readProperties.getMessage("msg.exception.internal"), 
									CodigosResposta.ERRO_INTERNO);
			}
		}
		
		List<UsuarioEntity> result = usuarioService.findAllByFilter(nome, situacao, perfil, pagina, limite);
		BigDecimal size = new BigDecimal(usuarioService.countByFilter(nome, situacao, perfil));

		BigDecimal paginas = BigDecimal.ZERO;
		if(limite == null && result.size() > 0) {
			paginas = new BigDecimal(1);
		} else if(limite != null) {
				paginas = size == BigDecimal.ZERO ? 
						BigDecimal.ZERO : size.divide(new BigDecimal(limite)).setScale(0, RoundingMode.UP);
		}
		UsuarioFilterOutput output = new UsuarioFilterOutput.Builder()
				.setTotal(size.intValue())
				.setPaginas(paginas.intValue())
				.setPrimeiraPagina(paginas.intValue() == 1 || (pagina != null && pagina == 1) ? true : false )
				.setUltimaPagina(pagina == null ? null : pagina == paginas.intValue())
				.setDados(result)
				.setCodResposta(CodigosResposta.SUCESSO)
				.builder();

		return output;
	}
	
	@Path("usuarios")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Output insertUsuario(UsuarioEntity entity) {
		
		Output output;
		try {
			usuarioService.insert(entity);
			output  = new Output(readProperties.getMessage("msg.MN001"), CodigosResposta.SUCESSO);
			return output;
		} catch (BusinessException e) {
			
			switch (e.getEnumCause()) {
			case CPF_INVALIDO:
				LOGGER.warn(e.getMessage());
				return new Output(e.getMessage(), CodigosResposta.REQUISICAO_INVALIDA);
			
			case ENTITY_DUPLICATED:
				LOGGER.warn(e.getMessage());
				return new Output(e.getMessage(), CodigosResposta.REQUISICAO_INVALIDA);
				
			default:
				e.printStackTrace();
				return new Output(e.getMessage(), CodigosResposta.ERRO_INTERNO);
			}
		}
	}
	
	@Path("usuarios/{cpf}")
	@DELETE
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Output delete(@PathParam("cpf") String cpf) {
		try {
			usuarioService.delete(cpf);
			return new Output(readProperties.getMessage("msg.MN005"), CodigosResposta.SUCESSO);
		} catch (BusinessException e) {
			if(e.getEnumCause() == CausesException.NO_RESULT) {
				return new Output(readProperties.getMessage("msg.exception.entity.excluir.naoexiste",cpf), 
						CodigosResposta.REQUISICAO_INVALIDA);
			}
			
			e.printStackTrace();
			return new Output(readProperties.getMessage("msg.exception.entity.exluir.internal"), 
					CodigosResposta.ERRO_INTERNO);
		}
	}
	
	
	@Path("usuarios/")
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Output atualiza(UsuarioEntity entity) {
		try {
			usuarioService.update(entity);
			return new Output(readProperties.getMessage("msg.MN030"), CodigosResposta.SUCESSO);
		} catch (BusinessException e) {
			e.printStackTrace();
			return new Output(readProperties.getMessage("msg.exception.entity.update.internal"), 
					CodigosResposta.ERRO_INTERNO);
		}
	}

	@Path("usuarios/{cpf}")
	@PATCH
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Output atualizaSituacao(SituacaoInput input, @PathParam("cpf") String cpf) {
		
		if(!input.getSituacao().equals("A") && !input.getSituacao().equals("I")) {
			return new Output(readProperties.getMessage("msg.exception.entity.update.situacao"), 
														CodigosResposta.REQUISICAO_INVALIDA);
		}
		
		try {
			usuarioService.updateSituacao(cpf, input);
			
			String msg = input.getSituacao().equals("A") ? readProperties.getMessage("msg.MN033") 
															:  readProperties.getMessage("msg.MN032");
			return new Output(msg, CodigosResposta.SUCESSO);
		} catch (BusinessException e) {
			e.printStackTrace();
			return new Output(readProperties.getMessage("msg.exception.entity.exluir.internal"), 
					CodigosResposta.ERRO_INTERNO);
		}
	}
}
