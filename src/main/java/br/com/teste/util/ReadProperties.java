package br.com.teste.util;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;


@Service
@Named
public class ReadProperties {

	public String getMessage(String key, String... params) {

		try {
		//	ClassLoader classLoader = getClass().getClassLoader();
			Properties prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream("br/com/teste/properties/pt_BR.properties"));
			String mensagemParametrizada = MessageFormat.format(prop.getProperty(key),params);
			return mensagemParametrizada;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
