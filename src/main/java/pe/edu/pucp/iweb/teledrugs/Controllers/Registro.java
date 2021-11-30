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
import java.util.ArrayList;

@WebServlet(name = "registro", value = "/Registro")
public class Registro extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        ArrayList<BFarmacia> listaFarmacias= farmaciaDao.mostrarListaFarmacias();
        request.setAttribute("listaFarmacias",listaFarmacias);
        RequestDispatcher view = request.getRequestDispatcher("/Login/registro.jsp");
        view.forward(request,response);


    }
}