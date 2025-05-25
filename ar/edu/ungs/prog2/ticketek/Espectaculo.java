package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Espectaculo {
	private String codigo;
	private String nombre;
	private Map<Fecha, Funcion> funciones;
	/**
	 * @param codigo
	 * @param nombre
	 * @param funciones
	 */
	public Espectaculo(String codigo, String nombre, Map<Fecha, Funcion> funciones) {
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
	public Map<Fecha, Funcion> obtenerFunciones() {
		return funciones;
	}
	
	public Funcion obtenerFuncion(Fecha fecha) {
		funciones.
	}
}
