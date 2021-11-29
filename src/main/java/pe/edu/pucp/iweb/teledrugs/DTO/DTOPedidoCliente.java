package pe.edu.pucp.iweb.teledrugs.DTO;

public class DTOPedidoCliente {

    private int cantidad;
    private String estado;
    private double resumenPago;
    private String farmacia;
    private int numeroOrden;
    private String fecha;

    public DTOPedidoCliente(int numeroOrden, int cantidad, String estado, double resumenPago, String farmacia,String fecha) {
        this.numeroOrden = numeroOrden;
        this.cantidad = cantidad;
        this.estado = estado;
        this.resumenPago = resumenPago;
        this.farmacia = farmacia;
        this.fecha=fecha;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getResumenPago() {
        return resumenPago;
    }

    public void setResumenPago(double resumenPago) {
        this.resumenPago = resumenPago;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }





}
