package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Estadio extends Sede {
	private static Sector CAMPO;
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		CAMPO = new Sector("Campo", 0,capacidadMaxima, 0);
	}
	
	public Sector obtenerSector() {
		return CAMPO;
	}

	@Override
	public double calcularPrecioBase(Sector sector, double precioBase) {
		if (!sector.equals(CAMPO)) {
			throw new IllegalArgumentException("Estadio solo puede tener sector CAMPO.");
		}
		return precioBase;
	}

	@Override
	public String obtenerInfoSectores(HashMap<String, Integer> entradasVendidasPorSector) {
		int entradasVendidas = entradasVendidasPorSector.getOrDefault(CAMPO, 0);
        return entradasVendidas + "/" + obtenerCapacidadMaxima();
	}

	@Override
	public String listarSectores() {
		StringBuilder sb = new StringBuilder();
		sb.append(CAMPO.toString());
		return sb.toString();
	}
}
