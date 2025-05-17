package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.List;

public class MiniEstadioCubierto extends Sede {
	private List<Sector> sectores;
	public MiniEstadioCubierto(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecioBase() {
		// TODO Auto-generated method stub
		return 0;
	}
}
