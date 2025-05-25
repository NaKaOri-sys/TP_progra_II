package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class MiniEstadioCubierto extends Sede {
	HashMap<String, Sector> sectoresRegistrados;
	private int puestosMerchandising;
	private int puestosComidaRapida;
	private double precioConsumicion;

	public MiniEstadioCubierto(String nombre, String direccion, int capacidadMaxima, double precioConsumicion,
			String[] tipoSectores, int[] capacidadMaxPorSector, int asientosPorFila, 
			int[] porcentajeIncrementoSectores) throws Exception {
		super(nombre, direccion, capacidadMaxima);
		this.sectoresRegistrados = new HashMap<String, Sector>();
		this.precioConsumicion = precioConsumicion;
		inicializarSectores(tipoSectores, capacidadMaxPorSector, asientosPorFila, porcentajeIncrementoSectores);
	}

	private void inicializarSectores(String[] tipoSectores, int[] capacidadMaxPorSector, int asientosPorFila, int[] porcentajeIncrementoSectores) throws Exception {
		if (tipoSectores.length == 0 || capacidadMaxPorSector.length == 0 || porcentajeIncrementoSectores.length == 0
				|| capacidadMaxPorSector.length != tipoSectores.length
				|| porcentajeIncrementoSectores.length != tipoSectores.length) {
			throw new Exception("Para poder inicializar los sectores, los tipos de sectores deben tener la misma cantidad de elementos que capacidadMaxPorSector y porcentajeIncrementoSectores");
		}
		for (int i = 0; i < tipoSectores.length; i++) {
			sectoresRegistrados.put(tipoSectores[i], new Sector(tipoSectores[i],
					asientosPorFila, capacidadMaxPorSector[i],porcentajeIncrementoSectores[i]));			
		}
	}

	@Override
	public double calcularPrecioBase(Sector sector) throws Exception {
		if (!sectoresRegistrados.containsKey(sector.obtenerTipo())) {
			throw new Exception("El sector ingresado no existe.");
		}
		return sector.obtenerIncremento() + obtenerPrecioBase() + obtenerPrecioConsumicion();
	}

	public double obtenerPrecioConsumicion() {
		return precioConsumicion;
	}

	public HashMap<String, Sector> obtenerSectores() {
		return sectoresRegistrados;
	}

	public Sector obtenerSector(String tipo) throws Exception {
		if (!sectoresRegistrados.containsKey(tipo)) {
			throw new Exception("El sector a buscar no existe.");
		}
		Sector sector = sectoresRegistrados.get(tipo);
		return sector;
	}

	public int obtenerPuestosMerchandising() {
		return puestosMerchandising;
	}

	public int obtenerPuestosComidaRapida() {
		return puestosComidaRapida;
	}
}
