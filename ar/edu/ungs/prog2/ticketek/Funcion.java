package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Funcion {
	private Sede sede;
	private Fecha fecha;
	private double precioBase;
	
	public Funcion(Sede sede, Fecha fecha, double precioBase) {
		this.sede = sede;
		this.fecha = fecha;
		this.precioBase = precioBase;
	}
	// Getters
    public Sede obtenerSede() {
        return sede;
    }

    public Fecha obtenerFecha() {
        return fecha;
    }
    public double obtenerPrecioBase() {
        return precioBase;
    }
    // Setters
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
}
