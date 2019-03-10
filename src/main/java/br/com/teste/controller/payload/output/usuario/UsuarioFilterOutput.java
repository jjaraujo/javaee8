package br.com.teste.controller.payload.output.usuario;

import java.util.List;

import br.com.teste.controller.payload.output.CodigosResposta;
import br.com.teste.controller.payload.output.Output;
import br.com.teste.model.entity.UsuarioEntity;

public class UsuarioFilterOutput extends Output{
	
	private Integer qtd_total;
	private Integer qtd_paginas;
	private Boolean ultima_pagina;
	private Boolean primeira_pagina;
	private List<UsuarioEntity> dados;
	
	public UsuarioFilterOutput() {
		
	}
	
	public UsuarioFilterOutput(Integer qtd_total, Integer qtd_paginas, Boolean ultima_pagina, 
			Boolean primeira_pagina, List<UsuarioEntity> dados, String msg, CodigosResposta cod) {
		super(msg, cod);
		this.qtd_total = qtd_total;
		this.qtd_paginas = qtd_paginas;
		this.ultima_pagina = ultima_pagina;
		this.primeira_pagina = primeira_pagina;
		this.dados = dados;
	}

	public Integer getQtdTotal() {
		return qtd_total;
	}
	
	public Integer getQtd_paginas() {
		return qtd_paginas;
	}
	
	public Boolean isUltima_pagina() {
		return ultima_pagina;
	}

	public Boolean isPrimeira_pagina() {
		return primeira_pagina;
	}

	public List<UsuarioEntity> getDados() {
		return dados;
	}

public static class Builder{

	Integer qtd_total;
	Integer qtd_paginas;
	Boolean ultima_pagina;
	Boolean primeira_pagina;
	List<UsuarioEntity> dados;
	String msg;
	CodigosResposta cod;

	public Builder setTotal(Integer qtd_total) {
		this.qtd_total = qtd_total;
		return this;
	}

	public Builder setPaginas(Integer qtd_paginas) {
		this.qtd_paginas = qtd_paginas;
		return this;
	}

	public Builder setUltimaPagina(Boolean ultima_pagina) {
		this.ultima_pagina = ultima_pagina;
		return this;
	}

	public Builder setPrimeiraPagina(Boolean primeira_pagina) {
		this.primeira_pagina = primeira_pagina;
		return this;
	}

	public Builder setDados(List<UsuarioEntity> dados) {
		this.dados = dados;
		return this;
	}

	public Builder setCodResposta(CodigosResposta cod) {
		this.cod = cod;
		return this;
	}

	public Builder setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public UsuarioFilterOutput builder() {
		return new UsuarioFilterOutput(qtd_total, qtd_paginas, ultima_pagina, primeira_pagina, dados, msg, cod);
	}
}
}
