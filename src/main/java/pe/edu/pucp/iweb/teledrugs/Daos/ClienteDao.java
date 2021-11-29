package pe.edu.pucp.iweb.teledrugs.Daos;


import pe.edu.pucp.iweb.teledrugs.Beans.BCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOBuscarProductoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOCarritoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOPedidoCliente;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClienteDao {
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/mydb";
    HashMap<String, String> carrito = new HashMap<>();
    //FUNCION PARA VALIDAR NOMBRE Y APELLIDOS
    public boolean nombreyApellidoValid(String nombre) {
        String regex = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:[\\]]]{1,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    //FUNCION PARA VALIDAR DNI
    public boolean dniValid(String dni) {
        String regex = "^[0-9]{8,8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dni);
        return matcher.find();
    }

    //FUNCION PARA VALIDAR FECHA
    public boolean fechaIsValid(String fecha) {
        String regex = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fecha);
        return matcher.find();
    }
    public boolean emailisValid(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //FUNCION QUE REGISTRA A UN USUARIO YA HAY CORREO
    public void registrarDatosUsuario(String logueo_correo, String dni, String nombre, String apellidos,String fecha,String distrito) {
        String sqlInsert = "INSERT INTO cliente(dni,nombre,apellidos,fecha_nac,distrito,logueo_correo)\n" +
                "VALUES(?,?,?,?,?,?)";
        if (dniValid(dni) && nombreyApellidoValid(nombre) && nombreyApellidoValid(apellidos) && fechaIsValid(fecha)) {
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(2, nombre);
                pstmt.setString(3, apellidos);
                pstmt.setString(4, fecha);
                pstmt.setString(5, distrito);
                pstmt.setString(6, logueo_correo);
                pstmt.setString(1, dni);
                pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void registrarCliente(BCliente clientito) {
        String sqlInsert = "INSERT INTO cliente(dni,nombre,apellidos,fecha_nac,distrito,logueo_correo)\n" +
                "VALUES(?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1,clientito.getDNI());
            pstmt.setString(2,clientito.getNombre());
            pstmt.setString(3,clientito.getApellidos());
            pstmt.setString(4,clientito.getFechaNacimiento());
            pstmt.setString(5,clientito.getDistrito());
            pstmt.setString(6,clientito.getLogueoCorreo());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    //FUNCION QUE ELIMINA A UN CLIENTE CON SU CORREO
    public void eliminarCliente(String correo) {
        String sql = "DELETE c FROM cliente c  WHERE ? = c.logueo_correo;";
        boolean bandera = false;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, correo);
            String sqlBusqueda = "SELECT logueo_correo FROM cliente";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlBusqueda);) {
                while (rs.next()) {
                    String logueo_correo = rs.getString(1);
                    if (logueo_correo.equalsIgnoreCase(correo)) {
                        bandera = true;
                        break;
                    }
                }
                if (!bandera) {
                    System.out.println("El correo no existe");
                } else {
                    pstmt.executeUpdate();
                    System.out.println("Se ha eliminado correctamente el cliente");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //FUNCION QUE MUESTRA PERFIL DE CLIENTE
    public BCliente mostrarPerfil(String correo) {
        String sql = "SELECT c.dni,c.nombre,c.apellidos,c.distrito, c.fecha FROM cliente c  WHERE ? = c.logueo_correo";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String dni = rs.getString(1);
            String nombre = rs.getString(2);
            String apellidos = rs.getString(3);
            String distrito = rs.getString(4);
            BCliente bCliente = new BCliente(dni,nombre,apellidos,distrito,correo);
            return bCliente;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String DNI(String correo) {
        String sql = "SELECT c.dni FROM cliente c WHERE ? = c.logueo_correo";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String dni = rs.getString(1);
            return dni;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //FUNCION QUE MUESTAR HISTORIAL DE PEDIDOS
    public ArrayList<DTOPedidoCliente> mostrarHistorial(String DNI) {
        ArrayList<DTOPedidoCliente> pedidos = new ArrayList<>();
        String sql = "SELECT truncate(sum(pr.precio*pt.cantidad),1),sum(pt.cantidad),p.numeroOrden , p.estado , f.nombre FROM pedidos p\n" +
                "INNER JOIN producto_tiene_pedidos pt ON pt.pedidos_numeroOrden=p.numeroOrden\n" +
                "INNER JOIN producto pr ON pr.idProducto=pt.producto_idProducto\n" +
                "INNER JOIN farmacia f ON pr.farmacia_ruc = f.ruc\n" +
                "WHERE p.usuarioDni = ? GROUP BY p.numeroOrden;";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, DNI);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                double resumenPago = rs.getDouble(1);
                int cantidad = rs.getInt(2);
                int numeroOrden = rs.getInt(3);
                String estado = rs.getString(4);
                String farmacia = rs.getString(5);
                pedidos.add(new DTOPedidoCliente(numeroOrden,cantidad, estado,resumenPago,farmacia));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pedidos;
    }


    //FUNCION VER EL CARRITO DE COMPRAS
    public void verCarrito() {
        String sql = "SELECT idProducto,nombre,requiereReceta,foto,stock,precio FROM producto p\n" +
                "WHERE idProducto=?;";
        Iterator<String> it = carrito.keySet().iterator();
        System.out.println("IdProducto   |   Nombre del Producto  | Requiere Receta  |  Foto  |   Stock    |  Precio  | Cantidad");
        while (it.hasNext()) {
            String key = it.next();
            String value = carrito.get(key);
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, key);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                String idProducto = rs.getString(1);
                String nombre = rs.getString(2);
                boolean requiereReceta = rs.getBoolean(3);
                String foto = rs.getString(4);
                String stock = rs.getString(5);
                double precio = rs.getDouble(6);
                System.out.println(idProducto + "||" + nombre + "||" + requiereReceta + "||" + foto + "||" + stock + "||" + precio + "||" + value);
                System.out.println("------------------------------------------------------------------------");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //FUNCION PARA AÑADIR UN PRODUCOT AL CARRITO

    public void agregarAlCarrito( String cantidad,String idProducto,String stock,String preciototal){
        String sql="INSERT INTO carrito (cantidad,idProducto,stock,preciototal) VALUES (?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,cantidad);
            pstmt.setString(2,idProducto);
            pstmt.setString(3,stock);
            pstmt.setString(4,preciototal);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //FUNCION PARA BUSCAR UN PRODUCTO

    public ArrayList<DTOBuscarProductoCliente> buscarProducto(String ruc, String nombre) {
        ArrayList<DTOBuscarProductoCliente> productos = new ArrayList<>();

        nombre = nombre.toLowerCase();

        String sql = "SELECT p.nombre,p.descripcion,p.foto,p.precio,p.idProducto FROM producto p INNER JOIN farmacia f ON f.ruc = p.farmacia_ruc WHERE f.ruc = ? AND lower(p.nombre) LIKE ?;";


        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,ruc);
            pstmt.setString(2,"%" + nombre + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    DTOBuscarProductoCliente producto = new DTOBuscarProductoCliente();
                    producto.setNombre(rs.getString(1));
                    producto.setDescripcion(rs.getString(2));
                    producto.setFoto(rs.getString(3));
                    producto.setPrecio(rs.getDouble(4));
                    producto.setIdProducto(rs.getInt(5));
                    productos.add(producto);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productos;
    }
    public DTOBuscarProductoCliente buscarProductoporId(String ruc, int idProducto) {
        DTOBuscarProductoCliente producto = null;
        String sql = "SELECT p.nombre,p.descripcion,p.foto,p.precio,p.idProducto,p.stock FROM producto p INNER JOIN farmacia f ON f.ruc = p.farmacia_ruc WHERE f.ruc = ? AND p.idProducto = ?;";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,ruc);
            pstmt.setInt(2,idProducto);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            producto = new DTOBuscarProductoCliente();
            producto.setNombre(rs.getString(1));
            producto.setDescripcion(rs.getString(2));
            producto.setFoto(rs.getString(3));
            producto.setPrecio(rs.getDouble(4));
            producto.setIdProducto(rs.getInt(5));
            producto.setStock(rs.getString(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public BCliente updatePerfil(String nombre, String Apellido, String distrito, String correo){
        String sql="UPDATE cliente c SET c.nombre = ?, c.apellidos = ?, c.distrito = ? WHERE c.logueo_correo= ?;";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,Apellido);
            pstmt.setString(3,distrito);
            pstmt.setString(4,correo);
            pstmt.executeUpdate();
            BCliente bCliente = mostrarPerfil(correo);
            return bCliente;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




    public String obtenerIDCliente (String correo){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String DNI = null;  //IGUAL SE LE VA ALLENAR EL CAMPO YA QUE SE VALIDO QUE EXISTE UNA PERSONA CON DICHO DNI

        String sentenciaSQL = "SELECT dni,nombre,apellidos ,logueo_correo FROM cliente\n"+
                "INNER JOIN credenciales ON (cliente.logueo_correo = credenciales.correo)\n"+
                "WHERE logueo_correo = ?;\n";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {

            pstmt.setString(1,correo);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    DNI = rs.getString(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return DNI;
    }


    public boolean existeCliente(String correo, String dni){
        Boolean existe = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql="SELECT * FROM cliente WHERE dni = ? AND logueo_correo = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,dni);
            pstmt.setString(2,correo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                existe=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return existe;
    }
   /* public ArrayList<DTOCarritoCliente> buscarCarrito(String ruc){
        ArrayList<DTOCarritoCliente> listaCarrito = new ArrayList<>();
        String sqlselect="SELECT * FROM carrito";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt=conn.prepareStatement(sqlselect)){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String cantidad = rs.getString(2);
                String idProducto = rs.getString(3);
                DTOBuscarProductoCliente bBuscarProductoCliente = buscarProductoporId(ruc,Integer.parseInt(idProducto));
                String stock = rs.getString(4);
                String preciototal = rs.getString(5);
                listaCarrito.add(new DTOCarritoCliente(rs.getInt(1),bBuscarProductoCliente.getNombre(),idProducto,stock,Integer.parseInt(cantidad),Double.parseDouble(preciototal),bBuscarProductoCliente.getFoto()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCarrito;
    }*/
    /*public void borrarCarrito(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql ="DELETE FROM carrito";
        try(Connection conn= DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void borrarCarritoporId(int idCarrito){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(idCarrito);
        String sql ="DELETE FROM carrito WHERE (idcarrito = ?);";
        try(Connection conn= DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idCarrito);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
