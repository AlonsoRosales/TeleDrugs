package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BCliente;
import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Beans.BProducto;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOBuscarProductoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOCarritoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOPedidoCliente;
import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ClienteServlet", value = "/Usuario")
public class ClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        BCliente bCliente = (BCliente) session.getAttribute("usuario");
        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();
        ArrayList<DTOCarritoCliente> bCarritoCliente;
        //Requests
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        String ruc =  session.getAttribute("farmacia") == null ? null : ((BFarmacia) session.getAttribute("farmacia")).getRuc() ;
        System.out.println(ruc);

        //Atributos
        switch (opcion) {
            case "mostrarPerfil":
                RequestDispatcher view = request.getRequestDispatcher("/FlujoUsuario/profile.jsp");
                view.forward(request, response);
                break;
            case "historialPedidos":
                String dni =  clienteDao.DNI(bCliente.getLogueoCorreo());
                System.out.println(dni);
                ArrayList<DTOPedidoCliente> pedidos = clienteDao.mostrarHistorial(dni);
                session.setAttribute("listaPedidos",pedidos);
                RequestDispatcher view2 = request.getRequestDispatcher("/FlujoUsuario/historial.jsp");
                view2.forward(request, response);
                break;
            case "mostrarProducto":
                session.setAttribute("producto", clienteDao.buscarProductoporId(ruc,Integer.parseInt((String) session.getAttribute("idProducto"))));
                RequestDispatcher view7 = request.getRequestDispatcher("/FlujoUsuario/paracetamol.jsp");
                view7.forward(request, response);
                break;
            case "carrito":
                RequestDispatcher view8 = request.getRequestDispatcher("/FlujoUsuario/shopping_cart.jsp");
                view8.forward(request, response);
                break;
            case "limpiarcarrito":
                session.removeAttribute("carrito");
                RequestDispatcher view9 = request.getRequestDispatcher("/FlujoUsuario/shopping_cart.jsp");
                view9.forward(request, response);
                break;
            case "salir":
                BFarmacia bFarmacia = new BFarmacia();
                if (ruc != null){
                    ArrayList<BProducto> listarProductos = farmaciaDao.listarProductos(ruc);
                    session.setAttribute("listaProducto",listarProductos);
                    bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
                }
                session.setAttribute("farmacia",bFarmacia);
                session.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias());
                RequestDispatcher view1 = request.getRequestDispatcher("/FlujoUsuario/homepage.jsp");
                view1.forward(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        BCliente bCliente = (BCliente) session.getAttribute("usuario");

        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        String ruc=null;


        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();

        //Atributos
        switch (opcion) {
            case "Update":
                String nombre = request.getParameter("Nombres") != null ? request.getParameter("Nombres") : "";
                String apellidos = request.getParameter("Apellidos") != null ? request.getParameter("Apellidos") : "";
                String distrito = request.getParameter("Distrito") != null ? request.getParameter("Distrito") : "";
                clienteDao.updatePerfil(nombre, apellidos, distrito, bCliente.getLogueoCorreo());
                session.setAttribute("usuario",clienteDao.obtenerCliente(bCliente.getLogueoCorreo()));
                response.sendRedirect(request.getContextPath() + "/Usuario?opcion=mostrarPerfil");
                break;
            case "Buscar":
                String texto = request.getParameter("search");
                ruc =  session.getAttribute("farmacia") == null ? null : ((BFarmacia) session.getAttribute("farmacia")).getRuc() ;
                if (texto.equalsIgnoreCase("")) {
                    response.sendRedirect(request.getContextPath() + "/Usuario?opcion=salir");
                } else {
                    request.setAttribute("productos", clienteDao.buscarProducto(ruc,texto));
                    RequestDispatcher view3 = request.getRequestDispatcher("/FlujoUsuario/jabon.jsp");
                    view3.forward(request, response);
                }
                break;
            case "carrito":
                ArrayList<DTOCarritoCliente> carritoCliente = session.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("carrito");
                String cantidad = request.getParameter("cantidad") != null ? request.getParameter("cantidad") : "0";
                String idProducto = (String) session.getAttribute("idProducto");
                ruc =  session.getAttribute("farmacia") == null ? null : ((BFarmacia) session.getAttribute("farmacia")).getRuc() ;
                if (!cantidad.equalsIgnoreCase("0")) {
                    DTOBuscarProductoCliente b = clienteDao.buscarProductoporId(ruc, Integer.parseInt(idProducto));
                    double cantidad2 = Integer.parseInt(cantidad);
                    cantidad2 = cantidad2 * b.getPrecio();
                    carritoCliente.add(new DTOCarritoCliente(b.getNombre(),String.valueOf(b.getIdProducto()),b.getStock(),Integer.parseInt(cantidad),cantidad2,b.getFoto()));
                    session.setAttribute("carrito",carritoCliente);
                }
                response.sendRedirect(request.getContextPath() + "/Usuario?opcion=carrito");
                break;
            case "mostrarFarmacia":
                ruc= request.getParameter("ruc");
                BFarmacia bFarmacia = new BFarmacia();
                if (ruc != null){
                    bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
                }
                session.removeAttribute("farmacia");
                session.setAttribute("farmacia",bFarmacia);
                session.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias());
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=salir");
                break;
            case "mostrarProducto":
                session.setAttribute("idProducto", request.getParameter("idProducto"));
                System.out.println(request.getParameter("idProducto"));
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=mostrarProducto");
                break;
            case "borraruno":
                ArrayList<DTOCarritoCliente> carritoClientes = session.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("carrito");
                System.out.println(request.getParameter("idcarrito"));
                carritoClientes.remove(Integer.parseInt(request.getParameter("idcarrito")));
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                break;
        }

    }
}
