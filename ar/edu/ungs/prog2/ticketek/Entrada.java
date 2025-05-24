package ar.edu.ungs.prog2.ticketek;

public class Entrada {
    private String codigo;
    private String espectaculoNombre;
    private Fecha fecha;
    private String ubicacion;

    // Constructor
    public Entrada(String codigo, String espectaculoNombre, Fecha fecha, String ubicacion) {
        this.codigo = codigo;
        this.espectaculoNombre = espectaculoNombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    // Métodos existentes
    public String obtenerNombre() {
        return espectaculoNombre;
    }

    public String obtenerFecha() {
        return fecha.ObtenerFecha();
    }

    public String obtenerUbicacion() {
        return ubicacion;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getEspectaculoNombre() {
        return espectaculoNombre;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEspectaculoNombre(String espectaculoNombre) {
        this.espectaculoNombre = espectaculoNombre;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

