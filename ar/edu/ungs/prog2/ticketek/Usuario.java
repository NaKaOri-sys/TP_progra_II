package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.List;

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

    // Anular entrada por código
    public void anularEntrada(String codigo) {
        Entrada entradaAEliminar = null;
        for (Entrada e : entradas) {
            if (e.getCodigo().equals(codigo)) {
                entradaAEliminar = e;
                break;
            }
        }
        if (entradaAEliminar != null) {
            entradas.remove(entradaAEliminar);
            System.out.println("Entrada con código " + codigo + " anulada.");
        } else {
            System.out.println("No se encontró una entrada con el código: " + codigo);
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
            if (e.getFecha().esMayor(e.getFecha(), fechaActual)) {
                listaEntradasFuturas.add(e);
            }
        }
        return listaEntradasFuturas;
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

    //Setters
    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}


