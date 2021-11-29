package pe.edu.pucp.iweb.teledrugs.Daos;

import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Beans.BProducto;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOPedidoHistorial;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class FarmaciaDao extends BaseDao {


    public ArrayList<DTOPedidoHistorial> mostrarHistorialPedidos(){

        ArrayList<DTOPedidoHistorial> listaHistorialPedidos= new ArrayList<>();

        String sql ="select CONCAT(c.nombre,\" \",c.apellidos),c.dni,p.estado,p.fechaRecojo from cliente c inner join pedidos p on (c.dni = p.usuarioDni) order by p.fechaRecojo;";

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String nombreCompleto =rs.getString(1);
                String dni = rs.getString(2);
                String estado = rs.getString(3);
                String fechaRecojo = rs.getString(4);
                listaHistorialPedidos.add(new DTOPedidoHistorial(nombreCompleto,dni,estado,fechaRecojo));
                System.out.println(listaHistorialPedidos);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaHistorialPedidos;
    }


    public ArrayList<BProducto> listarProductos(String ruc){

        ArrayList<BProducto> bProductos = new ArrayList<>();
        String sql ="SELECT p.* FROM producto p \n" +
                "INNER JOIN farmacia f ON f.ruc=p.farmacia_ruc\n" +
                "WHERE f.ruc = ? \n" +
                "ORDER BY p.nombre";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,ruc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                int idProducto = rs.getInt(1);
                String nombre = rs.getString(2);
                String descripcion = rs.getString(3);
                String requiereReceta = rs.getString(4);
                String foto = rs.getString(5);
                String stock = rs.getString(6);
                double precio = rs.getDouble(7);
                String ruc2 = rs.getString(8);
                bProductos.add(new BProducto(idProducto,nombre,descripcion,foto,Integer.parseInt(stock),precio,ruc2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bProductos;
    }

    public ArrayList<BFarmacia> mostrarListaFarmacias(){

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        String sql ="SELECT f.ruc, f.nombre, l.correo, f.distrito,f.pedidosPendientes,bloqueado,f.direccion FROM farmacia f\n" +
                "INNER JOIN credenciales l ON l.correo = f.logueo_correo\n" +
                "INNER JOIN producto p ON p.farmacia_ruc=f.ruc\n" +
                "LEFT JOIN producto_tiene_pedidos pt ON p.idProducto = pt.producto_idProducto\n" +
                "GROUP BY f.ruc " +
                "ORDER BY f.distrito;";

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();){

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String ruc = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String distrito =  rs.getString(4);
                String pedidosPendientes1 = rs.getString(5);
                String bloqueado= rs.getString(6);
                String direccion = rs.getString(7);
                BFarmacia f = new BFarmacia(ruc, nombre,correo, distrito,bloqueado,pedidosPendientes1,direccion);
                listaFarmacias.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaFarmacias;
    }

    public ArrayList<BFarmacia> mostrarListaFarmaciasTotal(){

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        String sql ="SELECT f.ruc, f.nombre, f.logueo_correo, f.distrito,f.pedidosPendientes,f.bloqueado,f.direccion from farmacia f";
        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();){

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String ruc = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String distrito =  rs.getString(4);
                String pedidosPendientes1 = rs.getString(5);
                String bloqueado= rs.getString(6);
                String direccion = rs.getString(7);
                BFarmacia f = new BFarmacia(ruc, nombre,correo, distrito,bloqueado,pedidosPendientes1,direccion);
                listaFarmacias.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaFarmacias;
    }


    public BFarmacia mostrarFarmacia(String ruc){

        BFarmacia bFarmacia = null;
        String sql ="SELECT f.ruc, f.nombre, l.correo, f.distrito,f.pedidosPendientes,bloqueado,f.direccion FROM farmacia f\n" +
                "INNER JOIN credenciales l ON l.correo = f.logueo_correo\n" +
                "INNER JOIN producto p ON p.farmacia_ruc=f.ruc\n" +
                "LEFT JOIN producto_tiene_pedidos pt ON p.idProducto = pt.producto_idProducto\n" +
                "WHERE f.ruc = ? " +
                "GROUP BY f.ruc ";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,ruc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String ruc2 = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String distrito =  rs.getString(4);
                String pedidosPendientes1 = rs.getString(5);
                String bloqueado= rs.getString(6);
                String direccion = rs.getString(7);
                bFarmacia = new BFarmacia(ruc2, nombre,correo, distrito,bloqueado,pedidosPendientes1,direccion);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bFarmacia;
    }

    public void insertarFarmacia(BFarmacia farmacia, String contrasena){

        CredencialesDao credenciales = new CredencialesDao();
        credenciales.registrarFarmacia(farmacia.getCorreo(),contrasena);

        //SI RECIEN SE ESTA CREANDO NO PUEDE TENER PEDIDOS PENDIENTES
        String sentenciaSQL = "INSERT INTO farmacia(ruc,nombre,direccion,distrito,bloqueado,pedidosPendientes,logueo_correo,fotos)\n" +
                "VALUES(?,?,?,?,'Falso','No',?,?)";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)){

            System.out.println("ESTOY LLENANDO LOS CAMPOS DE FARMACIA");
            pstmt.setString(1,farmacia.getRuc());
            pstmt.setString(2,farmacia.getNombre());
            pstmt.setString(3,farmacia.getDireccion());
            pstmt.setString(4, farmacia.getDistrito());
            pstmt.setString(5,farmacia.getCorreo());
            pstmt.setString(6,farmacia.getFotos());
            pstmt.executeUpdate();
            System.out.println("LLENE FARMACIA");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean existeFarmacia(String RUC){


        boolean existeFarmacia = false;

        String sentenciaSQL = "SELECT * FROM mydb.farmacia where ruc=?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)){
            pstmt.setString(1,RUC);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                existeFarmacia = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return existeFarmacia;
    }

    public boolean bloquearFarmacia(String rucStr) throws SQLException {
        String sqlUpdate = "UPDATE farmacia f SET f.bloqueado ='Verdadero' WHERE f.ruc = ?";
        String sqlBusqueda ="SELECT f.pedidosPendientes FROM farmacia f WHERE f.ruc = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlBusqueda)){
            pstmt.setString(1,rucStr);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String pendientes =  rs.getString(1);
            if(pendientes.equalsIgnoreCase("no")){
                try(PreparedStatement pstmt2 = conn.prepareStatement(sqlUpdate)){
                    pstmt2.setString(1,rucStr);
                    pstmt2.executeUpdate();
                    return true;
                }
            }else{
                System.out.println("No se puede bloquear porque tiene pedidos pendientes");
            }
        }
        return false;
    }

    public ArrayList<BFarmacia> mostrarListaFarmacias_offset(String offset){

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        String sql ="SELECT f.ruc, f.nombre, l.correo, f.distrito,f.pedidosPendientes,f.bloqueado,f.direccion FROM farmacia f\n" +
                "INNER JOIN credenciales l ON l.correo = f.logueo_correo\n" +
                "GROUP BY f.ruc LIMIT 4 OFFSET ?;";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            int offset_num = (Integer.parseInt(offset)-1)*4;
            pstmt.setInt(1,offset_num);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String ruc = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String distrito =  rs.getString(4);
                String pedidosPendientes1 = rs.getString(5);
                String bloqueado= rs.getString(6);
                String direccion = rs.getString(7);
                BFarmacia f = new BFarmacia(ruc, nombre,correo, distrito,bloqueado,pedidosPendientes1,direccion);
                listaFarmacias.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaFarmacias;
    }



    public ArrayList<BFarmacia> listaFarmaciasPorBusqueda(String opcion){

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();

        String sentenciaSQL = "SELECT f.ruc, f.nombre, l.correo, f.distrito,f.pedidosPendientes,f.bloqueado,f.direccion FROM farmacia f\n"+
                "INNER JOIN credenciales l ON l.correo = f.logueo_correo\n"+
                "GROUP BY f.ruc\n"+
                "HAVING lower(f.nombre) LIKE ?;";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)){

            pstmt.setString(1,"%" + opcion + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String ruc = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String distrito =  rs.getString(4);
                String pedidosPendientes1 = rs.getString(5);
                String bloqueado= rs.getString(6);
                String direccion = rs.getString(7);
                BFarmacia f = new BFarmacia(ruc, nombre,correo, distrito,bloqueado,pedidosPendientes1,direccion);
                listaFarmacias.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaFarmacias;

    }


    //buscar farmacia por correo logueo
    public BFarmacia buscarFarmaciaxCorreo(String logueocorreo){
        String sql ="SELECT f.ruc, f.nombre, f.direccion, f.distrito, f.bloqueado, f.pedidosPendientes,  f.logueo_correo FROM farmacia f " +
                "INNER JOIN credenciales l ON l.correo = f.logueo_correo " +
                "WHERE l.correo = ? limit 1 ";
        BFarmacia farmacia = null;

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,logueocorreo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String ruc=rs.getString(1);
                String nombre = rs.getString(2);
                String direccion = rs.getString(3);
                String distrito = rs.getString(4);
                String bloqueado = rs.getString(5);
                String pedidosPendientes = rs.getString(6);
                String logueo_correo = rs.getString(7);
                farmacia = new BFarmacia(ruc,nombre,direccion,distrito,bloqueado,pedidosPendientes,logueo_correo);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return farmacia;
    }


    public BFarmacia buscarFarmaciaxruc(String farmaciaruc){
        String sql ="select ruc,nombre, direccion, distrito, bloqueado, pedidosPendientes, logueo_correo from farmacia where ruc = ?;   ";
        BFarmacia farmacia = null;
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,farmaciaruc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String ruc=rs.getString(1);
                String nombre = rs.getString(2);
                String direccion = rs.getString(3);
                String distrito = rs.getString(4);
                String bloqueado = rs.getString(5);
                String pedidosPendientes = rs.getString(6);
                String logueo_correo = rs.getString(7);
                farmacia = new BFarmacia(ruc,nombre,direccion,distrito,bloqueado,pedidosPendientes,logueo_correo);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return farmacia;
    }
















    public boolean nombreyApellidoValid(String nombre) {
        String regex = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:[\\]]]{1,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    public boolean emailisValid(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean rucValid(String ruc) {
        String regex = "^[0-9]{11,11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ruc);
        return matcher.find();
    }
    public boolean pedidosPendientes(String dni) {
        String regex = "^[0-1]{1,1}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dni);
        return matcher.find();
    }

    public boolean contrasenaisValid(String contrasenia) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contrasenia);
        return matcher.find();
    }







}
