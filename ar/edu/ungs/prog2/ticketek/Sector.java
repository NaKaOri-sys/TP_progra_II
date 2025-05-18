package TP_progra_II.ar.edu.ungs.prog2.ticketek;

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
}
