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
        //Objetos
        BFarmacia bFarmacia = new BFarmacia();
        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();
        ArrayList<DTOCarritoCliente> bCarritoCliente;
        //Requests
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "salir";
        String ruc = request.getParameter("ruc");

        if (ruc != null){
            ArrayList<BProducto> listarProductos = farmaciaDao.listarProductos(ruc);
            request.setAttribute("listaProducto",listarProductos);
            bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
        }
        //Atributos
        request.setAttribute("farmacia",bFarmacia);
        request.setAttribute("correo",correo);
        request.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias());

        switch (opcion) {
            case "mostrarPerfil":
                BCliente bCliente = clienteDao.mostrarPerfil(correo);
                request.setAttribute("Perfil",bCliente);
                RequestDispatcher view = request.getRequestDispatcher("/FlujoUsuario/profile.jsp");
                view.forward(request, response);
                break;
            case "historialPedidos":
                String dni =  clienteDao.DNI(correo);
                ArrayList<DTOPedidoCliente> pedidos = clienteDao.mostrarHistorial(dni);
                request.setAttribute("listaPedidos",pedidos);
                RequestDispatcher view2 = request.getRequestDispatcher("/FlujoUsuario/historial.jsp");
                view2.forward(request, response);
                break;
            case "mostrarProducto":
                int idProducto =Integer.parseInt(request.getParameter("idProducto"));
                request.setAttribute("producto", clienteDao.buscarProductoporId(ruc,idProducto));
                RequestDispatcher view7 = request.getRequestDispatcher("/FlujoUsuario/paracetamol.jsp");
                view7.forward(request, response);
                break;
            case "carrito":
                bCarritoCliente = clienteDao.buscarCarrito(ruc);
                request.setAttribute("listaCarrito",bCarritoCliente);
                RequestDispatcher view8 = request.getRequestDispatcher("/FlujoUsuario/shopping_cart.jsp");
                view8.forward(request, response);
                break;
            case "limpiarcarrito":
                clienteDao.borrarCarrito();
                bCarritoCliente = clienteDao.buscarCarrito(ruc);
                request.setAttribute("listaCarrito",bCarritoCliente);
                RequestDispatcher view9 = request.getRequestDispatcher("/FlujoUsuario/shopping_cart.jsp");
                view9.forward(request, response);
                break;
            case "borraruno":
                String idcarrito =request.getParameter("idcarrito") != null ? request.getParameter("idcarrito") : "0";
                System.out.println(idcarrito);
                clienteDao.borrarCarritoporId(Integer.parseInt(idcarrito));
                bCarritoCliente = clienteDao.buscarCarrito(ruc);
                request.setAttribute("listaCarrito",bCarritoCliente);
                RequestDispatcher view10 = request.getRequestDispatcher("/FlujoUsuario/shopping_cart.jsp");
                view10.forward(request, response);
                break;
            case "salir":
                RequestDispatcher view1 = request.getRequestDispatcher("/FlujoUsuario/homepage.jsp");
                view1.forward(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "salir";
        String ruc = request.getParameter("ruc");


        request.setAttribute("correo",correo);
        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();

        BFarmacia bFarmacia = new BFarmacia();
        if (ruc != null){
            bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
        }
        //Atributos
        request.setAttribute("farmacia",bFarmacia);

        request.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias());
        switch (opcion) {
            case "Update":
                String nombre = request.getParameter("Nombres") != null ? request.getParameter("Nombres") : "";
                String apellidos = request.getParameter("Apellidos") != null ? request.getParameter("Apellidos") : "";
                String distrito = request.getParameter("Distrito") != null ? request.getParameter("Distrito") : "";
                clienteDao.updatePerfil(nombre, apellidos, distrito, correo);
                response.sendRedirect(request.getContextPath() + "/Usuario?correo=" + correo + "&opcion=mostrarPerfil&ruc="+ruc);
                break;
            case "Buscar":
                String texto = request.getParameter("search");
                if (texto.equalsIgnoreCase("")) {
                    response.sendRedirect(request.getContextPath() + "/Usuario?correo=" + correo + "&opcion=salir&ruc="+ruc);
                } else {
                    request.setAttribute("productos", clienteDao.buscarProducto(ruc,texto));
                    request.setAttribute("BuscarProducto", texto);
                    RequestDispatcher view3 = request.getRequestDispatcher("/FlujoUsuario/jabon.jsp");
                    view3.forward(request, response);
                }
                break;
            case "carrito":
                String cantidad = request.getParameter("cantidad");
                String idProducto=request.getParameter("idProducto");
                if (cantidad != null && idProducto != null){
                    DTOBuscarProductoCliente bBuscarProductoCliente = clienteDao.buscarProductoporId(ruc,Integer.parseInt(idProducto));
                    double cantidad2 = Integer.parseInt(cantidad);
                    cantidad2=cantidad2*bBuscarProductoCliente.getPrecio();
                    clienteDao.agregarAlCarrito(cantidad,idProducto,bBuscarProductoCliente.getStock(),String.valueOf(cantidad2));
                }
                response.sendRedirect(request.getContextPath() + "/Usuario?correo=" + correo + "&opcion=carrito&ruc="+ruc);
                break;
            case "mostrarFarmacia":
                response.sendRedirect(request.getContextPath()+"/Usuario?correo=" + correo + "&opcion=salir&ruc="+ruc);
                break;

        }

    }
}
