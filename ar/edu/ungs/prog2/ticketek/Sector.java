package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Sector {
	private String tipo;
	private int numero;
	private int asientosPorFila;
	private int incremento;
	public Sector(String tipo, int numero, int asientosPorFila, int incremento) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.asientosPorFila = asientosPorFila;
		this.incremento = incremento;
	}
	public String obtenerTipo() {
		return tipo;
	}
	public int obtenerNumero() {
		return numero;
	}
	public int obtenerAsientosPorFila() {
		return asientosPorFila;
	}
	public int obtenerIncremento() {
		return incremento;
	}
	
}
