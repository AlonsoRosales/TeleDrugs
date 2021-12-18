package pe.edu.pucp.iweb.teledrugs.Controllers;

import org.bouncycastle.util.encoders.Hex;
import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "AgregarFarmacia", value = "/AgregarFarmacia")
@MultipartConfig
public class AgregarFarmacia extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("agregar");
        RequestDispatcher view = request.getRequestDispatcher("/FlujoAdministrador/AgregarFarmacias/AgregarFarmacias.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part imagenFarmacia = request.getPart("Imagen");
        //String imagenFarmacia = request.getParameter("Imagen");
        String RUCFarmacia = request.getParameter("RUC");
        String direccionFarmacia = request.getParameter("Direccion");
        String distritoFarmacia = request.getParameter("Distrito");
        String nombreFarmacia = request.getParameter("Nombre");
        String correoFarmacia = request.getParameter("Correo");
        String contrasenaFarmacia = request.getParameter("Contrasena");

        //NO SE HACEN TANTAS VALIDACIONES YA QUE ES UN ADMINISTRADOR
        //VALIDACION DEL RUC
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        boolean correctoRUCFarmacia = farmaciaDao.rucValid(RUCFarmacia);
        //VALIDACION DE CONTRASENA
        boolean correctoContrasena = farmaciaDao.contrasenaisValid(contrasenaFarmacia);
        //VALIDACION DEL CORREO
        boolean correctoCorreo = farmaciaDao.emailisValid(correoFarmacia);

        HttpSession session = request.getSession();
        if(correctoRUCFarmacia && correctoContrasena & correctoCorreo){
            boolean existeFarmacia = farmaciaDao.existeFarmacia(RUCFarmacia);
            if(existeFarmacia){
                //YA EXISTE UNA IMPRIMIRIA UN FEEDBACK DICIENDO QUE YA EXISTE UNA FARMACIA
                session.setAttribute("err","Ya existe la Farmacia! . Ingrese una que no exista!");
                response.sendRedirect(request.getContextPath() + "/AgregarFarmacia");
            }
            else{
                InputStream inputStream = imagenFarmacia.getInputStream();
                BFarmacia farmacita = new BFarmacia(RUCFarmacia,nombreFarmacia,direccionFarmacia,distritoFarmacia,inputStream,correoFarmacia);

                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] hash = digest.digest(
                        contrasenaFarmacia.getBytes(StandardCharsets.UTF_8));
                String contrasenaFarmaciaHashed = new String(Hex.encode(hash));

                farmaciaDao.insertarFarmacia(farmacita,contrasenaFarmaciaHashed);
                response.sendRedirect(request.getContextPath() + "/AgregarFarmacia");
                session.setAttribute("msg","Farmacia agregada correctamente!");
            }
        }else{
            session.setAttribute("err","Ingrese datos validos!");
            response.sendRedirect(request.getContextPath() + "/AgregarFarmacia");
        }

    }
}