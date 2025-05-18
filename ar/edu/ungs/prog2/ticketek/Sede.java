package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public abstract class Sede {
	private String nombre;
	private String direccion;
	private int capacidadMaxima;
	private double precioBase;
	
	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String obtenerDireccion() {
		return direccion;
	}

	public int obtenerCapacidadMaxima() {
		return capacidadMaxima;
	}

	public double obtenerPrecioBase() {
		return precioBase;
	}
	
	public abstract double calcularPrecioBase(Sector sector);

	@Override
	public String toString() {
		return "Sede [nombre=" + nombre + ", direccion=" + direccion + ", capacidadMaxima=" + capacidadMaxima
				+ ", precioBase=" + precioBase + "]";
	}
}
