package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;
import pe.edu.pucp.iweb.teledrugs.Funciones.FuncionEnviaCorreo;


/*import javax.mail.*;*/
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
//-------------------------------------------------
/*import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;*/
//------------------------------------------------


@WebServlet(name = "recuperarcontrasena", value = "/RecuperarContrasena")
public class RecuperarContrasena extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String vista = request.getParameter("vista") != null ? request.getParameter("vista") : "recuperar";
        if(vista.equalsIgnoreCase("recuperar")){
            RequestDispatcher view = request.getRequestDispatcher("/Login/recuperar.jsp");
            view.forward(request,response);
        }
        else{
            RequestDispatcher view2 = request.getRequestDispatcher("/Login/cambiarContrasena.jsp");
            view2.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String correo = request.getParameter("Correo");
        String DNI = request.getParameter("DNI");

        //SE VERIFICA QUE EXISTA ESAS CREDENCIALES , LO QUE SE INGRESO AL CAMPO ESTE BIEN , TODAVIA NO QUE EXISTA EN LA BASE DE DATOS
        //SE VERIFICA DNI
        ClienteDao clienteDao = new ClienteDao();
        boolean dniCorrecto = clienteDao.dniValid(DNI);

        //SE VERIFICA EMAIL
        boolean correoCorrecto = clienteDao.emailisValid(correo);
        HttpSession session = request.getSession();
        if(dniCorrecto & correoCorrecto){
            //TOCA HACER LA VALIDACION DE SI EXISTE UN USUARIO CON ESAS CREDENCIALES
            boolean existeCliente = clienteDao.existeCliente(correo,DNI);
            System.out.println(existeCliente);
            if(!existeCliente){
                //SI NO EXISTE EL CLIENTE SE MUESTRA UN MENSAJE DE ERROR FEEDBACK
                session.setAttribute("err","No existe un cliente con esas credenciales!"); // TIENE SENTIDO DECIR QUE NO EXISTE EL CLIENTE?
                response.sendRedirect(request.getContextPath() + "/RecuperarContrasena");
            }else{
                //----------------------------------------------------------------------------------------------------------------------------------------
                try {
                    FuncionEnviaCorreo.main(correo);
                    response.sendRedirect(request.getContextPath());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                //----------------------------------------------------------------------------------------------------------------------------------------
            }
        }else{
            //SI NO ESTA CORRECTO SE MUESTRA UN MENSAJE DE FEEDBACK
            session.setAttribute("err","Datos ingresados incorrectamente!");
            response.sendRedirect(request.getContextPath() + "/RecuperarContrasena");
        }


    }
}

