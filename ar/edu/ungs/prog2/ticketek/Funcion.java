package ar.edu.ungs.prog2.ticketek;

public class Funcion {
	private Sede sede;
	private Fecha fecha;
	
	public Funcion(Sede sede, Fecha fecha) {
		this.sede = sede;
		this.fecha = fecha;
	}

	public Sede obtenerSede(String nombre) {
		Sede sedeObtenida = null;
		for (Sede s: sedes) {
			if(s.getNombre.equals(nombre)) {
				sedeObtenida = s;
				break;
			}
		}
		return sedeObtenida;
	}
	// Getters
    public Sede getSede() {
        return sede;
    }

    public Fecha getFecha() {
        return fecha;
    }

    // Setters
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
}