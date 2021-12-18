package pe.edu.pucp.iweb.teledrugs.Daos;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredencialesDao extends BaseDao {

    //FUNCION PARA REGISTRAR FARMACIA EN CREDENCIALES
    public void registrarFarmacia(String correo, String contrasena){

        String rol= "farmacia";
        String sql = "INSERT INTO credenciales(correo,contrasena,rol) VALUES(?,?,?)";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,correo);
            pstmt.setString(2,contrasena);
            pstmt.setString(3,rol);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //FUNCION PARA OBTENER ROL
    public String rolCredenciales(String correo){
        String rol = null;
        String sql="SELECT rol FROM credenciales c WHERE c.correo = ? ";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,correo);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                rol=rs.getString(1);
            }
            return rol;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rol;
    }

    public boolean existeCredenciales(String correo,String contrasena){
        Boolean existe = false;

        String sentenciaSQL ="SELECT * FROM credenciales WHERE correo = ? AND contrasena = sha2(?,256)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {

            pstmt.setString(1,correo);
            pstmt.setString(2,contrasena);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                existe=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return existe;
    }



    public void insertCliente(String correo, String contrasena){

        String sentenciaSQL = "INSERT INTO credenciales(correo,contrasena,rol)\n" +
                "VALUES(?,?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {
            pstmt.setString(1,correo);
            pstmt.setString(2,contrasena);
            pstmt.setString(3,"cliente");
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void cambiarContrasenaCliente(String correo,String contrasena){
        String sentenciaSQL = "UPDATE credenciales c SET c.contrasena = ? WHERE c.correo = ?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {
            pstmt.setString(1,contrasena);
            pstmt.setString(2,correo);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean nombreValid(String nombre) {
        String regex = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:[\\]]]{1,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    public boolean apellidoValid(String apellido) {
        String regex = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:[\\]]]{1,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellido);
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
    public boolean contrasenaisValid(String contrasenia) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contrasenia);
        return matcher.find();
    }

}