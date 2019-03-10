package br.com.teste.util.enuns;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.glassfish.jersey.internal.inject.InjectionManagerFactory;

@Converter(autoApply=true)
public class PerfilAcessoConverter implements AttributeConverter<PerfilAcesso, Integer>{
	
	public Integer convertToDatabaseColumn(PerfilAcesso acesso) {
		return acesso.getCod();
	}

	public PerfilAcesso convertToEntityAttribute(Integer id) {
		if(id == null) {
			return null;
		}
		for(PerfilAcesso acesso : PerfilAcesso.values()) {
			if(acesso.getCod() == id){
				return acesso;
			}
		}
		return null;
		
	}
	
}
