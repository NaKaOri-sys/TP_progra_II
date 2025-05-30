package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MiniEstadioCubierto extends Sede {
	LinkedHashMap<String, Sector> sectoresRegistrados;
	private int puestosMerchandising;
	private int puestosComidaRapida;
	private double precioConsumicion;

	public MiniEstadioCubierto(String nombre, String direccion, int capacidadMaxima, double precioConsumicion,
			String[] tipoSectores, int[] capacidadMaxPorSector, int asientosPorFila,
			int[] porcentajeIncrementoSectores) {
		super(nombre, direccion, capacidadMaxima);
		this.sectoresRegistrados = new LinkedHashMap<String, Sector>();
		this.precioConsumicion = precioConsumicion;
		inicializarSectores(tipoSectores, capacidadMaxPorSector, asientosPorFila, porcentajeIncrementoSectores);
	}

	/**
	 * Función privada que se encarga de inicializar los sectores, en caso de que
	 * los parametros no tengan un sentido lógico o no correspondan se lanzara
	 * IllegalArgumentException
	 * 
	 * @param tipoSectores
	 * @param capacidadMaxPorSector
	 * @param asientosPorFila
	 * @param porcentajeIncrementoSectores
	 * @throws IllegalArgumentException
	 */
	private void inicializarSectores(String[] tipoSectores, int[] capacidadMaxPorSector, int asientosPorFila,
			int[] porcentajeIncrementoSectores) {
		validarParametrosSectores(tipoSectores, capacidadMaxPorSector, porcentajeIncrementoSectores);
		for (int i = 0; i < tipoSectores.length; i++) {
			sectoresRegistrados.put(tipoSectores[i], new Sector(tipoSectores[i], asientosPorFila,
					capacidadMaxPorSector[i], porcentajeIncrementoSectores[i]));
		}
	}

	/**
	 * Valida que los arrays de parámetros para la inicialización de sectores sean
	 * consistentes.
	 *
	 * @param tipoSectores                 Un array de tipos de sectores.
	 * @param capacidadMaxPorSector        Un array de capacidades máximas por
	 *                                     sector.
	 * @param porcentajeIncrementoSectores Un array de porcentajes de incremento por
	 *                                     sector.
	 * @throws IllegalArgumentException Si los arrays no tienen la misma longitud o
	 *                                  están vacíos.
	 */
	private void validarParametrosSectores(String[] tipoSectores, int[] capacidadMaxPorSector,
			int[] porcentajeIncrementoSectores) {
		if (tipoSectores == null || capacidadMaxPorSector == null || porcentajeIncrementoSectores == null) {
			throw new IllegalArgumentException(
					"Los arrays de tipos de sectores, capacidades o incrementos no pueden ser nulos.");
		}
		if (tipoSectores.length == 0 || capacidadMaxPorSector.length == 0 || porcentajeIncrementoSectores.length == 0) {
			throw new IllegalArgumentException(
					"Los arrays de tipos de sectores, capacidades o incrementos no pueden estar vacíos.");
		}
		if (capacidadMaxPorSector.length != tipoSectores.length
				|| porcentajeIncrementoSectores.length != tipoSectores.length) {
			throw new IllegalArgumentException(
					"Para poder inicializar los sectores, los tipos de sectores deben tener la misma cantidad de elementos que capacidadMaxPorSector y porcentajeIncrementoSectores.");
		}
	}

	@Override
	public double calcularPrecioBase(Sector sector, double precioBase) {
		if (!sectoresRegistrados.containsKey(sector.obtenerTipo())) {
			throw new IllegalArgumentException("El sector ingresado no existe.");
		}
		double calculoIncremento = precioBase * (sector.obtenerIncremento() / 100.0);
		return calculoIncremento + precioBase + obtenerPrecioConsumicion();
	}

	public double obtenerPrecioConsumicion() {
		return precioConsumicion;
	}

	@Override
	public HashMap<String, Sector> obtenerSectores() {
		return sectoresRegistrados;
	}

	/**
	 * Obtiene el sector en base al tipo enviado, en caso de que este no exista en
	 * los sectores registrados, lanza Exception
	 * 
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
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
	
	

	@Override
	public String obtenerInfoSectores(HashMap<String, Integer> entradasVendidasPorSector) {
		StringBuilder sb = new StringBuilder();
		boolean firstSector = true;
		for (Sector sector : this.sectoresRegistrados.values()) {
			if (!firstSector) {
				sb.append(" | ");
			}

			int entradasVendidas = entradasVendidasPorSector.getOrDefault(sector.obtenerTipo(), 0);
			int capacidadSector = sector.obtenerCapacidadMaxSector();

			sb.append(sector.obtenerTipo()).append(": ").append(entradasVendidas).append("/").append(capacidadSector);
			firstSector = false;
		}
		return sb.toString();
	}

	@Override
	public String listarSectores() {
		StringBuilder sb = new StringBuilder();
		for (Sector entry : sectoresRegistrados.values()) {
			sb.append(entry.toString());
		}
		return sb.toString();
	}
}