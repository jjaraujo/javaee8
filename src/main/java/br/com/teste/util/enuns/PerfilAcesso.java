package br.com.teste.util.enuns;

public enum PerfilAcesso {
	
	GESTOR_MUNICIPAL(1,"Gestor Municipal"),
	GESTOR_ESTADUAL(2,"Gestor Estadual"),
	GESTOR_NACIONAL(3,"Gestor Nacional");
	
	private int cod;
	private String nome;
	
	PerfilAcesso(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}
}

