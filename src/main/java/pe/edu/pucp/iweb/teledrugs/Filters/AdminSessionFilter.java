package pe.edu.pucp.iweb.teledrugs.Filters;

import pe.edu.pucp.iweb.teledrugs.Beans.BAdministrador;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminSessionFilter",urlPatterns = {"/AdminPrincipal","/AgregarFarmacia"})
public class AdminSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        BAdministrador administrador = (BAdministrador) session.getAttribute("admin");

        if(administrador!= null && administrador.getCorreo()!=""){
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            chain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath());
        }


    }
}
