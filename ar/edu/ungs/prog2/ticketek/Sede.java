package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public abstract class Sede {
	private String nombre;
	private String direccion;
	private int capacidadMaxima;
	private HashMap<String, Double> recaudacionPorEspectaculo;

	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
		this.recaudacionPorEspectaculo = new HashMap<String, Double>();
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

	public void actualizarRecaudacionEspectaculo(String nombreEspectaculo, double monto) {
		recaudacionPorEspectaculo.merge(nombreEspectaculo, monto, Double::sum);
	}

	public double obtenerRecaudacionPorEspectaculo(String nombreEspectaculo) {
		return recaudacionPorEspectaculo.getOrDefault(nombreEspectaculo, 0.0);
	}

	/***
	 * Devuelve los sectores registrados en el caso de Estadio, solo devuelve el
	 * nombre de sector.
	 * 
	 * @return
	 */
	public abstract String listarSectores();

	/**
	 * Recibe el diccionario de entradas vendidas de LA FUNCIÓN ESPECÍFICA para que
	 * la Sede pueda calcular y mostrar "vendidas / capacidad".
	 *
	 * @param entradasVendidasPorSector Mapa<String (tipoSector), Integer
	 *                                  (cantidadVendida)> de la función.
	 * @return String con el formato de sectores solicitado.
	 */
	public abstract String obtenerInfoSectores(HashMap<String, Integer> entradasVendidasPorSector);

	public abstract HashMap<String, Sector> obtenerSectores();

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
		StringBuilder builder = new StringBuilder();
		builder.append("- ");
		builder.append(nombre);
		builder.append(" | ");
		builder.append(direccion);
		builder.append(" | ");
		builder.append("Capacidad máxima: ");
		builder.append(capacidadMaxima);
		builder.append(" | ");
		for (Entry<String, Double> entry : recaudacionPorEspectaculo.entrySet()) {
			String key = entry.getKey();
			double val = entry.getValue();
			builder.append("Espectaculo: ").append(key).append(" | ").append("Total recaudado: $").append(val);
		}
		builder.append(" \n");
		return builder.toString();
	}

}
