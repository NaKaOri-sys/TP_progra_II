package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Teatro extends Sede {
	private HashMap<String,Sector> sectoresRegistrados;
	public Teatro(String nombre, String direccion, int capacidadMaxima, 
			String[] tipoSectores, int[] capacidadMaxPorSector, 
			int asientosPorFila, int[] porcentajeIncrementoSectores){
		super(nombre, direccion, capacidadMaxima);
		this.sectoresRegistrados =new HashMap<String,Sector>();
		inicializarSectores(tipoSectores, capacidadMaxPorSector, asientosPorFila, porcentajeIncrementoSectores);
	}
	
	/**
	 * Función privada que se encarga de inicializar los sectores en base a los parametros del constructor de la clase, en caso
	 * de que los parametros no tengan un sentido lógico o no correspondan se lanzara Exception
	 * @param tipoSectores
	 * @param capacidadMaxPorSector
	 * @param asientosPorFila
	 * @param porcentajeIncrementoSectores
	 * @throws IllegalArgumentException
	 */
	private void inicializarSectores(String[] tipoSectores, int[] capacidadMaxPorSector, int asientosPorFila, int[] porcentajeIncrementoSectores){
		validarParametrosSectores(tipoSectores, capacidadMaxPorSector, porcentajeIncrementoSectores);
		for (int i = 0; i < tipoSectores.length; i++) {
			sectoresRegistrados.put(tipoSectores[i], new Sector(tipoSectores[i],
					asientosPorFila, capacidadMaxPorSector[i],porcentajeIncrementoSectores[i]));			
		}
	}
	
	/**
	 * Valida que los arrays de parámetros para la inicialización de sectores sean consistentes.
	 *
	 * @param tipoSectores Un array de tipos de sectores.
	 * @param capacidadMaxPorSector  Un array de capacidades máximas por sector.
	 * @param porcentajeIncrementoSectores Un array de porcentajes de incremento por sector.
	 * @throws IllegalArgumentException Si los arrays no tienen la misma longitud o están vacíos.
	 */
	private void validarParametrosSectores(String[] tipoSectores, int[] capacidadMaxPorSector, int[] porcentajeIncrementoSectores) {
	    if (tipoSectores == null || capacidadMaxPorSector == null || porcentajeIncrementoSectores == null) {
	        throw new IllegalArgumentException("Los arrays de tipos de sectores, capacidades o incrementos no pueden ser nulos.");
	    }
	    if (tipoSectores.length == 0 || capacidadMaxPorSector.length == 0 || porcentajeIncrementoSectores.length == 0) {
	        throw new IllegalArgumentException("Los arrays de tipos de sectores, capacidades o incrementos no pueden estar vacíos.");
	    }
	    if (capacidadMaxPorSector.length != tipoSectores.length || porcentajeIncrementoSectores.length != tipoSectores.length) {
	        throw new IllegalArgumentException("Para poder inicializar los sectores, los tipos de sectores deben tener la misma cantidad de elementos que capacidadMaxPorSector y porcentajeIncrementoSectores.");
	    }
	}

	
	public HashMap<String, Sector> obtenerSectores(){
		return sectoresRegistrados;
	}
	
	/**
	 * Obtiene el sector en base al tipo enviado, en caso de que este no exista en los sectores registrados, lanza Exception
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public Sector obtenerSector(String tipo) {
		if (!sectoresRegistrados.containsKey(tipo)) {
			throw new IllegalArgumentException("El sector a buscar no existe.");
		}
		Sector sector = sectoresRegistrados.get(tipo);
		return sector;
	}

	@Override
	public double calcularPrecioBase(Sector sector) {
		if (!sectoresRegistrados.containsKey(sector.obtenerTipo())) {
			throw new IllegalArgumentException("El sector ingresado no existe.");
		}
		
		return obtenerPrecioBase() + sector.obtenerIncremento();
	}

	@Override
	public String toString() {
		return "Teatro [sectoresRegistrados=" + sectoresRegistrados + "]";
	}
	
}