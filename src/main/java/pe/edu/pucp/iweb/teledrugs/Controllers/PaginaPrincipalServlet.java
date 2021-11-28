
package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BCliente;
import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;
import pe.edu.pucp.iweb.teledrugs.Daos.CredencialesDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaginaPrincipalServlet", value = "/PaginaPrincipal")
public class PaginaPrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("/FlujoUsuario/homepage.jsp");
        view.forward(request,response);
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

            ClienteDao clienteDao = new ClienteDao();
            if(nombreCorrecto & apellidoCorrecto & dniCorrecto & birthdayCorrecto & contrasenaCorrecto & recontrasenaCorrecto){
                //VALIDAMOS SI EXISTE EL CLIENTE
                boolean existeCliente = clienteDao.existeCliente(email,contrasenia);
                if(existeCliente == true){
                    //SE IMPRIME UN MENSAJE DE ERRROR UN FEEDBACK
                }
                else{
                    BCliente clientito = new BCliente(dni,nombre,apellido,distrito,birthday,email);
                    // YA TENGO EL CLIENTE AHORA FALTA PASAR LA CONTRASEÑA PARA PODER REGISTRARLO
                    //PRIMERO REGISTRAMOS EN LAS CREDENCIALES
                    credencialesDao.insertCliente(email,contrasenia);

                    //UNA VEZ REGISTRADO LAS CREDENCIALES , REGISTRAMOS EL CLIENTE
                    clienteDao.registrarCliente(clientito);
                    response.sendRedirect(request.getContextPath() + "/Registro");
                }

            }
            else{
                //COMO SON DEMASIADAS VALIDACIONES , HABRIA UN MONTON DE CASOS , ENTONCES LO IDEAL SERIA MOSTRARLE UN SOLO MENSAJE
                //DICIENDOLE QUE LOS CAMPOS ESTAN MAL Y YA , EN FORMA GENERAL PARA NO DECIRLE UNO EN ESPECIFICO

            }

        } else if (act.equalsIgnoreCase("login")) {

            String constrasenia = request.getParameter("constrasenia");
            String correo = request.getParameter("correo");

            CredencialesDao credencialesDao = new CredencialesDao();

            if(credencialesDao.existeCredenciales(correo,constrasenia)){
                String rol = credencialesDao.rolCredenciales(correo);
                System.out.println(rol);
                //String DNI = credencialesDao.obtenerIDCliente(correo);
                //BAdministrador bAdministrador = new BAdministrador();
                //bAdministrador.setContrasenia(constrasenia);
                //bAdministrador.setLogueoCorreo(correo);

                if (rol.equalsIgnoreCase("administrador")) {
                    response.sendRedirect(request.getContextPath() + "/AdminPrincipal?correo=" + correo);
                } else if (rol.equalsIgnoreCase("cliente")) {
                    response.sendRedirect(request.getContextPath() + "/Usuario?correo=" + correo);
                } else if (rol.equalsIgnoreCase("farmacia")) {
                    response.sendRedirect(request.getContextPath() + "/FarmaciaPrincipal");
                }

            }
            else{
                //MENSAJE DE ERROR MENSAJE DE FEEDBACK
            }

        }
    }
}
