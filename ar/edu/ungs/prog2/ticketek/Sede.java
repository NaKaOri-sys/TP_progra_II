package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Objects;

public abstract class Sede {
	private String nombre;
	private String direccion;
	private int capacidadMaxima;
	
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
	
	public abstract double calcularPrecioBase(Sector sector, double precioBase);
	
	 /**
     * Recibe el diccionario de entradas vendidas de LA FUNCIÓN ESPECÍFICA para que la Sede
     * pueda calcular y mostrar "vendidas / capacidad".
     *
     * @param entradasVendidasPorSector Mapa<String (tipoSector), Integer (cantidadVendida)> de la función.
     * @return String con el formato de sectores solicitado.
     */
    public abstract String obtenerInfoSectores(HashMap<String, Integer> entradasVendidasPorSector);

	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
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
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Sede [nombre=" + nombre + ", direccion=" + direccion + ", capacidadMaxima=" + capacidadMaxima
				+ "]";
	}

}
