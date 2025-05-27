package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ticketek implements ITicketek {
	private HashMap<String, Usuario> usuarios;
	private HashMap<String, Espectaculo> espectaculos;
	private HashMap<String, Sede> sedes;

	public Ticketek() {
		this.usuarios = new HashMap<String, Usuario>();
		this.espectaculos = new HashMap<String, Espectaculo>();
		this.sedes = new HashMap<String, Sede>();
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
		validarParametrosSede(nombre, direccion, capacidadMaxima);
		if (sedes.containsKey(nombre)) {
			throw new IllegalArgumentException("La sede ya se encuentra creada");
		}
		Sede sede = new Estadio(nombre, direccion, capacidadMaxima);
		sedes.put(nombre, sede);
		System.out.println(sede.toString() + " " + sede.listarSectores());
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		validarParametrosSede(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad,
				porcentajeAdicional);
		if (sedes.containsKey(nombre)) {
			throw new IllegalArgumentException("La sede ya se encuentra creada");
		}
		Sede sede = new Teatro(nombre, direccion, capacidadMaxima, sectores, capacidad, asientosPorFila,
				porcentajeAdicional);
		sedes.put(nombre, sede);
		System.out.println(sede.toString() + " " + sede.listarSectores());
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
		validarParametrosSede(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion,
				sectores, capacidad, porcentajeAdicional);
		if (sedes.containsKey(nombre)) {
			throw new IllegalArgumentException("La sede ya se encuentra creada");
		}
		Sede sede = new MiniEstadioCubierto(nombre, direccion, capacidadMaxima, precioConsumicion, sectores, capacidad,
				asientosPorFila, porcentajeAdicional);
		sedes.put(nombre, sede);
		System.out.println(sede.toString() + " " + sede.listarSectores());
	}

	private void validarParametrosSede(String nombre, String direccion, int capacidadMaxima) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre de la sede no puede ser nulo o vacío.");
		}
		if (direccion == null || direccion.trim().isEmpty()) {
			throw new IllegalArgumentException("La dirección de la sede no puede ser nula o vacía.");
		}
		if (capacidadMaxima <= 0) {
			throw new IllegalArgumentException("La capacidad máxima de la sede debe ser un número positivo.");
		}

	}

	private void validarParametrosSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		validarParametrosSede(nombre, direccion, capacidadMaxima); // Reutilizamos el primer método

		// Validaciones específicas de los sectores
		if (asientosPorFila <= 0) {
			throw new IllegalArgumentException("Los asientos por fila deben ser un número positivo.");
		}

		if (sectores == null || capacidad == null || porcentajeAdicional == null) {
			throw new IllegalArgumentException(
					"Los arrays de sectores, capacidad o porcentaje adicional no pueden ser nulos.");
		}

		if (sectores.length == 0 || capacidad.length == 0 || porcentajeAdicional.length == 0) {
			throw new IllegalArgumentException(
					"Los arrays de sectores, capacidad o porcentaje adicional no pueden estar vacíos.");
		}

		if (sectores.length != capacidad.length || sectores.length != porcentajeAdicional.length) {
			throw new IllegalArgumentException(
					"La cantidad de elementos en los arrays de sectores, capacidad y porcentaje adicional debe ser la misma.");
		}

		for (String tipoSector : sectores) {
			if (tipoSector == null || tipoSector.trim().isEmpty()) {
				throw new IllegalArgumentException("Los tipos de sector no pueden ser nulos o vacíos.");
			}
		}
		for (int i = 0; i < capacidad.length; i++) {
			if (capacidad[i] <= 0) {
				throw new IllegalArgumentException(
						"La capacidad de cada sector debe ser un número positivo. Error en el sector: "
								+ (sectores[i]));
			}
			if (porcentajeAdicional[i] < 0) { // O <= 0 si no puede ser cero
				throw new IllegalArgumentException(
						"El porcentaje adicional de cada sector no puede ser negativo. Error en el sector: "
								+ (sectores[i]));
			}
		}

		int sumaCapacidadesSectores = 0;
		for (int cap : capacidad) {
			sumaCapacidadesSectores += cap;
		}
		if (sumaCapacidadesSectores > capacidadMaxima) {
			throw new IllegalArgumentException("La suma de las capacidades de los sectores (" + sumaCapacidadesSectores
					+ ") , excede la capacidad máxima de la sede (" + capacidadMaxima + ").");
		}

	}

	private void validarParametrosSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
		validarParametrosSede(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad,
				porcentajeAdicional);

		if (cantidadPuestos < 0) {
			throw new IllegalArgumentException(
					"La cantidad de puestos (merchandising/comida rápida) no puede ser negativa.");
		}
		if (precioConsumicion < 0) {
			throw new IllegalArgumentException("El precio de la consumición no puede ser negativo.");
		}

	}

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {

		// Si el email ya está registrado, se debe lanzar una excepcion

		if (usuarios.containsKey(email)) {
			throw new IllegalStateException("El email ya está registrado.");
		}

		// Si algun dato no es aceptable, se debe lanzar una excepcion.
		if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
			throw new IllegalStateException("El Email ingresado es invalido.");
		}
		if (nombre == null) {
			throw new IllegalStateException("El Nombre ingresado no puede estar vacio.");
		}
		if (apellido == null) {
			throw new IllegalStateException("El Apellido ingresado no puede estar vacio.");
		}
		if (contrasenia == null) {
			throw new IllegalStateException("La contraña ingresado no puede estar vacio.");
		}
		// Registrar un nuevo usuario en el sistema
		Fecha fechaRegistro = Fecha.fechaActual();
		Usuario nuevo = new Usuario(email, nombre, apellido, contrasenia, fechaRegistro);
		usuarios.put(email, nuevo);
		System.out.println("Usuario registrado correctamente: " + email);
	}

	@Override
	public void registrarEspectaculo(String nombre) {
		if (nombre == null || nombre.trim().isEmpty())
			throw new IllegalArgumentException("El nombre del espectaculo no puede estar vacio.");
		if (espectaculos.containsKey(nombre))
			throw new IllegalArgumentException("El espectaculo ya se encuentra registrado.");
		Espectaculo espectaculo = new Espectaculo(nombre);
		espectaculo.registrarCodigo();
		espectaculos.put(nombre, espectaculo);
	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		validarParametrosFuncion(nombreEspectaculo, fecha, sede, precioBase);
		Sede sedeRegistrada = sedes.get(sede);
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		espectaculo.registrarFuncion(fecha, sedeRegistrada, precioBase);
	}

	private void validarParametrosFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		if (nombreEspectaculo == null || nombreEspectaculo.trim().isEmpty())
			throw new IllegalArgumentException("El nombre del espectaculo no puede estar vacio.");
		if (!espectaculos.containsKey(nombreEspectaculo))
			throw new IllegalArgumentException("El espectaculo debe encontrarse registrado.");
		if (!espectaculos.containsKey(nombreEspectaculo))
			throw new IllegalArgumentException("El espectaculo ingresado no se encuentra registrado.");
		if (!sedes.containsKey(sede))
			throw new IllegalArgumentException("La sede ingresada no se encuentra registrada");
		if (fecha == null || fecha.trim().isEmpty())
			throw new IllegalArgumentException("La fecha no puede encontrarse vacia");
		if (precioBase <= 0)
			throw new IllegalArgumentException("El precio debe ser un número positivo mayor a 0.");
	}

	@Override
	// Vende una o varias entradas a un usuario para funciones en sedes no
	// numeradas.

	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
	    validarParametrosEntrada(nombreEspectaculo, fecha, email, contrasenia, cantidadEntradas);
	    Usuario usuario = usuarios.get(email);
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
	    Fecha fechaEntrada = new Fecha(fecha);
	    Funcion funcion = espectaculo.obtenerFuncion(fechaEntrada);
	    Sede sede = funcion.obtenerSede();

	    List<IEntrada> entradas = new ArrayList<>();

	    for (int i = 0; i < cantidadEntradas; i++) {
	    	String emailComprador = usuario.getEmail();
	        String codigo = Entrada.generarCodigo(8);
	        Entrada entrada = new Entrada(codigo, nombreEspectaculo, fechaEntrada, sede, "Campo", emailComprador);
	        entradas.add(entrada);

	        // Registrar la entrada vendida en la función
	        funcion.registrarEntrada(entrada, "Campo");
	    }
	    return entradas;
	}
	@Override
	// Vende una o varias entradas a un usuario para funciones con sedes numeradas.

	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
	    validarParametrosEntradaEnumeradas(nombreEspectaculo, fecha, email, contrasenia, sector, asientos);
	    Usuario usuario = usuarios.get(email);
	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
	    Fecha fechaEntrada = new Fecha(fecha);
	    Funcion funcion = espectaculo.obtenerFuncion(fechaEntrada);
	    Sede sede = funcion.obtenerSede();

	    List<IEntrada> entradas = new ArrayList<>();

	    for (int i = 0; i < asientos.length; i++) {
	    	String emailComprador = usuario.getEmail();
	        String codigo = Entrada.generarCodigo(8);
	        Entrada entrada = new Entrada(codigo, nombreEspectaculo, fechaEntrada, sede, sector, 1, asientos[i], emailComprador);
	        entradas.add(entrada);

	        // Registrar la entrada vendida en la función
	        funcion.registrarEntrada(entrada, sector);
	    }
	    return entradas;
	}

	private void validarParametrosEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
	    if (!usuarios.containsKey(email)) throw new IllegalStateException("Usuario no registrado");
	    if (!espectaculos.containsKey(nombreEspectaculo)) throw new IllegalStateException("Espectáculo no registrados");
	    if (!usuarios.get(email).validarContrasenia(contrasenia)) throw new IllegalStateException("Contraseña inválida");
	    if (cantidadEntradas <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
	}

	private void validarParametrosEntradaEnumeradas(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
	    validarParametrosEntrada(nombreEspectaculo, fecha, email, contrasenia, asientos.length);

	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
	    Fecha fechaEntrada = new Fecha(fecha);
	    Sede sede = espectaculo.obtenerFuncion(fechaEntrada).obtenerSede();

	     if (!sede.obtenerSectores().containsKey(sector)) 
	        throw new IllegalArgumentException("El sector no existe en esta sede.");
	}


	@Override
	public String listarFunciones(String nombreEspectaculo) {
		if (!espectaculos.containsKey(nombreEspectaculo))
			throw new IllegalArgumentException("El espectaculo ingresado no se encuentra registrado.");
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		StringBuilder sb = new StringBuilder();
		for (Funcion entry : espectaculo.obtenerFunciones().values()) {
			sb.append(entry.toString());
		}
		return sb.toString(); 
	}

		@Override

	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
	    if (!espectaculos.containsKey(nombreEspectaculo)) {
	        throw new IllegalArgumentException("Espectáculo no registrado.");
	    }

	    Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
	    List<IEntrada> todasLasEntradas = new ArrayList<>();

	    for (Funcion funcion : espectaculo.obtenerFunciones().values()) {
	        todasLasEntradas.addAll(funcion.obtenerEntradasVendidas());
	    }

	   return todasLasEntradas;
	}
		    
	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
	    validarUsuario(email, contrasenia);
	    Usuario usuario = usuarios.get(email);

	    return usuario.obtenerEntradasFuturas(usuario.getEntradas());
	}

	private void validarUsuario(String email, String contrasenia) {
	    if (!usuarios.containsKey(email)) throw new IllegalStateException("Usuario no registrado");
	    if (!usuarios.get(email).validarContrasenia(contrasenia)) throw new IllegalStateException("Contraseña inválida");
	}
	
	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		validarUsuario(email, contrasenia);
	    Usuario usuario = usuarios.get(email);
	    return usuario.obtenerEntradas(usuario.getEntradas());
	}
	
	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		// TODO Auto-generated method stub
		return 0;
	}

}
