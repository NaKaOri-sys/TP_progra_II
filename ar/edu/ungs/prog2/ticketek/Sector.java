package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.Objects;

public class Sector {
	private String tipo;
	private int asientosPorFila;
	private int incremento;
	private int capacidadMaxSector;
	public Sector(String tipo, int asientosPorFila, int capacidadMaxSector, int incremento) {
		this.tipo = tipo;
		this.asientosPorFila = asientosPorFila;
		this.incremento = incremento;
		this.capacidadMaxSector = capacidadMaxSector;
	}
	
	public Sector(String tipo) {
		this.tipo = tipo;
	}
	public String obtenerTipo() {
		return tipo;
	}
	public int obtenerAsientosPorFila() {
		return asientosPorFila;
	}
	public int obtenerIncremento() {
		return incremento;
	}
	
	public int obtenerCapacidadMaxSector() {
		return capacidadMaxSector;
	}

	@Override
	public int hashCode() {
		return Objects.hash(asientosPorFila, capacidadMaxSector, incremento, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sector other = (Sector) obj;
		return asientosPorFila == other.asientosPorFila && capacidadMaxSector == other.capacidadMaxSector
				&& incremento == other.incremento && Objects.equals(tipo, other.tipo);
	}
	
	
}