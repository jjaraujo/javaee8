package br.com.teste.util.exceptions;

import br.com.teste.util.ReadProperties;

public class BusinessException extends Exception {	
	
	private static final long serialVersionUID = 1L;
	
	private CausesException cause;
		
	public BusinessException(String keyProperty, CausesException cause, String... parametros) throws BusinessException {
		super(getNameProperty(keyProperty, parametros));
		this.cause = cause;
	}
	
	public BusinessException(String keyProperty, CausesException cause) throws BusinessException {
		super(getNameProperty(keyProperty));
		this.cause = cause;
	}
	
	public BusinessException(String keyProperty, Throwable cause, String... parametersProperty) throws BusinessException  {
		super(getNameProperty(keyProperty, parametersProperty), cause);
	}
	

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	private static String getNameProperty(String property, String... parametersProperty) throws BusinessException {
		return new ReadProperties().getMessage(property, parametersProperty);
	}

	public CausesException getEnumCause() {
		return cause;
	}

	public void setEnumCause(CausesException cause) {
		this.cause = cause;
	}
}
