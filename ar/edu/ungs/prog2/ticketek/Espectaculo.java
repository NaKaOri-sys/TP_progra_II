package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Espectaculo {
	private String codigo;
	private String nombre;
	private HashMap<Fecha, Funcion> funciones;
	/**
	 * @param codigo
	 * @param nombre
	 * @param funciones
	 */
	public Espectaculo(String codigo, String nombre, HashMap<Fecha, Funcion> funciones) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.funciones = funciones;
	}
	public String obtenerCodigo() {
		return codigo;
	}
	public String obtenerNombre() {
		return nombre;
	}
	public HashMap<Fecha, Funcion> obtenerFunciones() {
		return funciones;
	}
	
	public Funcion obtenerFuncion(Fecha fecha) throws Exception {
		if(!funciones.containsKey(fecha))
			throw new Exception();
		return funciones.get(fecha);
	}
}
