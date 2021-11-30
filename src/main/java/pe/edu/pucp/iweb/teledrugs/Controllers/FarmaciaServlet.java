package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Beans.BProducto;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOPedidoHistorial;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;
import pe.edu.pucp.iweb.teledrugs.Daos.ProductoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FarmaciaServlet", value = "/FarmaciaPrincipal")
public class FarmaciaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listarProductos" : request.getParameter("action");
        ProductoDao productoDao =new ProductoDao();
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        HttpSession session = request.getSession();
        RequestDispatcher view;
        System.out.println(action);
        BFarmacia farmacia;
        String ruc =request.getParameter("ruc")!= null ? request.getParameter("ruc"):"";
        switch (action){
            case "listarProductos":
                farmacia = (BFarmacia) session.getAttribute("farmacia");
                String rucFarmacia = farmacia.getRuc(); //"27207510101";
                ArrayList<BProducto> listaProductos = productoDao.ProductosPorFarmacia(rucFarmacia);
                session.setAttribute("listaProductos",listaProductos);
                view = request.getRequestDispatcher("/FlujoFarmacia/ListaProductos.jsp");
                view.forward(request,response);
                break;

            case "listarPedidos":
                response.setContentType("text/html");
                ArrayList<DTOPedidoHistorial> listaHistorialPedidos = farmaciaDao.mostrarHistorialPedidos();
                BFarmacia farmacia2 = farmaciaDao.buscarFarmaciaxruc(ruc);
                request.setAttribute("listaHistorialPedidos",listaHistorialPedidos);
                request.setAttribute("ruc",ruc);
                request.setAttribute("farmacia",farmacia2);
                view = request.getRequestDispatcher("/FlujoFarmacia/HistorialPedidos.jsp");
                view.forward(request,response);
                break;

            case "crear":
                view=request.getRequestDispatcher("/FlujoFarmacia/AgregarProducto.jsp");
                view.forward(request,response);
                break;

            case "editar":
                String id=request.getParameter("id")!= null ? request.getParameter("id") : "";

                BProducto producto= productoDao.obtenerProducto(id);

                if(producto!=null){
                    request.setAttribute("producto",producto);
                    view = request.getRequestDispatcher("/FlujoFarmacia/EditarProducto.jsp");
                    view.forward(request, response);
                }else {
                    response.sendRedirect(request.getContextPath()+"/FarmaciaServlet");
                }
                break;

            case "eliminar":
                String id1=request.getParameter("id")!= null ? request.getParameter("id"):"";
                System.out.println("eliminar");
                BProducto producto1= productoDao.obtenerProducto(id1);
                if (producto1 != null) { //borramos
                    productoDao.eliminarProducto(Integer.parseInt(id1), ruc);
                    response.sendRedirect(request.getContextPath() + "/FarmaciaServlet");
                } else { //el no existe el productoid ni el ruc` no existe
                    response.sendRedirect(request.getContextPath() + "/FarmaciaServlet");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action") != null ? request.getParameter("action") : "crear";
        ProductoDao productoDao = new ProductoDao();
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        BFarmacia bFarmacia = session.getAttribute("farmacia") == null ? new BFarmacia() : (BFarmacia) session.getAttribute("farmacia");
        RequestDispatcher view;
        switch (action) {
            case "crear":
                String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String precioStr = request.getParameter("precio") != null ? request.getParameter("precio") : "";
                String stockStr = request.getParameter("stock") != null ? request.getParameter("stock") : "";
                String descripcion = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
                String requiere = request.getParameter("requiere") != null ? request.getParameter("requiere") : "";
                double precio = Double.parseDouble(precioStr);
                int stock = Integer.parseInt(stockStr);
                boolean requiereReceta = Boolean.parseBoolean(requiere);
                productoDao.inserta_producto(nombre, precio, stock, descripcion, requiereReceta, bFarmacia.getRuc());
                response.sendRedirect(request.getContextPath() +"/FarmaciaPrincipal");
                break;
            case "update":
                String nombre2 = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String precioStr2 = request.getParameter("precio") != null ? request.getParameter("precio") : "";
                String stockStr2 = request.getParameter("stock") != null ? request.getParameter("stock") : "";
                String descripcion2 = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
                String requiere2 = request.getParameter("requiere") != null ? request.getParameter("requiere") : "";
                String farmacia_ruc2 = request.getParameter("farmacia_ruc") != null ? request.getParameter("farmacia_ruc") : "";
                double precio2 = Double.parseDouble(precioStr2);
                int stock2 = Integer.parseInt(stockStr2);
                boolean requiereReceta2 = Boolean.parseBoolean(requiere2);
                productoDao.inserta_producto(nombre2, precio2, stock2, descripcion2, requiereReceta2, farmacia_ruc2);
                //CORREGIR
                response.sendRedirect(request.getContextPath() );
                break;
            case "Buscar":
                String texto = request.getParameter("search");
                String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "salir";
                if (texto.equalsIgnoreCase("")) {
                    response.sendRedirect(request.getContextPath() + "/Usuario");
                } else {
                    request.setAttribute("productos", farmaciaDao.buscarProducto(bFarmacia.getRuc(),texto));
                    request.setAttribute("BuscarProducto", texto);
                    view = request.getRequestDispatcher("/FlujoFarmacia/Buscador.jsp");
                    view.forward(request, response);
                }
                break;
        }
    }
}
