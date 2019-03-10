package br.com.teste.controller.payload.output;

public enum CodigosResposta {

	SUCESSO(200),
	NENHUM_RESULTADO(204),
	REQUISICAO_INVALIDA(400),
	ERRO_INTERNO(500);
	
	private int cod;
	CodigosResposta(int cod) {
		this.cod = cod;
	}
	
	public int getCod() {
		return cod;
	}
	
}
