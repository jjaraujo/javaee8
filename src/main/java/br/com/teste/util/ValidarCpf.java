package br.com.teste.util;

import br.com.teste.util.exceptions.BusinessException;
import br.com.teste.util.exceptions.CausesException;

public class ValidarCpf {
	/**
	 * Fonte: https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
	 * @throws BusinessException 
	 * 
	 * */
	public static boolean validar(String cpf) throws BusinessException {	
		if(cpf == null || cpf.isEmpty()) {
			return false;	
		}
		// Elimina CPFs invalidos conhecidos	
		if (cpf.length() != 11 || 
			cpf == "00000000000" || 
			cpf == "11111111111" || 
			cpf == "22222222222" || 
			cpf == "33333333333" || 
			cpf == "44444444444" || 
			cpf == "55555555555" || 
			cpf == "66666666666" || 
			cpf == "77777777777" || 
			cpf == "88888888888" || 
			cpf == "99999999999")
				return false;		
		// Valida 1o digito	
		int add = 0;	
		int rev = 0;
		try {
			for (int i=0; i < 9; i ++) {
				add += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);	
			}
			 rev = 11 - (add % 11);	
			 
			if (rev == 10 || rev == 11)	{
				rev = 0;	
			}
			if (rev != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {		
				return false;		
			}
			// Valida 2o digito	
			add = 0;	
			for (int i = 0; i < 10; i ++){
				add += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
			}
			rev = 11 - (add % 11);	
			if (rev == 10 || rev == 11)	{
				rev = 0;	
			}
			if (rev != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {
				return false;	
			}
		}catch (NumberFormatException e) {
			throw new BusinessException("msg.exception.cpf.letras", CausesException.CPF_INVALIDO);
		}
		return true;   
	}
	
}
