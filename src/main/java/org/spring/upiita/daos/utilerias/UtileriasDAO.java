package org.spring.upiita.daos.utilerias;

import java.util.Map;

public class UtileriasDAO {

	// =---------------- EJERCICIO -----------------------
	private String cadena;

	private Integer numero;

	private Map<String, String> mapa;

	private ConfiguracionUtileria configuracion;

	// ------------------------------------------------

	public void procesar() {

		System.out.println("procesando datos...");
		System.out.println("cadena:" + cadena + ", numero:" + numero
				+ ", mapa:" + mapa + ", configuracion:" + configuracion);

	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Map<String, String> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}

	public ConfiguracionUtileria getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionUtileria configuracion) {
		this.configuracion = configuracion;
	}

}
