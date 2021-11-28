package pe.edu.pucp.iweb.teledrugs.Beans;

public class BCliente {

    private String DNI;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String distrito;
    private String logueoCorreo;

    public BCliente() {
    }

    public BCliente(String DNI, String nombre, String apellidos, String distrito, String logueoCorreo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.distrito = distrito;
        this.logueoCorreo = logueoCorreo;
    }

    public BCliente(String DNI, String nombre, String apellidos, String distrito,String fechaNacimiento, String logueoCorreo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.distrito = distrito;
        this.logueoCorreo = logueoCorreo;
        this.fechaNacimiento=fechaNacimiento;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLogueoCorreo() {
        return logueoCorreo;
    }

    public void setLogueoCorreo(String logueoCorreo) {
        this.logueoCorreo = logueoCorreo;
    }

}
