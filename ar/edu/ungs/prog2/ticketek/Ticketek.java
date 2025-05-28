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
		Fecha fechaFuncion = new Fecha(fecha); 
		Funcion funcion = espectaculo.obtenerFunciones().get(fechaFuncion);
		if (funcion != null) {
			throw new IllegalStateException("Solo puede haber una fecha por espectaculo.");
		}
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

	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		validarParametrosEntrada(nombreEspectaculo, fecha, email, contrasenia, cantidadEntradas);
		Usuario usuario = usuarios.get(email);
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		Fecha fechaEntrada = new Fecha(fecha);
		Funcion funcion = espectaculo.obtenerFuncion(fechaEntrada);
		Sede sede = funcion.obtenerSede();

		List<IEntrada> entradas = new ArrayList<>();

		for (int i = 0; i < cantidadEntradas; i++) {
			String codigo = Entrada.generarCodigo(8);
			Entrada entrada = new Entrada(codigo, espectaculo, fechaEntrada, sede, "Campo", email);
			entradas.add(entrada);

			// Registrar la entrada vendida en la función
			funcion.registrarEntrada(entrada, "Campo");
		}
		return entradas;
	}

	@Override
	// Vende una o varias entradas a un usuario para funciones con sedes numeradas.

	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		validarParametrosEntradaEnumeradas(nombreEspectaculo, fecha, email, contrasenia, sector, asientos);
		Usuario usuario = usuarios.get(email);
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		Fecha fechaEntrada = new Fecha(fecha);
		Funcion funcion = espectaculo.obtenerFuncion(fechaEntrada);
		Sede sede = funcion.obtenerSede();

		List<IEntrada> entradas = new ArrayList<>();

		for (int i = 0; i < asientos.length; i++) {

			String codigo = Entrada.generarCodigo(8);
			Entrada entrada = new Entrada(codigo, espectaculo, fechaEntrada, sede, sector, 1, asientos[i], email);
			entradas.add(entrada);

			// Registrar la entrada vendida en la función
			funcion.registrarEntrada(entrada, sector);
		}
		return entradas;
	}

	private void validarParametrosEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		if (!usuarios.containsKey(email))
			throw new IllegalStateException("Usuario no registrado");
		if (!espectaculos.containsKey(nombreEspectaculo))
			throw new IllegalStateException("Espectáculo no registrados");
		if (!usuarios.get(email).validarContrasenia(contrasenia))
			throw new IllegalStateException("Contraseña inválida");
		if (cantidadEntradas <= 0)
			throw new IllegalArgumentException("Cantidad debe ser > 0");
	}

	private void validarParametrosEntradaEnumeradas(String nombreEspectaculo, String fecha, String email,
			String contrasenia, String sector, int[] asientos) {
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
		return usuario.obtenerEntradasFuturas();
	}

	private void validarUsuario(String email, String contrasenia) {
		if (!usuarios.containsKey(email))
			throw new IllegalStateException("Usuario no registrado");
		if (!usuarios.get(email).validarContrasenia(contrasenia))
			throw new IllegalStateException("Contraseña inválida");
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		validarUsuario(email, contrasenia);
		Usuario usuario = usuarios.get(email);
		return new ArrayList<>(usuario.getEntradas());
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		if (!(entrada instanceof Entrada)) {
			throw new IllegalArgumentException("Tipo de entrada desconocido.");
		}

		String email = ((Entrada) entrada).obtenerEmailComprador();
		validarUsuario(email, contrasenia);

		Fecha hoy = Fecha.fechaActual();
		if (!hoy.esMenor(hoy, ((Entrada) entrada).obtenerFecha())) {
			return false;
		}
		if (!entrada.ubicacion().equals("Campo")) {
			Espectaculo espectaculo = espectaculos.get(((Entrada) entrada).obtenerNombre());
			Funcion funcion = espectaculo.obtenerFuncion(((Entrada) entrada).obtenerFecha());
			funcion.liberarAsiento(entrada);
		}
		return true;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fechaNuevaStr) {

		Entrada original = (Entrada) entrada;

		String email = original.obtenerEmailComprador();
		validarUsuario(email, contrasenia);

		Fecha hoy = Fecha.fechaActual();
		if (!hoy.esMenor(hoy, original.obtenerFecha())) {
			throw new IllegalStateException("La entrada original ya pasó");
		}
		Espectaculo espectaculo = espectaculos.get(original.obtenerNombre());
		Fecha fechaNueva = Fecha.parse(fechaNuevaStr);
		Funcion nuevaFuncion = espectaculo.obtenerFuncion(fechaNueva);
		if (nuevaFuncion == null) {
			throw new IllegalStateException("No hay función en la fecha indicada");
		}

		// Crear nueva entrada (tipo no numerada)
		String nuevoCodigo = Entrada.generarCodigo(8);
		Entrada nuevaEntrada = new Entrada(nuevoCodigo, espectaculo, fechaNueva, nuevaFuncion.obtenerSede(), "Campo",
				email);

		// Registrar nueva entrada
		nuevaFuncion.registrarEntrada(nuevaEntrada, "Campo");
		usuarios.get(email).comprarEntradas(List.of(nuevaEntrada));

		// Anular la anterior
		anularEntrada(original, contrasenia);
		return nuevaEntrada;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fechaNuevaStr, String sector,
			int asiento) {
		if (!(entrada instanceof Entrada)) {
			throw new IllegalArgumentException("Tipo de entrada no válido");
		}
		Entrada original = (Entrada) entrada;

		String email = original.obtenerEmailComprador();
		validarUsuario(email, contrasenia);

		Fecha hoy = Fecha.fechaActual();
		if (!hoy.esMenor(hoy, original.obtenerFecha())) {
			throw new IllegalStateException("La entrada original ya pasó");
		}
		Espectaculo espectaculo = espectaculos.get(original.obtenerNombre());
		Fecha fechaNueva = Fecha.parse(fechaNuevaStr);
		Funcion nuevaFuncion = espectaculo.obtenerFuncion(fechaNueva);
		if (nuevaFuncion == null) {
			throw new IllegalStateException("No hay función en la fecha indicada");
		}

		Sector nuevoSector = nuevaFuncion.obtenerSede().obtenerSectores().get(sector);

		if (nuevoSector == null) {
			throw new IllegalStateException("El sector indicado no existe");
		}
		int fila = nuevoSector.calcularFila(asiento);

		if (nuevaFuncion.asientoOcupado(sector, fila, asiento)) {
			throw new IllegalStateException("El asiento está ocupado");
		}
		String nuevoCodigo = Entrada.generarCodigo(8);
		Entrada nuevaEntrada = new Entrada(nuevoCodigo, espectaculo, fechaNueva, nuevaFuncion.obtenerSede(), sector,
				fila, asiento, email);

		nuevaFuncion.registrarEntrada(nuevaEntrada, sector);
		usuarios.get(email).comprarEntradas(List.of(nuevaEntrada));

		anularEntrada(original, contrasenia);

		return nuevaEntrada;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		validarParametrosCostoEntrada(nombreEspectaculo, fecha);
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		Fecha fechaFuncion = new Fecha(fecha);
		Funcion funcion = espectaculo.obtenerFuncion(fechaFuncion);
		HashMap<String, Sector> sectores = funcion.obtenerSede().obtenerSectores();
		Sector sectorFuncion = sectores.get("Campo");
		return funcion.obtenerSede().calcularPrecioBase(sectorFuncion, funcion.obtenerPrecioBase());
	}

	private void validarParametrosCostoEntrada(String nombreEspectaculo, String fecha) {
		if (nombreEspectaculo == null || nombreEspectaculo.isEmpty()) {
			throw new IllegalArgumentException("El nombre del espectaculo es requerido para consultar costo.");
		}
		if (!espectaculos.containsKey(nombreEspectaculo)) {
			throw new IllegalArgumentException("El espectaculo solicitado no se encuentra registrado.");
		}
		if (fecha == null || fecha.isEmpty()) {
			throw new IllegalArgumentException("La fecha es requerida para consultar costo.");
		}
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		validarParametrosCostoEntradaEnumerada(nombreEspectaculo, fecha, sector);
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		Fecha fechaFuncion = new Fecha(fecha);
		Funcion funcion = espectaculo.obtenerFuncion(fechaFuncion);
		HashMap<String, Sector> sectores = funcion.obtenerSede().obtenerSectores();
		Sector sectorFuncion = sectores.get(sector);
		return funcion.obtenerSede().calcularPrecioBase(sectorFuncion, funcion.obtenerPrecioBase());
	}

	private void validarParametrosCostoEntradaEnumerada(String nombreEspectaculo, String fecha, String sector) {
		validarParametrosCostoEntrada(nombreEspectaculo, fecha);
		if (sector == null || sector.isBlank()) {
			throw new IllegalArgumentException("El sector es requerido para consultar costo.");
		}
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		Espectaculo espectaculo = obtenerYValidarEspectaculo(nombreEspectaculo);
		return calcularRecaudacionParaFunciones(new ArrayList<>(espectaculo.obtenerFunciones().values()));
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		Espectaculo espectaculo = obtenerYValidarEspectaculo(nombreEspectaculo);
		if (nombreSede == null || nombreSede.isEmpty()) {
			throw new IllegalArgumentException("La sede es requerida para consultar el total recaudado.");
		}

		List<Funcion> funcionesEnSede = new ArrayList<>();
		for (Funcion funcion : espectaculo.obtenerFunciones().values()) {
			if (funcion.obtenerSede().obtenerNombre().equals(nombreSede)) {
				funcionesEnSede.add(funcion);
			}
		}
		return calcularRecaudacionParaFunciones(funcionesEnSede);
	}

	private Espectaculo obtenerYValidarEspectaculo(String nombreEspectaculo) {
		if (nombreEspectaculo == null || nombreEspectaculo.isEmpty()) {
			throw new IllegalArgumentException("El nombre del espectáculo es requerido.");
		}
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		if (espectaculo == null) {
			throw new IllegalArgumentException(
					"El espectáculo '" + nombreEspectaculo + "' no se encuentra registrado.");
		}
		return espectaculo;
	}

	private double calcularRecaudacionParaFunciones(List<Funcion> funciones) {
		double totalRecaudado = 0;
		for (Funcion funcion : funciones) {
			for (IEntrada entrada : funcion.obtenerEntradasVendidas()) {
				totalRecaudado += entrada.precio();
			}
		}
		return totalRecaudado;
	}
}