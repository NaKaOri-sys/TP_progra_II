package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.UUID;

public class Espectaculo {
	private String codigo;
	private String nombre;
	private HashMap<Fecha, Funcion> funciones;

	public Espectaculo(String nombre) {
		this.nombre = nombre;
		this.funciones = new HashMap<Fecha, Funcion>();
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

	public String listarFunciones() {
		StringBuilder sb = new StringBuilder();
		for (Funcion entry : funciones.values()) {
			sb.append(entry.toString());

		}
		return sb.toString();
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

	public Funcion obtenerFuncion(Fecha fecha) {
		if (!funciones.containsKey(fecha))
			throw new IllegalArgumentException("La fecha ingresada no existe para ninguna función activa.");
		return funciones.get(fecha);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("- ").append(codigo).append(" | ").append(nombre).append(" | ").append("\n").append("Funciones \n");
		if (funciones.isEmpty()) {
			builder.append("Por el momento no hay funciones agregadas.\n");
		}
		for (Funcion func : funciones.values()) {
			double totalRecaudado = 0;
			builder.append(func.obtenerFecha());
			for (IEntrada entrada : func.obtenerEntradasVendidas()) {
				totalRecaudado += entrada.precio();
			}
			builder.append(" | ").append("Total recaudado: $").append(totalRecaudado).append("\n");
		}

		return builder.toString();
	}

}