package TP_progra_II.ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {
	
    private String codigo;
    private String espectaculoNombre;
    private Fecha fecha;
    private Sede sede;
    private String ubicacion;
    private String sector;
    private int fila;
    private int asiento;

	// Constructor
    public Entrada(String codigo, String espectaculoNombre, Fecha fecha, Sede sede ,String ubicacion) {
        this.codigo = codigo;
        this.espectaculoNombre = espectaculoNombre;
        this.fecha = fecha;
        this.sede = sede;
        this.ubicacion = ubicacion;
    }
    // Métodos existentes
    public String obtenerNombre() {
        return espectaculoNombre;
    }
    public Fecha obtenerFecha() {
        return fecha;
    }
    public String getCodigo() {
		return codigo;
	}
    public double precio() {
        return 0.0;
    }
    public String ubicacion() {
    	
    	 StringBuilder formatoSector = new StringBuilder();
    	 
		    if (ubicacion.equals("estadio")){
		    	formatoSector.append("CAMPO");
		    } 
		    else {
		    	formatoSector.append(sector).append(" f:").append(fila).append(" a:").append(asiento);
		    }
		    return formatoSector.toString();
    }

	public String toString() {

		    StringBuilder formatoEntrada = new StringBuilder();

		    // Parte 1: Código y nombre del espectáculo
		    formatoEntrada.append(codigo).append(" - ").append(espectaculoNombre).append(" - ");

		    // Agregamos Fecha y agregamos el detalle si la fecha ya paso
			Fecha hoy = Fecha.fechaActual();
			Fecha fechaEspectaculo = obtenerFecha();
			String fechaPasada = hoy.toString();
				if(fecha.esMayor(fechaEspectaculo,hoy)) {
					fechaPasada += " P";
		    }
				formatoEntrada.append(fechaPasada).append(" - ");

		    // Agregamos Sede
				formatoEntrada.append(sede.toString()).append(" - ");

		    // Agregamos ubicación y diferenciamos lo pedido en ubicacion
		    if (ubicacion.equals(ubicacion())) {
		    	formatoEntrada.append("CAMPO");
		    } else {
		    	formatoEntrada.append(ubicacion());
		    }

		    return formatoEntrada.toString();			
	}
}
