package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Teatro extends Sede {
	private HashMap<String,Sector> sectoresRegistrados;
	public Teatro(String nombre, String direccion, int capacidadMaxima, String[] sectores, int filasPorAsiento) {
		super(nombre, direccion, capacidadMaxima);
		this.sectoresRegistrados =new HashMap<String,Sector>(); 
	}

	public HashMap<String, Sector> obtenerSectores(){
		return sectoresRegistrados;
	}
	
	public Sector obtenerSector(String tipo) throws Exception{
		if (!sectoresRegistrados.containsKey(tipo)) {
			throw new Exception("El sector a buscar no existe.");
		}
		Sector sector = sectoresRegistrados.get(tipo);
		return sector;
	}

	@Override
	public double calcularPrecioBase(Sector sector) {
		return obtenerPrecioBase() + sector.obtenerIncremento();
	}
}
