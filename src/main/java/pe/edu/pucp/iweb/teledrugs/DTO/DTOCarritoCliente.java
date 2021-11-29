package pe.edu.pucp.iweb.teledrugs.DTO;

public class DTOCarritoCliente {

    private int idcarrito;
    private String producto;
    private String codigo;
    private String stock;
    private int cantidad;
    private double precio;
    private String foto;

    public DTOCarritoCliente(String producto, String codigo, String stock, int cantidad, double precio,String foto) {
        this.producto = producto;
        this.codigo = codigo;
        this.stock = stock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
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
}
