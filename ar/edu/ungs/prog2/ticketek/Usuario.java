package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<Entrada> entradas;

    // Constructor
    public Usuario(String email, String nombre, String apellido, String contrasenia) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = new ArrayList<>();
    }

    // Obtener todas las entradas como String
    public String obtenerEntradas() {
        return entradas.toString();
    }

    // Comprar entradas
    public void comprarEntradas(List<Entrada> nuevasEntradas) {
        for (Entrada e : nuevasEntradas) {
            this.entradas.add(e);
        }
    }
    // Obtener entrada por código
    public Entrada obtenerEntrada(String codigo) {
        for (Entrada e : entradas) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    // Obtener solo las entradas futuras
    public List<Entrada> obtenerEntradasFuturas(List<Entrada> entradasFuturas) {
        List<Entrada> listaEntradasFuturas = new ArrayList<>();
        Fecha fechaActual = Fecha.fechaActual();
        for (Entrada e : entradasFuturas) {
            if (e.obtenerFecha().esMayor(e.obtenerFecha(), fechaActual)) {
                listaEntradasFuturas.add(e);
            }
        }
        return listaEntradasFuturas;
    }
    
    @Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenia="
				+ contrasenia + ", entradas=" + entradas + "]";
	}
    
	@Override
	public int hashCode() {
		return Objects.hash(apellido, contrasenia, email, entradas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(email, other.email) && Objects.equals(entradas, other.entradas)
				&& Objects.equals(nombre, other.nombre);
	}

	//Getters
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
    public List<Entrada> getEntradas() {
        return entradas;
    }
    public boolean validarContrasenia(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }
    
}