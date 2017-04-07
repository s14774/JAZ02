package Zaliczeniowe2.web;

import Zaliczeniowe2.Utils.DummyUserDB;
import Zaliczeniowe2.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/listuser")
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<table border=\"1\">");
        resp.getWriter().println("<tr><td>Login</td><td>Email</td><td>Permissions</td></tr>");

        for(User u : DummyUserDB.getUsers(getServletConfig().getServletContext())){
            resp.getWriter().print("<tr>");
            resp.getWriter().print("<td>" + u.getLogin() + "</td>");
            resp.getWriter().print("<td>" + u.getEmail() + "</td>");
            resp.getWriter().print("<td>" + u.getPermissionLevel() + "</td>");
            //resp.getWriter().print("<td>" + u.getPassword() + "</td>");
            resp.getWriter().print("</tr>");
        }
        resp.getWriter().println("</table>");
    }
}
