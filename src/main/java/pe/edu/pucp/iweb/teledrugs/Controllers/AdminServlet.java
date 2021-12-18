package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Admin", value = "/AdminPrincipal")
public class AdminServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String busqueda = request.getParameter("busqueda") != null ? request.getParameter("busqueda") : "";
        String offset = request.getParameter("offset") != null ? request.getParameter("offset") : "1";
        FarmaciaDao farmaciaDao = new FarmaciaDao() ;
        if(busqueda.equals("")){
            ArrayList<BFarmacia> listaFarmacias = farmaciaDao.mostrarListaFarmacias_offset(offset);
            ArrayList<BFarmacia> listaFarmaciasTotal = farmaciaDao.mostrarListaFarmaciasTotal();
            request.setAttribute("listaFarmacias",listaFarmacias);
            request.setAttribute("pag",Integer.parseInt(offset));
            System.out.println(Integer.parseInt(offset));
            System.out.println("Tamaño de lista de farmacias "+ listaFarmaciasTotal.size());
            int index;
            if(listaFarmaciasTotal.size()%4==0){
                index=listaFarmaciasTotal.size()/4;
            }else{

                index=listaFarmaciasTotal.size()/4+1;
            }
            request.setAttribute("index",index);
            System.out.println("Index = "+ index);
            RequestDispatcher view = request.getRequestDispatcher("FlujoAdministrador/Listafarmacias/Listafarmacias.jsp");
            view.forward(request,response);

        }else{
            request.setAttribute("pag",Integer.parseInt(offset));
            int offsetInt = Integer.parseInt(offset);
            request.setAttribute("listaFarmacias",farmaciaDao.listaFarmaciasPorBusqueda(busqueda,offsetInt));
            ArrayList<BFarmacia> listaFarmaciasTotal = farmaciaDao.mostrarListaFarmaciasTotal();
            int index;
            if(listaFarmaciasTotal.size()%4==0){
                index=listaFarmaciasTotal.size()/4;
            }else{
                index=listaFarmaciasTotal.size()/4+1;
            }
            request.setAttribute("index",index);
            RequestDispatcher view = request.getRequestDispatcher("FlujoAdministrador/Listafarmacias/Listafarmacias.jsp");
            view.forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        String correo = request.getParameter("correo");
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "";
        //System.out.println(opcion);
        //FarmaciaDao farmaciaDao = new FarmaciaDao();
        if(opcion.equalsIgnoreCase("Buscar")){
            String search = request.getParameter("search") != null ? request.getParameter("search") : "";
            // System.out.println(search);
            if(opcion.equalsIgnoreCase("")){
                response.sendRedirect(request.getContextPath() + "/AdminPrincipal?action=" + correo );

            }else{
                //System.out.println("ENTRE HASTA ANTES DE ENVIAR LA LISTA");
                //response.sendRedirect(request.getContextPath() + "/AdminPrincipal?action=" + correo + "?lista=" + farmaciaDao.listaFarmaciasPorBusqueda(search));
                response.sendRedirect(request.getContextPath() + "/AdminPrincipal?action=" + correo + "&busqueda=" + search);
            }

        }else if (opcion.equalsIgnoreCase("bloquear")){
            String numero = request.getParameter("num")!= null ? request.getParameter("num") : "1";
            int num = Integer.parseInt(numero);
            String check="check";
            ArrayList<String> lista = new ArrayList<String>();
            for(int i=0; i<num;i++){
                String check_num=check+i;
                String elemento = request.getParameter(check_num) != null ? request.getParameter(check_num) : "no";
                if (!elemento.equals("no")){
                    lista.add(elemento);
                }
            }
            for (String f : lista){

                try {
                    farmaciaDao.bloquearFarmacia(f);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(f);
            }
            String search ="";
            response.sendRedirect(request.getContextPath() + "/AdminPrincipal?correo=" + correo);
        }

    }
}
