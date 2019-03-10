package br.com.teste.model.entity;

public abstract class Auditing extends Versioned{

	private static final long serialVersionUID = 1L;

	/*
	 * Aqui seriam adicionadas os campos de auditoria, 
	 * como usu�rio que criou, data de cria��o, usu�rio que alterou,
	 * data de altera��o etc...
	 * N�o adicionei pois o script de cria��o das tabelas n�o possui 
	 * esses dados, assim evitarei maior esfor�o na hora de testarem
	 * e tamb�m porque a implementa��o que eu fiz n�o requer usu�rio 
	 * logado.
	 * */
	
}
