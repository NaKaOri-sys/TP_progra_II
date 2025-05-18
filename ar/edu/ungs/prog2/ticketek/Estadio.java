package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {
	private static Sector CAMPO;
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		CAMPO = new Sector("Campo");
	}
	
	public Sector obtenerSector() {
		return CAMPO;
	}

	@Override
	public double calcularPrecioBase(Sector sector) throws Exception {
		if (!sector.equals(CAMPO)) {
			throw new Exception("Estadio solo puede tener sector CAMPO.");
		}
		return obtenerPrecioBase();
	}
}
