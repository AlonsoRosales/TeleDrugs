package pe.edu.pucp.iweb.teledrugs.Filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "OpcionFilter" ,urlPatterns = {"/Usuario"})
public class OpcionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "salir";
        System.out.println("Entro al filtro");
        System.out.println(opcion);


        System.out.println(opcion.equalsIgnoreCase("salir"));


        if(opcion.equalsIgnoreCase("mostrarPerfil") ||opcion.equalsIgnoreCase("historialPedidos") ||opcion.equalsIgnoreCase("mostrarProducto")
                ||opcion.equalsIgnoreCase("carrito") ||opcion.equalsIgnoreCase("limpiarcarrito")||opcion.equalsIgnoreCase("salir")||opcion.equalsIgnoreCase("logout")||opcion.equalsIgnoreCase("buscar")||opcion.equalsIgnoreCase("mostrarFarmacia")||opcion.equalsIgnoreCase("borraruno")||opcion.equalsIgnoreCase("comprar")||opcion.equalsIgnoreCase("borrarPedido")){
            chain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath()+"/Usuario");
        }


    }
}
