package TP2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "mytest", urlPatterns = { "/myurl" })
public class MyServlet extends HttpServlet
{
    private static final long serialVersionUID = -7480506594083656950L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        PrintWriter p = new PrintWriter(resp.getOutputStream());
        p.print("Hello world TAA");
        p.flush();
    }
}
