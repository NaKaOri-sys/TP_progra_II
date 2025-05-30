package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Fecha implements Comparable<Fecha> {
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
	
	public Fecha(String fechaStr) throws IllegalArgumentException {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La cadena de fecha no puede ser nula o vacía.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            LocalDate localDate = LocalDate.parse(fechaStr, formatter);
            this.dia = localDate.getDayOfMonth();
            this.mes = localDate.getMonthValue();
            this.anio = localDate.getYear();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Se esperaba 'dd/MM/yy'. Error: " + e.getMessage(), e);
        }
    }

	// Validación básica
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

    public int compareTo(Fecha otraFecha) {
        if (this.anio != otraFecha.anio) {
            return Integer.compare(this.anio, otraFecha.anio);
        }
        if (this.mes != otraFecha.mes) {
            return Integer.compare(this.mes, otraFecha.mes);
        }
        return Integer.compare(this.dia, otraFecha.dia);
    }

    public boolean esPosteriorA(Fecha otraFecha) {
        return this.compareTo(otraFecha) > 0;
    }

    public boolean esAnteriorA(Fecha otraFecha) {
        return this.compareTo(otraFecha) < 0;
    }

    public boolean esIgualA(Fecha otraFecha) {
        return this.compareTo(otraFecha) == 0;
    }

    public boolean esMayorOIgual(Fecha otraFecha) {
        return this.compareTo(otraFecha) >= 0;
    }

    public boolean esMenorOIgual(Fecha otraFecha) {
        return this.compareTo(otraFecha) <= 0;
    }

    public boolean esMayor(Fecha fecha1, Fecha fecha2) {
        return fecha1.compareTo(fecha2) > 0;
    }

    public boolean esMenor(Fecha fecha1, Fecha fecha2) {
        return fecha1.compareTo(fecha2) < 0;
    }

	public static Fecha parse(String fechaStr) {
	    return new Fecha(fechaStr);
	}
	private boolean esBisiesto(int anio) {
		return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}
    
	public static Fecha fechaActual() {
        LocalDate hoy = LocalDate.now();
        return new Fecha(hoy.getDayOfMonth(), hoy.getMonthValue(), hoy.getYear());
    }

	@Override
	public String toString() {
		return String.format("%02d/%02d/%02d", dia, mes, anio % 100);
	}
	
	public String formatoFechaCorta() {
	    return String.format("%02d/%02d/%02d", dia, mes, anio % 100);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(anio, dia, mes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fecha other = (Fecha) obj;
		return anio == other.anio && dia == other.dia && mes == other.mes;
	}
}