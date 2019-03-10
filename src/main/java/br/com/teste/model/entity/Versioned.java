package br.com.teste.model.entity;

import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Versioned extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Version
	@JsonIgnore
	private Long version;

	/**
	 * Retorna a versao
	 * @return versao
	 */
	public Long getVersion() {
		return version;
	}
	
	/**
	 * Seta a versao
	 * @param version versao a ser setada
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
}
