package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.UUID;

public class Espectaculo {
	private String codigo;
	private String nombre;
	private HashMap<Fecha, Funcion> funciones;
	/**
	 * @param codigo
	 * @param nombre
	 * @param funciones
	 */
	public Espectaculo(String nombre) {
		this.nombre = nombre;
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
	
	public void registrarCodigo() {
		String uuid = UUID.randomUUID().toString();
		this.codigo = uuid;
	}
	
	public void registrarFuncion(String fecha, Sede sedeRegistrada, double precioBase) {
		Fecha fechaFuncion = new Fecha(fecha);
		Funcion funcion = new Funcion(sedeRegistrada, fechaFuncion, precioBase);
		funciones.put(fechaFuncion, funcion);
	}
	
	public Funcion obtenerFuncion(Fecha fecha) throws Exception {
		if(!funciones.containsKey(fecha))
			throw new Exception();
		return funciones.get(fecha);
	}
}
