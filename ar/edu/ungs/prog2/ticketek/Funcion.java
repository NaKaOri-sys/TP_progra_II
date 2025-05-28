package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Funcion {
	private Sede sede;
	private Fecha fecha;
	private double precioBase;
	private List<IEntrada> entradasVendidas;
	private HashMap<String, Integer> entradasVendidasPorSector;

	public Funcion(Sede sede, Fecha fecha, double precioBase) {
		this.sede = sede;
		this.fecha = fecha;
		this.precioBase = precioBase;
		this.entradasVendidas = new ArrayList<>();
		this.entradasVendidasPorSector = new HashMap<String, Integer>();
	}

	public Sede obtenerSede() {
		return sede;
	}

	public Fecha obtenerFecha() {
		return fecha;
	}

	public double obtenerPrecioBase() {
		return precioBase;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	
	public void registrarEntrada(IEntrada entrada, String sector) {
		if (entrada == null) {
			throw new IllegalArgumentException("La entrada no puede ser nula.");
		}
		if (!this.obtenerSede().obtenerSectores().containsKey(sector)) {
			throw new IllegalArgumentException("El sector no se encuentra registrado.");
		}
		this.entradasVendidas.add(entrada);
		int entradasActuales = entradasVendidasPorSector.getOrDefault(sector, 0);
		entradasVendidasPorSector.put(sector, entradasActuales + 1);
	}

	public List<IEntrada> obtenerEntradasVendidas() {
		return entradasVendidas;
	}
	
	public HashMap<String, Integer> obtenerEntradasVendidasPorSector(){
		return this.entradasVendidasPorSector;
	}

	public boolean asientoOcupado(String sector, int fila, int asiento) {
		for (IEntrada e : entradasVendidas) {
			if (e.ubicacion().equals(sector + " f:" + fila + " a:" + asiento)) {
				return true;
			}
		}
		return false;
	}
	public void liberarAsiento(IEntrada entrada) {

	    Entrada e = (Entrada) entrada;   
	    // Eliminar la entrada de la lista de vendidas
	    this.entradasVendidas.remove(entrada);
	    // Obtener el sector desde la ubicación
	    String sector;
	    if (e.ubicacion().equals("CAMPO")) {
	        sector = "CAMPO";
	    } else {
	        // Extraer sector desde ubicacion: "Platea f:3 a:5" → "Platea"
	        String[] partes = e.ubicacion().split(" f:");
	        sector = partes[0];
	    }
	    // Restar uno al contador de entradas vendidas por sector
	    int cantidadActual = this.entradasVendidasPorSector.getOrDefault(sector, 0);
	    if (cantidadActual > 0) {
	        this.entradasVendidasPorSector.put(sector, cantidadActual - 1);
	    }
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" - ").append("(").append(obtenerFecha().toString()).append(")").append(" ").append(obtenerSede().obtenerNombre())
				.append(" - ").append(this.sede.obtenerInfoSectores(obtenerEntradasVendidasPorSector()));
		return sb.toString();
	}
}
