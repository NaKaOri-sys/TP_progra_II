package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;

public class Fecha {
	private int dia;
	private int mes;
	private int anio;

	// constructor
	public Fecha(int dia, int mes, int anio) {
		if (!esFechaValida(dia, mes, anio)) {
			throw new IllegalArgumentException("Fecha inválida");
		}
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	// Validación básica (puedes mejorarla con más reglas)
	private boolean esFechaValida(int dia, int mes, int anio) {
		if (mes < 1 || mes > 12)
			return false;
		if (dia < 1 || dia > diasEnMes(mes, anio))
			return false;
		return true;
	}

	// Días por mes, incluyendo bisiesto
	private int diasEnMes(int mes, int anio) {
		switch (mes) {
		case 2:
			return esBisiesto(anio) ? 29 : 28;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			return 31;
		}
	}

//metodos de clase:
	public int ObtenerDia() {
		return dia;
	}

	public int ObtenerMes() {
		return mes;
	}

	public int ObtenerAnio() {
		return anio;
	}

	public String ObtenerFecha() {
		return dia + "/" + mes + "/" + anio;
	}

	public boolean esMayor(Fecha fecha, Fecha otrafecha) {

		if ((fecha.dia > otrafecha.dia && fecha.mes > otrafecha.mes && fecha.anio > otrafecha.anio)
				|| (fecha.dia < otrafecha.dia && fecha.mes > otrafecha.mes && fecha.anio > otrafecha.anio))
			return true;
		else
			return false;

	}

	public boolean esMenor(Fecha fecha, Fecha otrafecha) {

		if (esMayor(fecha, otrafecha))
			return false;
		else
			return true;

	}

	private boolean esBisiesto(int anio) {
		return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}
    
	public static Fecha fechaActual() {
        LocalDate hoy = LocalDate.now();
        return new Fecha(hoy.getDayOfMonth(), hoy.getMonthValue(), hoy.getYear());
    }
    //Getters
    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
    public int getAnio() {
        return anio;
    }
//	Setters   
    public void setDia(int dia) {
        if (!esFechaValida(dia, this.mes, this.anio)) {
            throw new IllegalArgumentException("Día inválido para la fecha actual.");
        }
        this.dia = dia;
    }
    
    public void setMes(int mes) {
        if (!esFechaValida(this.dia, mes, this.anio)) {
            throw new IllegalArgumentException("Mes inválido para la fecha actual.");
        }
        this.mes = mes;
    }
    public void setAnio(int anio) {
        if (!esFechaValida(this.dia, this.mes, anio)) {
            throw new IllegalArgumentException("Año inválido para la fecha actual.");
        }
        this.anio = anio;
    }
}

