package TP_progra_II.ar.edu.ungs.prog2.ticketek;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Set<Entrada> entradas;
    private Fecha fechaRegistro;

    public Usuario(String email, String nombre, String apellido, String contrasenia, Fecha fechaRegistro) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
        this.entradas = new HashSet<>();
    }

    public String obtenerEntradas() {
        return entradas.toString();
    }

    public void comprarEntradas(List<Entrada> nuevasEntradas) {
        entradas.addAll(nuevasEntradas);
    }

    public Entrada obtenerEntrada(String codigo) {
        for (Entrada e : entradas) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public List<IEntrada> obtenerEntradas(List<Entrada> entradasFuturas) {
        List<IEntrada> listaEntradas = new ArrayList<>();
        Fecha fechaActual = Fecha.fechaActual();
        for (Entrada e : entradasFuturas) {
            if (fechaActual.esMayor(fechaRegistro, fechaActual)) {
                listaEntradas.add(e);
            }
        }
        return listaEntradas;
    }

    public List<IEntrada> obtenerEntradasFuturas() {
        List<IEntrada> listaFuturas = new ArrayList<>();
        Fecha actual = Fecha.fechaActual();
        for (Entrada e : entradas) {
            if (e.obtenerFecha().esMayor(e.obtenerFecha(), actual)) {
                listaFuturas.add(e);
            }
        }
        return listaFuturas;
    }

    public boolean anularEntrada(Entrada entrada) {
        return entradas.remove(entrada); // O(1)
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

    public Set<Entrada> getEntradas() {
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
        return "Usuario [email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenia="
                + contrasenia + ", entradas=" + entradas + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellido, contrasenia, email, entradas, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(email, other.email);
    }
}
