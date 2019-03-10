package br.com.teste.model.entity;

public abstract class Auditing extends Versioned{

	private static final long serialVersionUID = 1L;

	/*
	 * Aqui seriam adicionadas os campos de auditoria, 
	 * como usuário que criou, data de criação, usuário que alterou,
	 * data de alteração etc...
	 * Não adicionei pois o script de criação das tabelas não possui 
	 * esses dados, assim evitarei maior esforço na hora de testarem
	 * e também porque a implementação que eu fiz não requer usuário 
	 * logado.
	 * */
	
}
