package TP_progra_II.ar.edu.ungs.prog2.ticketek;

import java.util.Random;

public class Entrada implements IEntrada {
    private String codigo;
    private String espectaculoNombre;
    private Fecha fecha;
    private Sede sede;
    private String ubicacion;
    private String sector;
    private int fila;
    private int asiento;
    private String emailComprador;

    // Constructor para no numeradas
    public Entrada(String codigo, String espectaculoNombre, Fecha fecha, Sede sede, String ubicacion,String emailComprador) {
        this.codigo = codigo;
        this.espectaculoNombre = espectaculoNombre;
        this.fecha = fecha;
        this.sede = sede;
        this.ubicacion = ubicacion;
        this.emailComprador = emailComprador;
    }

    // Constructor para numeradas
    public Entrada(String codigo, String espectaculoNombre, Fecha fecha, Sede sede, String sector, int fila, int asiento,String emailComprador ) {
        this.codigo = codigo;
        this.espectaculoNombre = espectaculoNombre;
        this.fecha = fecha;
        this.sede = sede;
        this.sector = sector;
        this.fila = fila;
        this.asiento = asiento;
        this.ubicacion = sector + " f:" + fila + " a:" + asiento;
        this.emailComprador = emailComprador;
    }

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
    
    public String obtenerEmailComprador() {
        return emailComprador;
    }
    
    public String ubicacion() {
    	if (ubicacion.equals("CAMPO")) {
            return "CAMPO";
        } else {
            return sector + " f:" + fila + " a:" + asiento;
        }
    }
    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Fecha hoy = Fecha.fechaActual();
        sb.append(codigo).append(" - ").append(espectaculoNombre).append(" - ");
        sb.append(fecha.toString());
        if (fecha.esMayor(fecha, hoy)) {
            sb.append(" P");
        }
        sb.append(" - ").append(sede.toString()).append(" - ").append(ubicacion());
        return sb.toString();
    }

    public static String generarCodigo(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entrada other = (Entrada) obj;
        return codigo.equals(other.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}


