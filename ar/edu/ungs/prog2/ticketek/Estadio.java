package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		
	}
	


	@Override
	public double calcularPrecioBase() {
		return obtenerPrecioBase();
	}

}
