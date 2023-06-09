package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configuratore {

	private static final String FILE_NAME = "src/diadia.properties";
	private static final String VINCOLO_CFU = "cfu";
	private static final String VINCOLO_PESO_MAX_BORSA = "peso_max";
	private static final String VINCOLO_DIREZIONI_MAX_STANZA = "direzioni_max";
	private static final String VINCOLO_ATTREZZI_MAX_STANZA = "attrezzi_max";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(VINCOLO_CFU));
	}
	
	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(VINCOLO_PESO_MAX_BORSA));
	}
	
	public static int getDirezioniMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(VINCOLO_DIREZIONI_MAX_STANZA));
	}
	
	public static int getAttrezziMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(VINCOLO_ATTREZZI_MAX_STANZA));
	}

	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(FILE_NAME);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
