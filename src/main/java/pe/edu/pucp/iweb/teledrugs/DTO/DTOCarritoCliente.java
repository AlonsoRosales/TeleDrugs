package pe.edu.pucp.iweb.teledrugs.DTO;

import java.io.InputStream;
import java.sql.Blob;

public class DTOCarritoCliente {

    private int idcarrito;
    private String producto;
    private String codigo;
    private String stock;
    private int cantidad;
    private double precio;
    private String foto;
    private boolean requiereReceta;
    private InputStream receta;

    public DTOCarritoCliente(String producto, String codigo, String stock, int cantidad, double precio,String foto,boolean requiereReceta,InputStream receta) {
        this.producto = producto;
        this.codigo = codigo;
        this.stock = stock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
        this.requiereReceta=requiereReceta;
        this.receta=receta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdcarrito() {
        return idcarrito;
    }

    public void setIdcarrito(int idcarrito) {
        this.idcarrito = idcarrito;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public InputStream getReceta() {
        return receta;
    }

    public void setReceta(InputStream receta) {
        this.receta = receta;
    }
}
