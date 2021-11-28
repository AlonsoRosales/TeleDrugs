package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "recuperarcontrasena", value = "/RecuperarContrasena")
public class RecuperarContrasena extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher view = request.getRequestDispatcher("/Login/recuperar.jsp");
        view.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("Correo");
        String DNI = request.getParameter("DNI");

        //SE VERIFICA QUE EXISTA ESAS CREDENCIALES , LO QUE SE INGRESO AL CAMPO ESTE BIEN , TODAVIA NO QUE EXISTA EN LA BASE DE DATOS

        //SE VERIFICA DNI
        ClienteDao clienteDao = new ClienteDao();
        boolean dniCorrecto = clienteDao.dniValid(DNI);

        //SE VERIFICA EMAIL
        boolean correoCorrecto = clienteDao.emailisValid(correo);

        if(dniCorrecto & correoCorrecto){
            //TOCA HACER LA VALIDACION DE SI EXISTE UN USUARIO CON ESAS CREDENCIALES
            boolean existeCliente = clienteDao.existeCliente(correo,DNI);
            System.out.println(existeCliente);
            if(!existeCliente){
                //SI NO EXISTE EL CLIENTE SE MUESTRA UN MENSAJE DE ERROR FEEDBACK
            }else{


                response.sendRedirect(request.getContextPath() +  "/RecuperarContrasena");
            }
        }else{
            //SI NO ESTA CORRECTO SE MUESTRA UN MENSAJE DE FEEDBACK
        }


    }
}
