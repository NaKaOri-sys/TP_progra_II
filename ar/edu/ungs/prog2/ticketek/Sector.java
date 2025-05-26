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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(obtenerTipo());
		if (obtenerTipo().equals("Campo")) {
			return sb.toString();
		}
		sb.append(" - ").append("asientos por fila: ").append(obtenerAsientosPorFila()).append(" - ")
		.append("incremento por sector: %").append(obtenerIncremento())
		.append(" - ").append("capacidad máxima en sector: ").append(obtenerCapacidadMaxSector()).append("\n");
		return sb.toString();
	}
	
	
}