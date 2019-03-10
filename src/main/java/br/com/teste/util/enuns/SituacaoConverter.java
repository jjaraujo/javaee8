package br.com.teste.util.enuns;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class SituacaoConverter implements AttributeConverter<Situacao, String>{
	
	public String convertToDatabaseColumn(Situacao acesso) {
		return acesso.toString();
	}

	public Situacao convertToEntityAttribute(String id) {
		if(id == null) {
			return null;
		}
		for(Situacao acesso : Situacao.values()) {
			if(acesso.toString().equals(id)){
				return acesso;
			}
		}
		return null;
		
	}
	
}
