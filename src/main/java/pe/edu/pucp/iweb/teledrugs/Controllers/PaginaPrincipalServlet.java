
package pe.edu.pucp.iweb.teledrugs.Controllers;

import org.bouncycastle.util.encoders.Hex;
import pe.edu.pucp.iweb.teledrugs.Beans.BAdministrador;
import pe.edu.pucp.iweb.teledrugs.Beans.BCliente;
import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;
import pe.edu.pucp.iweb.teledrugs.Daos.CredencialesDao;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "PaginaPrincipalServlet", value = "/PaginaPrincipal")
public class PaginaPrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act") != null ? request.getParameter("act") : "login";

        if (act.equalsIgnoreCase("logout")){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        }else{
            RequestDispatcher view = request.getRequestDispatcher("/FlujoUsuario/homepage.jsp");
            view.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act") != null ? request.getParameter("act") : "login";

        if (act.equalsIgnoreCase("reg")) {
            String nombre = request.getParameter("Nombres");
            String apellido = request.getParameter("Apellidos");
            String dni = request.getParameter("DNI");
            String birthday = request.getParameter("FechaNacimiento");
            String distrito = request.getParameter("Distrito");
            String email = request.getParameter("Correo");
            String contrasenia = request.getParameter("Contrasena");
            String recontrasenia = request.getParameter("RePass");

            CredencialesDao credencialesDao = new CredencialesDao();
            boolean nombreCorrecto = credencialesDao.nombreValid(nombre);
            boolean apellidoCorrecto = credencialesDao.apellidoValid(apellido);
            boolean dniCorrecto = credencialesDao.dniValid(dni);
            boolean birthdayCorrecto = credencialesDao.fechaIsValid(birthday);
            //EL DISTRITO YA NO SE VALIDA PORQUE VA A ESCOGER UNO DE LA LISTA
            boolean correoCorrecto = credencialesDao.emailisValid(email);
            boolean contrasenaCorrecto = credencialesDao.contrasenaisValid(contrasenia);
            boolean recontrasenaCorrecto = false;

            if(recontrasenia.equals(contrasenia)){
                recontrasenaCorrecto = true;
            }
            HttpSession session = request.getSession();
            ClienteDao clienteDao = new ClienteDao();
            if(nombreCorrecto & apellidoCorrecto & dniCorrecto & birthdayCorrecto & contrasenaCorrecto & recontrasenaCorrecto){
                //VALIDAMOS SI EXISTE EL CLIENTE
                boolean existeCliente = clienteDao.existeCliente(email,dni);
                if(existeCliente == true){
                    //SE IMPRIME UN MENSAJE DE ERRROR UN FEEDBACK
                    //SI IMPRIMIMOS ESTE MENSAJE QUIERE DECIR QUE  p.e. HA "DESCUBIERTO" UNA CUENTA QUE NO ERA SUYA , POR ESO
                    //LO MEJOR SERÍA QUE LE SALGA UN MENSAJE DICIENDO QUE HA OCURRIDO UN ERROR O ALGO SIMILAR
                    session.setAttribute("err","Correo no disponible! Ingrese otro!");
                    response.sendRedirect(request.getContextPath() + "/Registro");
                }
                else{
                    BCliente clientito = new BCliente(dni,nombre,apellido,distrito,birthday,email);
                    // YA TENGO EL CLIENTE AHORA FALTA PASAR LA CONTRASEÑA PARA PODER REGISTRARLO
                    //PRIMERO REGISTRAMOS EN LAS CREDENCIALES

                    MessageDigest digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    byte[] hash = digest.digest(
                            contrasenia.getBytes(StandardCharsets.UTF_8));
                    String contraseniahashed = new String(Hex.encode(hash));
                    System.out.println("La contra hasehada es : "+ contraseniahashed);


                    credencialesDao.insertCliente(email,contraseniahashed);

                    //UNA VEZ REGISTRADO LAS CREDENCIALES , REGISTRAMOS EL CLIENTE
                    clienteDao.registrarCliente(clientito);
                    session.setAttribute("msg","Cuenta creada exitosamente!");
                    response.sendRedirect(request.getContextPath() + "/Registro");
                }

            }
            else{
                //COMO SON DEMASIADAS VALIDACIONES , HABRIA UN MONTON DE CASOS , ENTONCES LO IDEAL SERIA MOSTRARLE UN SOLO MENSAJE
                //DICIENDOLE QUE LOS CAMPOS ESTAN MAL Y YA , EN FORMA GENERAL PARA NO DECIRLE UNO EN ESPECIFICO
                session.setAttribute("err","Datos ingresados incorrectamente!");
                response.sendRedirect(request.getContextPath() + "/Registro");
            }

        } else if (act.equalsIgnoreCase("login")) {

            String constrasenia = request.getParameter("constrasenia");
            String correo = request.getParameter("correo");

            CredencialesDao credencialesDao = new CredencialesDao();
            HttpSession session = request.getSession();
            if(credencialesDao.existeCredenciales(correo,constrasenia)){
                String rol = credencialesDao.rolCredenciales(correo);
                //HttpSession session = request.getSession();
                if (rol.equalsIgnoreCase("administrador")) {
                    BAdministrador administrador= new BAdministrador();
                    administrador.setCorreo(correo);
                    session.setAttribute("correo", correo);
                    session.setAttribute("admin", administrador);
                    session.setMaxInactiveInterval(10*60);
                    response.sendRedirect(request.getContextPath() + "/AdminPrincipal");
                } else if (rol.equalsIgnoreCase("cliente")) {
                    ClienteDao clienteDao = new ClienteDao();
                    BCliente cliente = clienteDao.obtenerCliente(correo);
                    session.setAttribute("usuario", cliente);
                    session.setMaxInactiveInterval(10*60);
                    response.sendRedirect(request.getContextPath() + "/Usuario");
                } else if (rol.equalsIgnoreCase("farmacia")) {
                    FarmaciaDao farmaciaDao = new FarmaciaDao();
                    session.setAttribute("farmacia", farmaciaDao.mostrarFarmaciaCorreo(correo));
                    response.sendRedirect(request.getContextPath() + "/FarmaciaPrincipal");
                }

            }
            else{
                session.setAttribute("msg","El correo o contraseña no existen");
                response.sendRedirect(request.getContextPath());
            }

        } else if (act.equalsIgnoreCase("cambio")){
            String correo = request.getParameter("Correo");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            HttpSession session = request.getSession();
            if(pass.equalsIgnoreCase("") || pass.equalsIgnoreCase("")){
                session.setAttribute("err","La contrasena no puede estar vacia");
                response.sendRedirect(request.getContextPath() + "/RecuperarContrasena?"+ "vista=cambio");
            }else if(!pass.equals(repass)){
                session.setAttribute("err","Ambas contraseñas tienen que ser iguales");
                response.sendRedirect(request.getContextPath() + "/RecuperarContrasena?"+ "vista=cambio");
            }else{
                CredencialesDao credencialesDao = new CredencialesDao();
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] hash = digest.digest(
                        pass.getBytes(StandardCharsets.UTF_8));
                String passhashed = new String(Hex.encode(hash));
                credencialesDao.cambiarContrasenaCliente(correo,passhashed);
                session.setAttribute("msg","La contraseña a sido cambiada exitosamente!. Porfavor cerrar pestaña");
                response.sendRedirect(request.getContextPath() + "/RecuperarContrasena?"+ "vista=cambio");
            }
        }
    }
}
