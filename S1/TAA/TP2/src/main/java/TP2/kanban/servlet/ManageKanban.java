package TP2.kanban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TP2.kanban.dao.KanbanDAO;
import TP2.kanban.metier.Kanban;

@WebServlet(name = "managekanban", urlPatterns = { "/kanban/ManageKanban" })
public class ManageKanban extends HttpServlet
{
    private static final long serialVersionUID = 110184269608584189L;
    
    private void displayManager(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        KanbanDAO kanbanDAO = new KanbanDAO();
        
        kanbanDAO.createKanbans();
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.print("<html>\n" + 
                "\n" + 
                "<head>\n" + 
                "    <meta charset=\"utf-8\">\n" + 
                "    <title>Manage Kanban</title>\n" + 
                "\n" + 
                "    <style>\n" + 
                "        table {\n" + 
                "            font-family: arial, sans-serif;\n" + 
                "            border-collapse: collapse;\n" + 
                "        }\n" + 
                "        \n" + 
                "        td,\n" + 
                "        th {\n" + 
                "            border: 1px solid #dddddd;\n" + 
                "            text-align: left;\n" + 
                "            padding: 8px;\n" + 
                "        }\n" + 
                "    </style>\n" + 
                "</head>\n" + 
                "\n" + 
                "<body>\n" + 
                "    <table>\n" + 
                "        <tr>\n" + 
                "            <th>ID</th>\n" + 
                "            <th>Nom</th>\n" + 
                "        </tr>");
        
        for (Kanban kanban : kanbanDAO.findAll())
        {
            out.print("        <tr>\n" + 
                    "            <td>" + kanban.getId() + "</td>\n" + 
                    "            <td>" + kanban.getName() + "</td>\n" + 
                    "        </tr>");
        }
        
        out.print("</table> <br>\n" + 
                "\n" + 
                "    <form action=\"ManageKanban\" method=\"post\">\n" + 
                "        <label for=\"name\">Nom du Kanban :</label><br>\n" + 
                "        <input type=\"text\" id=\"name\" name=\"name\"><br>\n" + 
                "        <input type=\"submit\" value=\"Envoyer\">\n" + 
                "    </form>\n" + 
                "</body>\n" + 
                "\n" + 
                "</html>");
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        displayManager(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        KanbanDAO kanbanDAO = new KanbanDAO();
        
        kanbanDAO.save(new Kanban(request.getParameter("name")));
        
        displayManager(request, response);
    }
}
