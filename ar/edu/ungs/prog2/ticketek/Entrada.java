package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import TP_progra_II.ar.edu.ungs.prog2.ticketek.IEntrada;


public class Entrada implements IEntrada {
    private String codigo;
    private String espectaculoNombre;
    private Fecha fecha;
    private String ubicacion;
    private Double precioEntrada;

    // Constructor
    public Entrada(String codigo, String espectaculoNombre, Fecha fecha, String ubicacion, Double precioEntrada) {
        this.codigo = codigo;
        this.espectaculoNombre = espectaculoNombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.precioEntrada=precioEntrada;
    }

    // Métodos existentes
    public String obtenerNombre() {
        return espectaculoNombre;
    }
    public String obtenerFecha() {
        return fecha.toString();
    }
    public String obtenerUbicacion() {
        return ubicacion;
    }
    public String getCodigo() {
        return codigo;
    }
    public double precio() {
        return precioEntrada;
    }

    @Override
	public String toString() {
		return " - " + codigo + " - " + espectaculoNombre + " - " + Funcion.Sede.nombre + "-" +funcion.fecha + " - " + ubicacion;
	}




}