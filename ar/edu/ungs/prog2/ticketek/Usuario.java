package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
	private String email;
	private String nombre;
	private String apellido;
	private String contrasenia;
	private HashMap<String, IEntrada> entradas;
	private Fecha fechaRegistro;

	public Usuario(String email, String nombre, String apellido, String contrasenia, Fecha fechaRegistro) {
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.fechaRegistro = fechaRegistro;
		this.entradas= new HashMap<String, IEntrada>();
	}

	/*
	 * public void comprarEntradas(List<IEntrada> nuevasEntradas) {
	 * entradas.addAll(nuevasEntradas); }
	 */

	public void comprarEntrada(IEntrada nuevaEntrada) {
		entradas.put(nuevaEntrada.obtenerCodigo(), nuevaEntrada);
	}

	public IEntrada obtenerEntrada(String codigo) {
		return entradas.get(codigo);
	}

	public List<IEntrada> obtenerEntradas() {
		List<IEntrada> listaEntradas = new ArrayList<>();
		for (IEntrada e : this.entradas.values()) {
			listaEntradas.add(e);
		}
		return listaEntradas;
	}

	public List<IEntrada> obtenerEntradasFuturas() {
		List<IEntrada> listaFuturas = new ArrayList<>();
		Fecha actual = Fecha.fechaActual();
		for (IEntrada e : entradas.values()) {
			if (e.obtenerFecha().esMayor(e.obtenerFecha(), actual)) {
				listaFuturas.add(e);
			}
		}
		return listaFuturas;
	}

	public boolean anularEntrada(IEntrada entrada) {
		return entradas.remove(entrada.obtenerCodigo() ,entrada);
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public HashMap<String, IEntrada> getEntradas() {
		return entradas;
	}

	public Fecha getFechaRegistro() {
		return fechaRegistro;
	}

	public boolean validarContrasenia(String contrasenia) {
		return this.contrasenia.equals(contrasenia);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("- ").append(email).append(" | ").append(nombre).append(" | ").append(apellido).append(" | ")
				.append(contrasenia).append(" \n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, contrasenia, email, entradas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email);
	}
}
