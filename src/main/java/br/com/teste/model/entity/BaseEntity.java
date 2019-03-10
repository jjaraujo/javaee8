package br.com.teste.model.entity;

import java.io.Serializable;

/**
 * Classe abstrata que implementa Serializable para entidades JPA.
 */
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public abstract Object getId();
	
}
