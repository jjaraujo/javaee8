package br.com.teste.controller.payload.output;

public class Output {

	protected String msg;
	protected Integer codigoResposta;
	
	public Output() {
		
	}
	public Output(String msg, CodigosResposta codigoResposta) {
		this.msg = msg;
		this.codigoResposta = codigoResposta == null ? null : codigoResposta.getCod();
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCodigoResposta() {
		return codigoResposta;
	}
	public void setCodigoResposta(Integer codigoResposta) {
		this.codigoResposta = codigoResposta;
	}
	
}
