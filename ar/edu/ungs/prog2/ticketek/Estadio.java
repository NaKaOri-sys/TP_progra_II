package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {
	private static String SECTOR = "CAMPO";
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima, precioBase);
	}
	
	public String obtenerSector() {
		return SECTOR;
	}
	
	@Override
	public double calcularPrecioBase(Sector sector) {
		return obtenerPrecioBase();
	}
}
