package pe.edu.pucp.iweb.teledrugs.Beans;

import java.io.InputStream;

public class BFarmacia {
    private String ruc;
    private String nombre;
    private String direccion;
    private String distrito;
    private String bloqueado;
    private String pedidosPendientes;
    private InputStream fotos;
    private String correo;

    //VERIFICAR SI ESTE CONSTRUCTOR SE USA EN ALGO
    public BFarmacia(String ruc, String nombre, String direccion, String distrito, InputStream fotos, String correo) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.fotos = fotos;
        this.correo = correo;
    }

    public BFarmacia() {
    }

    public BFarmacia(String ruc, String nombre, String correo, String distrito, String bloqueado, String pedidosPendientes, String direccion) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.bloqueado = bloqueado;
        this.pedidosPendientes = pedidosPendientes;
        this.correo = correo;
    }


    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void setPedidosPendientes(String pedidosPendientes) {
        this.pedidosPendientes = pedidosPendientes;
    }

    public InputStream getFotos() {
        return fotos;
    }

    public void setFotos(InputStream fotos) {
        this.fotos = fotos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
