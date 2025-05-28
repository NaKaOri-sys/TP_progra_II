package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.*;

public class Funcion {
	private Sede sede;
	private Fecha fecha;
	private double precioBase;
    private Set<IEntrada> entradasVendidas;
	private HashMap<String, Integer> entradasVendidasPorSector;

	public Funcion(Sede sede, Fecha fecha, double precioBase) {
		this.sede = sede;
		this.fecha = fecha;
		this.precioBase = precioBase;
        this.entradasVendidas = new HashSet<>();
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
        entradasVendidas.add(entrada);
        entradasVendidasPorSector.put(sector,
            entradasVendidasPorSector.getOrDefault(sector, 0) + 1);
    }

	public Set<IEntrada> obtenerEntradasVendidas() {
		return entradasVendidas;
	}
	
	public HashMap<String, Integer> obtenerEntradasVendidasPorSector(){
		return this.entradasVendidasPorSector;
	}

    public boolean asientoOcupado(String sector, int fila, int asiento) {
        for (IEntrada entrada : entradasVendidas) {
            if (entrada.ubicacion().equalsIgnoreCase(sector + " f:" + fila + " a:" + asiento)) {
                return true;
            }
        }
        return false;
    }
    public void liberarAsiento(IEntrada entrada) {
        entradasVendidas.remove(entrada);
        String sector;
        if (entrada.ubicacion().equals("CAMPO")) {
            sector = "CAMPO";
        } else {
            String[] partes = entrada.ubicacion().split(" f:");
            sector = partes[0];
        }

        int cantidadActual = this.entradasVendidasPorSector.getOrDefault(sector, 0);
        if (cantidadActual > 0) {
            this.entradasVendidasPorSector.put(sector, cantidadActual - 1);
        }
    }
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" - ").append("(").append(obtenerFecha().toString()).append(")").append(" ").append(obtenerSede().obtenerNombre())
				.append(" - ").append(this.sede.obtenerInfoSectores(obtenerEntradasVendidasPorSector())).append("\n");
		return sb.toString();
	}
}
