package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.Random;
import java.util.Objects;

public class Entrada implements IEntrada {
	private String codigo;
	private Espectaculo espectaculo;
	private Fecha fecha;
	private Sede sede;
	private String ubicacion;
	private String emailComprador;

	public Entrada(String codigo, Espectaculo espectaculo, Fecha fecha, Sede sede, String ubicacion,
			String emailComprador) {
		this.codigo = codigo;
		this.espectaculo = espectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.ubicacion = ubicacion;
		this.emailComprador = emailComprador;
	}

	@Override
	public String obtenerNombre() {
		return espectaculo.obtenerNombre();
	}

	@Override
	public Fecha obtenerFecha() {
		return fecha;
	}

	@Override
	public String obtenerCodigo() {
		return codigo;
	}

	@Override
	public String obtenerEmailComprador() {
		return emailComprador;
	}

	@Override
	public String ubicacion() {

		if (ubicacion.equals("CAMPO")) {
			return "CAMPO";
		} else {
			return ubicacion;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Fecha hoy = Fecha.fechaActual();
		sb.append(codigo).append(" - ").append(obtenerNombre()).append(" - ");
		sb.append(fecha.toString());

		if (fecha.esMayor(hoy, fecha)) {
			sb.append(" P");
		}

		sb.append(" - ").append(sede.obtenerNombre()).append(" - ").append(ubicacion());
		return sb.toString();
	}

	public static String generarCodigo(int longitud) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < longitud; i++) {
			sb.append(caracteres.charAt(rand.nextInt(caracteres.length())));
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Entrada))
			return false;
		Entrada entrada = (Entrada) o;
		return codigo != null && codigo.equals(entrada.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public double precio() {
		return espectaculo.obtenerFuncion(obtenerFecha()).obtenerSede().calcularPrecioBase(espectaculo.obtenerFuncion(fecha).obtenerSede().obtenerSectores()
				.get(obtenerNombreSectorDesdeUbicacion(ubicacion)), 
				espectaculo.obtenerFuncion(fecha).obtenerPrecioBase());
	}

	public static String obtenerNombreSectorDesdeUbicacion(String ubicacion) {
		if (ubicacion == null || ubicacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La ubicación no puede ser nula o vacía para extraer el sector.");
		}

		if (ubicacion.contains(" f:")) {
			String[] partes = ubicacion.split(" f:");
			// Asegurarse de que haya al menos una parte después del split
			if (partes.length > 0) {
				return partes[0].trim(); // Usamos trim() para eliminar posibles espacios en blanco
			}
		}
		return ubicacion.trim();
	}
}
