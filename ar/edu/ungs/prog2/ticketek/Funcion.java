package TP_progra_II.ar.edu.ungs.prog2.ticketek;
import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private Sede sede;
    private Fecha fecha;
    private double precioBase;
    private List<IEntrada> entradasVendidas;

    public Funcion(Sede sede, Fecha fecha, double precioBase) {
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
        this.entradasVendidas = new ArrayList<>();
    }
    public Sede obtenerSede() {
        return sede;
    }

    public Fecha obtenerFecha() {
        return fecha;
    }

    public double obtenerPrecioBase() {
        return precioBase;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    // Registrar una entrada vendida
    public void registrarEntrada(IEntrada entrada) {
        entradasVendidas.add(entrada);
    }

    public List<IEntrada> obtenerEntradasVendidas() {
        return entradasVendidas;
    }
    // Verifica si un asiento ya está ocupado (opcional)
    public boolean asientoOcupado(String sector, int fila, int asiento) {
        for (IEntrada e : entradasVendidas) {
            if (e.ubicacion().equals(sector + " f:" + fila + " a:" + asiento)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" - (").append(obtenerFecha().toString()).append(")")
          .append(obtenerSede().obtenerNombre()).append(" - ")
          .append(this.sede.obtenerInfoSectores(null));
        return sb.toString();
    }
}
