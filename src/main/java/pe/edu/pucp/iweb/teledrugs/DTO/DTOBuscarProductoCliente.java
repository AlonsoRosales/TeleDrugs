package pe.edu.pucp.iweb.teledrugs.DTO;

public class DTOBuscarProductoCliente {


    private int idProducto;
    private String nombre;
    private String descripcion;
    private String foto;
    private double precio;
    private String stock;
    private boolean requiereReceta;
    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }



    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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



}


