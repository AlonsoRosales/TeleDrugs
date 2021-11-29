package pe.edu.pucp.iweb.teledrugs.Controllers;

import pe.edu.pucp.iweb.teledrugs.Daos.ClienteDao;


/*import javax.mail.*;*/
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
//-------------------------------------------------
/*import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;*/
//------------------------------------------------


@WebServlet(name = "recuperarcontrasena", value = "/RecuperarContrasena")
public class RecuperarContrasena extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher view = request.getRequestDispatcher("/Login/recuperar.jsp");
        view.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String correo = request.getParameter("Correo");
        String DNI = request.getParameter("DNI");

        //SE VERIFICA QUE EXISTA ESAS CREDENCIALES , LO QUE SE INGRESO AL CAMPO ESTE BIEN , TODAVIA NO QUE EXISTA EN LA BASE DE DATOS
        //SE VERIFICA DNI
        ClienteDao clienteDao = new ClienteDao();
        boolean dniCorrecto = clienteDao.dniValid(DNI);

        //SE VERIFICA EMAIL
        boolean correoCorrecto = clienteDao.emailisValid(correo);
        HttpSession session = request.getSession();
        if(dniCorrecto & correoCorrecto){
            //TOCA HACER LA VALIDACION DE SI EXISTE UN USUARIO CON ESAS CREDENCIALES
            boolean existeCliente = clienteDao.existeCliente(correo,DNI);
            System.out.println(existeCliente);
            if(!existeCliente){
                //SI NO EXISTE EL CLIENTE SE MUESTRA UN MENSAJE DE ERROR FEEDBACK
                session.setAttribute("err","No existe un cliente con esas credenciales!"); // TIENE SENTIDO DECIR QUE NO EXISTE EL CLIENTE?
                response.sendRedirect(request.getContextPath() + "/RecuperarContrasena");
            }else{
                //----------------------------------------------------------------------------------------------------------------------------------------
               /* String correo2 = "teledrugs2021@gmail.com";
                String contra = "paeocxrkjrtkcyuy";
                //String correoDestino = "teledrugs2021@gmail.com";
                // CONTRASEÑA 16 CARACETERES QUE SE TIENE QUE USAR -> paeocxrkjrtkcyuy
                Properties p = new Properties();
                p.put("mail.smtp.host","smtp.gmail.com");
                p.setProperty("mail.smtp.starttls.enable","true");
                p.put("mail.smtp.ssl.trust","smtp.gmail.com");
                p.setProperty("mail.smtp.port","587");
                p.setProperty("mail.smtp.user",correo2);
                p.setProperty("mail.smtp.auth","true");

                Session s = Session.getDefaultInstance(p);
                MimeMessage mensaje = new MimeMessage(s);
                try {
                    mensaje.setFrom(new InternetAddress(correo2));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress (correo));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    mensaje.setSubject("Prueba de video dia 22");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                String link = "www.youtube.com";
                try {
                    mensaje.setText(link);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                //mensaje.setText("Este es un mensaje que se envia desde JAVA");

                Transport t = null;
                try {
                    t = s.getTransport("smtp");
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }
                try {
                    t.connect(correo2,contra);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    t.sendMessage(mensaje,mensaje.getAllRecipients());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    t.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Mensaje enviado");
                */
                //----------------------------------------------------------------------------------------------------------------------------------------
                response.sendRedirect(request.getContextPath() +  "/RecuperarContrasena");
            }
        }else{
            //SI NO ESTA CORRECTO SE MUESTRA UN MENSAJE DE FEEDBACK
            session.setAttribute("err","Datos ingresados incorrectamente!");
            response.sendRedirect(request.getContextPath() + "/RecuperarContrasena");
        }


    }
}
