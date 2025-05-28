package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Random;

public class Entrada implements IEntrada {
	private String codigo;
	private Espectaculo espectaculo;
	private Fecha fecha;
	private Sede sede;
	private String ubicacion;
	private String sector;
	private int fila;
	private int asiento;

	// Constructor para no numeradas
	public Entrada(String codigo, Espectaculo espectaculo, Fecha fecha, Sede sede, String sector) {
		this.codigo = codigo;
		this.espectaculo = espectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.sector = sector;
		this.ubicacion = "Campo";
	}

	// Constructor para numeradas
	public Entrada(String codigo, Espectaculo espectaculo, Fecha fecha, Sede sede, String sector, int fila,
			int asiento) {
		this.codigo = codigo;
		this.espectaculo = espectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.sector = sector;
		this.fila = fila;
		this.asiento = asiento;
		this.ubicacion = sector + " f:" + fila + " a:" + asiento;
	}

	public String obtenerNombre() {
		return espectaculo.obtenerNombre();
	}

	public Fecha obtenerFecha() {
		return fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public String ubicacion() {
		if (ubicacion.equals("Campo")) {
			return "Campo";
		} else {
			return sector + " f:" + fila + " a:" + asiento;
		}
	}
	
	public String obtenerSector() {
		return this.sector;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Fecha hoy = Fecha.fechaActual();
		sb.append(codigo).append(" - ").append(espectaculo.obtenerNombre()).append(" - ");
		sb.append(fecha.toString());
		if (fecha.esMayor(fecha, hoy)) {
			sb.append(" P");
		}
		sb.append(" - ").append(sede.toString()).append(" - ").append(ubicacion());
		return sb.toString();
	}

	public static String generarCodigo(int longitud) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < longitud; i++) {
			sb.append(caracteres.charAt(rand.nextInt(caracteres.length())));
		}
		return sb.toString();
	}

	@Override
	public double precio() {
		Funcion funcion = espectaculo.obtenerFuncion(fecha);
		HashMap<String, Sector> sectores =  funcion.obtenerSede().obtenerSectores();
		Sector sectorFuncion = sectores.get(obtenerSector());
		return funcion.obtenerSede().calcularPrecioBase(sectorFuncion, funcion.obtenerPrecioBase());
	}
}
