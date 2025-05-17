package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.List;

public class Teatro extends Sede {
	private List<Sector> sectores;
	public Teatro(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		this.sectores =new ArrayList<Sector>(); 
	}

	@Override
	public double calcularPrecioBase() {
		// TODO Auto-generated method stub
		return 0;
	}

}
