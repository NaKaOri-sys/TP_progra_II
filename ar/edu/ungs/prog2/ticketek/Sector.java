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

	/**
	 * Calcula la fila dado un número de asiento global y los asientos por fila del
	 * sector. Los asientos empiezan en 1.
	 * 
	 * @param numeroAsientoGlobal El número de asiento global en el rango total del
	 *                            sector (1 a capacidadMaxima).
	 * @return El número de fila.
	 */
	public int calcularFila(int numeroAsientoGlobal) {
		if (asientosPorFila <= 0 || numeroAsientoGlobal <= 0) {
			throw new IllegalArgumentException(
					"asientosPorFila y numeroAsientoGlobal tienen que ser numeros positivos mayores a 0.");
		}
		return (int) Math.ceil((double) numeroAsientoGlobal / asientosPorFila);
	}

	/**
	 * Calcula el número de asiento dentro de su fila dado un número de asiento
	 * global. Los asientos dentro de la fila empiezan en 1.
	 * 
	 * @param numeroAsientoGlobal El número de asiento global.
	 * @return El número de asiento dentro de su fila.
	 */
	public int calcularAsientoEnFila(int numeroAsientoGlobal) {
		if (asientosPorFila <= 0 || numeroAsientoGlobal <= 0) {
			throw new IllegalArgumentException(
					"asientosPorFila y numeroAsientoGlobal tienen que ser numeros positivos mayores a 0.");
		}
		int asientoEnFila = numeroAsientoGlobal % asientosPorFila;
		// Si el módulo es 0, significa que es el último asiento de la fila.
		return (asientoEnFila == 0) ? asientosPorFila : asientoEnFila;
	}
	public int calcularFilaParaAsiento(Funcion funcion, String nombreSector, int numeroAsiento) {
	    Sector sector = funcion.obtenerSede().obtenerSectores().get(nombreSector);
	    if (sector == null) {
	        throw new IllegalArgumentException("El sector no existe en la sede de esta función.");
	    }
	    return sector.calcularFila(numeroAsiento);
	}
	public int getAsientosPorFila() {
		return asientosPorFila;
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
				.append("incremento por sector: %").append(obtenerIncremento()).append(" - ")
				.append("capacidad máxima en sector: ").append(obtenerCapacidadMaxSector()).append("\n");
		return sb.toString();
	}

}