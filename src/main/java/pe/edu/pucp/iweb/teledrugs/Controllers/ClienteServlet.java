package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Beans.BCliente;
import pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia;
import pe.edu.pucp.iweb.teledrugs.Beans.BProducto;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOBuscarProductoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOCarritoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOPedidoCliente;
import pe.edu.pucp.iweb.teledrugs.DTO.DTOinservible;
import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;
import pe.edu.pucp.iweb.teledrugs.Daos.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.ArrayList;

@WebServlet(name = "ClienteServlet", value = "/Usuario")
@MultipartConfig
public class ClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = 0;
        request.setCharacterEncoding("UTF-8");
        String offset = request.getParameter("offset") != null ? request.getParameter("offset") : "1";
        HttpSession session = request.getSession();
        BCliente bCliente = (BCliente) session.getAttribute("usuario");
        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();
        //Requests
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        String ruc =  session.getAttribute("farmacia") == null ? null : ((BFarmacia) session.getAttribute("farmacia")).getRuc() ;
        //Atributos
        switch (opcion) {
            case "mostrarPerfil":
                RequestDispatcher view = request.getRequestDispatcher("/FlujoUsuario/profile.jsp");
                view.forward(request, response);
                break;
            case "historialPedidos":

                String dni =  clienteDao.DNI(bCliente.getLogueoCorreo());
                ArrayList<DTOPedidoCliente> pedidos = clienteDao.mostrarHistorial(dni);
                ArrayList<DTOPedidoCliente> pedidos2 = clienteDao.mostrarHistorialPaginacion(dni,offset);
                if(pedidos.size() % 4 == 0){
                    index = pedidos.size()/4;
                }else{
                    index= 1 + (pedidos.size()/4);
                }
                request.setAttribute("pagina",Integer.parseInt(offset));
                request.setAttribute("index", index);
                session.setAttribute("listaPedidos",pedidos2);
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
                    ArrayList<BProducto> listarProductos2 = farmaciaDao.listarProductosPaginacion(ruc,offset);
                    if(listarProductos.size() % 8 == 0){
                        index = listarProductos.size()/8;
                    }else{
                        index= 1 + (listarProductos.size()/8);
                    }
                    session.setAttribute("listaProducto",listarProductos2);
                    bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
                }
                request.setAttribute("pagina",Integer.parseInt(offset));
                request.setAttribute("index", index);
                session.setAttribute("farmacia",bFarmacia);
                session.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias(bCliente.getDistrito()));
                RequestDispatcher view1 = request.getRequestDispatcher("/FlujoUsuario/homepage.jsp");
                view1.forward(request, response);
                break;
            case "Buscar":
                String texto = (String) session.getAttribute("texto");
                ArrayList<DTOBuscarProductoCliente> productos = clienteDao.buscarProducto(ruc,texto);
                ArrayList<DTOBuscarProductoCliente> productos2= clienteDao.buscarProductoPaginacion(ruc,texto, offset);
                if(productos.size() % 8 == 0){
                    index = productos.size()/8;
                }else{
                    index= 1 + (productos.size()/8);
                }
                request.setAttribute("pagina",Integer.parseInt(offset));
                request.setAttribute("index", index);
                request.setAttribute("productos", productos2);

                RequestDispatcher view10 = request.getRequestDispatcher("/FlujoUsuario/jabon.jsp");
                view10.forward(request,response);

                break;
            case "logout":
                session.invalidate();
                RequestDispatcher view11 = request.getRequestDispatcher("/Login/iniciar.jsp");
                view11.forward(request,response);

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        BCliente bCliente = (BCliente) session.getAttribute("usuario");

        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        ArrayList<DTOCarritoCliente> carritoClientes = session.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("carrito");
        String ruc=null;
        ClienteDao clienteDao = new ClienteDao();
        FarmaciaDao farmaciaDao= new FarmaciaDao();
        session.setAttribute("listafarmacias",farmaciaDao.mostrarListaFarmacias(bCliente.getDistrito()));
        //Atributos
        switch (opcion) {
            case "Update":
                String nombre = request.getParameter("Nombres") != null ? request.getParameter("Nombres") : "";
                String apellidos = request.getParameter("Apellidos") != null ? request.getParameter("Apellidos") : "";
                String distrito = request.getParameter("Distrito") != null ? request.getParameter("Distrito") : "";
                if(!nombre.equalsIgnoreCase("") && !apellidos.equalsIgnoreCase("") && !distrito.equalsIgnoreCase("")){
                    if(clienteDao.nombreyApellidoValid(nombre)){
                        if (clienteDao.nombreyApellidoValid(apellidos)){
                            clienteDao.updatePerfil(nombre, apellidos, distrito, bCliente.getLogueoCorreo());
                        }else{
                            session.setAttribute("msg","No se permiten numeros en el apellido... RESPETA");
                        }
                    }else{
                        session.setAttribute("msg","No se permiten numeros en el nombre... RESPETA");
                    }
                }else{
                    session.setAttribute("msg", "No deje los parametros vacios... No te hagas!!");
                }
                session.setAttribute("usuario",clienteDao.obtenerCliente(bCliente.getLogueoCorreo()));
                response.sendRedirect(request.getContextPath() + "/Usuario?opcion=mostrarPerfil");
                break;
            case "Buscar":
                String texto = request.getParameter("search");
                if (texto.equalsIgnoreCase("")) {
                    response.sendRedirect(request.getContextPath()+"/Usuario");
                } else {
                    session.setAttribute("texto", texto);
                    response.sendRedirect(request.getContextPath()+"/Usuario?opcion=Buscar");
                }
                break;
            case "carrito":
                String cantidad = request.getParameter("cantidad") != null ? request.getParameter("cantidad") : "0";
                String idProducto = (String) session.getAttribute("idProducto");
                ruc =  session.getAttribute("farmacia") == null ? null : ((BFarmacia) session.getAttribute("farmacia")).getRuc() ;
                if (!cantidad.equalsIgnoreCase("0") && !cantidad.equalsIgnoreCase("")) {
                    DTOBuscarProductoCliente b = clienteDao.buscarProductoporId(ruc, Integer.parseInt(idProducto));
                    double cantidad2 = Integer.parseInt(cantidad);
                    if(cantidad2 <= Double.parseDouble(b.getStock())){
                        cantidad2 = cantidad2 * b.getPrecio();
                        carritoClientes.add(new DTOCarritoCliente(b.getNombre(),String.valueOf(b.getIdProducto()),b.getStock(),Integer.parseInt(cantidad),Double.parseDouble(new DecimalFormat("#.##").format(cantidad2)),b.getFoto(),b.isRequiereReceta(),null));
                        session.setAttribute("carrito",carritoClientes);
                        response.sendRedirect(request.getContextPath() + "/Usuario?opcion=carrito");
                    }else{
                        session.setAttribute("msg","La cantidad de productos solicitados supera nuestro limite");
                        response.sendRedirect(request.getContextPath() + "/Usuario?opcion=mostrarProducto");
                    }
                }else{
                    if(request.getParameter("cantidad") == null){
                        response.sendRedirect(request.getContextPath() + "/Usuario?opcion=carrito");
                    }else{
                        session.setAttribute("msg","Por favor ingresa una cantidad valida... Poner cero es de idiotas");
                        response.sendRedirect(request.getContextPath() + "/Usuario?opcion=mostrarProducto");
                    }
                }

                break;
            case "mostrarFarmacia":
                ruc= request.getParameter("ruc");
                BFarmacia bFarmacia = new BFarmacia();
                if (ruc != null){
                    bFarmacia=farmaciaDao.mostrarFarmacia(ruc);
                }
                session.removeAttribute("farmacia");
                session.setAttribute("farmacia",bFarmacia);
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=salir");
                break;
            case "mostrarProducto":
                session.setAttribute("idProducto", request.getParameter("idProducto"));
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=mostrarProducto");
                break;
            case "borraruno":
                int carrito = (Integer) session.getAttribute("idcarrito");
                System.out.println(carrito);
                carritoClientes.remove(carrito);
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                break;
            case "comprar":
                String fecha = request.getParameter("fecha");
                String hora = request.getParameter("hora") + ":00";
                ArrayList<Integer> indexes = session.getAttribute("listaRecetas") != null ? (ArrayList) session.getAttribute("listaRecetas") : new ArrayList<>();
                ArrayList<String> nombres=new ArrayList<>();
                for(Integer numero : indexes){
                    Part blob = request.getPart(String.valueOf(numero));
                    System.out.println("INPUT: "+blob.getInputStream().available());
                    if(carritoClientes.get(numero).getReceta() == null && blob.getInputStream().available() == 0){
                        session.setAttribute("msg",carritoClientes.get(numero).getProducto() + " requiere receta por favor adjuntar...");
                        break;
                    }else if( (carritoClientes.get(numero).getReceta() == null && blob.getInputStream().available() > 0) || (carritoClientes.get(numero).getReceta() != null && blob.getInputStream().available() > 0)){
                        nombres.add(blob.getContentType());
                        InputStream inputStream = blob.getInputStream();
                        carritoClientes.get(numero).setReceta(inputStream);
                    }
                }
                if(session.getAttribute("msg") == null && carritoClientes.size() > 0){
                    if(!fecha.equalsIgnoreCase("")){
                        if(!hora.equalsIgnoreCase(":00")){
                            if(clienteDao.validarFecha(fecha + " " + hora) ){
                                ArrayList<DTOinservible> dtOinservibles = clienteDao.listaSinRepetir(carritoClientes);
                                if(clienteDao.validarStock(dtOinservibles)){
                                    boolean bandera=true;
                                    for(String nombre1 : nombres){
                                        if(!nombre1.startsWith("image/")){
                                            bandera=false;
                                        }
                                    }
                                    if (bandera) {
                                        clienteDao.restarStock(dtOinservibles);
                                        clienteDao.crearPedido(carritoClientes, fecha , hora, bCliente.getDNI());
                                        session.removeAttribute("carrito");
                                        session.removeAttribute("listaRecetas");
                                        response.sendRedirect(request.getContextPath()+"/Usuario?opcion=historialPedidos");
                                    }else{
                                        session.setAttribute("msg","No me hagas perder el tiempo, pon archivos de imagen... ");
                                        response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                                    }

                                }else{
                                    session.setAttribute("msg","No me hagas perder el tiempo, no te creas habil, pide lo necesario ni que tuvieras tanta plata...-_-");
                                    response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                                }
                            }else{
                                session.setAttribute("msg","Por favor ingresa una fecha mayor a la actual... No se puede retroceder en el tiempo :)");
                                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                            }
                        }else{
                            session.setAttribute("msg","Por favor ingrese una hora... No somos adivinos con su hora de entrega :)");
                            response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                        }

                    }else{
                        session.setAttribute("msg","Por favor ingrese una fecha... No somos adivinos con su fecha de entrega :)");
                        response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                    }
                }else{
                    if(carritoClientes.size()==0){
                        session.setAttribute("msg", "Si no va a comprar nada no aprete comprar...");
                    }
                    response.sendRedirect(request.getContextPath()+"/Usuario?opcion=carrito");
                }
                break;
            case "borrarPedido":
                ArrayList<DTOinservible> dtOinservibles = clienteDao.listaStockSumar(request.getParameter("numeroOrden"));
                clienteDao.sumarStock(dtOinservibles);
                clienteDao.cancelarPedido(Integer.parseInt(request.getParameter("numeroOrden")));
                response.sendRedirect(request.getContextPath()+"/Usuario?opcion=historialPedidos");
                break;
        }

    }
}
