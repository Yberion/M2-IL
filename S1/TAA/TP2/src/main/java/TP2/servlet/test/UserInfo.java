package TP2.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userinfo", urlPatterns = { "/UserInfo" })
public class UserInfo extends HttpServlet
{
    private static final long serialVersionUID = -7841847282256465093L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.print("<html>\r\n" + 
                "<head>" +
                "    <meta charset=\"utf-8\">" +
                "    <title>Basic informations</title>" +
                "</head>" +
                "<body>\r\n" + 
                "    <ul>\r\n" + 
                "        <li>Prénom : " + request.getParameter("fname") + "</li>\r\n" + 
                "        <li>Nom de famille : " + request.getParameter("lname") + "</li>\r\n" + 
                "        <li>Âge : " + request.getParameter("age") + "</li>\r\n" + 
                "    </ul>\r\n" + 
                "</body>\r\n" + 
                "</html>");
    }
}