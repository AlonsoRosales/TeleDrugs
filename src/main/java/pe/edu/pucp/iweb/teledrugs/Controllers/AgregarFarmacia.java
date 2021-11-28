package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AgregarFarmacia", value = "/AgregarFarmacia")
public class AgregarFarmacia extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        System.out.println("agregar");
        System.out.println(user);
        request.setAttribute("usuario",user);
        RequestDispatcher view = request.getRequestDispatcher("/FlujoAdministrador/AgregarFarmacias/AgregarFarmacias.jsp");
        view.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("usuario");
        System.out.println("agregar post");
        System.out.println(user);

        String imagenFarmacia = request.getParameter("Imagen");
        String RUCFarmacia = request.getParameter("RUC");
        String direccionFarmacia = request.getParameter("Direccion");
        String distritoFarmacia = request.getParameter("Distrito");
        String nombreFarmacia = request.getParameter("Nombre");
        String correoFarmacia = request.getParameter("Correo");
        String contrasenaFarmacia = request.getParameter("Contrasena");
        System.out.println(imagenFarmacia);
        System.out.println(RUCFarmacia);
        System.out.println(direccionFarmacia);
        System.out.println(distritoFarmacia);
        System.out.println(nombreFarmacia);
        System.out.println(correoFarmacia);
        System.out.println(contrasenaFarmacia);

        //VALIDACION DEL RUC
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        boolean correctoRUCFarmacia = farmaciaDao.rucValid(RUCFarmacia);
        System.out.println(correctoRUCFarmacia);
        //VALIDACION DE CONTRASENA
        boolean correctoContrasena = farmaciaDao.contrasenaisValid(contrasenaFarmacia);
        System.out.println(correctoContrasena);
        //VALIDACION DEL CORREO
        boolean correctoCorreo = farmaciaDao.emailisValid(correoFarmacia);
        System.out.println(correctoCorreo);
        if(correctoRUCFarmacia && correctoContrasena & correctoCorreo){
            boolean existeFarmacia = farmaciaDao.existeFarmacia(RUCFarmacia);
            if(existeFarmacia){
                //YA EXISTE UNA IMPRIMIRIA UN FEEDBACK DICIENDO QUE YA EXISTE UNA FARMACIA
            }
            else{
                BFarmacia farmacita = new BFarmacia(RUCFarmacia,nombreFarmacia,direccionFarmacia,distritoFarmacia,imagenFarmacia,correoFarmacia);
                farmaciaDao.insertarFarmacia(farmacita,contrasenaFarmacia);
                response.sendRedirect(request.getContextPath() + "/AgregarFarmacia?user="+user);
            }
        }else{
            //ACA SE TENDRIA QUE IMPRIMIR UN MENSAJE DE FEEDBACK DICIENDO QUE EL CAMPO DE RUC ESTA MAL INGRESADO
            //SI ESTA MAL EL RUC O LA CONTRASENA O EL CORREO
        }

    }
}