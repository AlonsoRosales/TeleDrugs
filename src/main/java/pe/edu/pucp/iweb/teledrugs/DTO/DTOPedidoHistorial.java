package pe.edu.pucp.iweb.teledrugs.DTO;

public class DTOPedidoHistorial {
    private String nombreCompleto;
    private String dni;
    private String estado;
    private String fechaRecojo;

    public DTOPedidoHistorial(String nombreCompleto, String dni, String estado, String fechaRecojo) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.estado = estado;
        this.fechaRecojo = fechaRecojo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRecojo() {
        return fechaRecojo;
    }

    public void setFechaRecojo(String fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
