package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.Objects;

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
	
	public abstract double calcularPrecioBase(Sector sector) throws Exception;
	
	public double calcularPrecioBase() {
		return obtenerPrecioBase();
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(capacidadMaxima, direccion, nombre, precioBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sede other = (Sede) obj;
		return capacidadMaxima == other.capacidadMaxima && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precioBase) == Double.doubleToLongBits(other.precioBase);
	}

	@Override
	public String toString() {
		return "Sede [nombre=" + nombre + ", direccion=" + direccion + ", capacidadMaxima=" + capacidadMaxima
				+ ", precioBase=" + precioBase + "]";
	}

}
