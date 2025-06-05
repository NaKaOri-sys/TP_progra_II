package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<IEntrada> venderEntradaDelEspectaculo(Espectaculo espectaculo, Fecha fecha, Usuario usuario,
			String email, String ubicacion, int cantidadEntradas) {
		Funcion funcion = espectaculo.obtenerFuncion(fecha);
		Sede sede = funcion.obtenerSede();
		double precioPorEntrada = 0;
		List<IEntrada> entradas = new ArrayList<>();

		for (int i = 0; i < cantidadEntradas; i++) {

			String codigo = Entrada.generarCodigo(8);

			Entrada entrada = new Entrada(codigo, espectaculo, fecha, sede, ubicacion, email);
			entradas.add(entrada);
			usuario.comprarEntrada(entrada);
			funcion.registrarEntrada(entrada, ubicacion);
		}
		double montoRecaudado = precioPorEntrada * cantidadEntradas;
		sede.actualizarRecaudacionEspectaculo(obtenerNombre(), montoRecaudado);
		return entradas;
	}

	public List<IEntrada> venderEntradaDelEspectaculo(Espectaculo espectaculo, Fecha fecha, Usuario usuario,
			String email, String sector, int[] asientos) {
		Funcion funcion = espectaculo.obtenerFuncion(fecha);
		Sede sede = funcion.obtenerSede();
		double precioPorEntrada = sede.calcularPrecioBase(sede.obtenerSectores().get(sector),
				funcion.obtenerPrecioBase());
		List<IEntrada> entradas = new ArrayList<>();
		for (int i = 0; i < asientos.length; i++) {

			String codigo = Entrada.generarCodigo(8);
			String ubicacion = sector + " f:" + sede.obtenerSectores().get(sector).calcularFila(asientos[i]) + " a:"
					+ asientos[i];

			Entrada entrada = new Entrada(codigo, espectaculo, fecha, sede, ubicacion, email);
			entradas.add(entrada);
			usuario.comprarEntrada(entrada);
			funcion.registrarEntrada(entrada, sector);
		}
		double montoRecaudado = precioPorEntrada * asientos.length;
		sede.actualizarRecaudacionEspectaculo(espectaculo.obtenerNombre(), montoRecaudado);
		return entradas;
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
		builder.append("- ").append(codigo).append(" | ").append(nombre).append(" | ").append("\n")
				.append("Funciones \n");
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