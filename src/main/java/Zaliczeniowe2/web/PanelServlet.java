package Zaliczeniowe2.web;

import Zaliczeniowe2.Utils.DummyDB.DummyUserDB;
import Zaliczeniowe2.Utils.StringUtils;
import Zaliczeniowe2.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/panel")
public class PanelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        User u = (User)session.getAttribute("user");

        out.println("<h1>About You</h1><br/>");
        out.println("Login: "+ u.getLogin() +"<br/>");
        out.println("Email: "+ u.getEmail() +"<br/>");
        out.print("Premium: ");
        out.print(u.getPermissionLevel().intValue()<2 ?"No":"Yes");
        out.println("<br/>");

        out.println("<h1>User Space</h1><br/>");
        //links for users

        if(u.getPermissionLevel()>1){
            out.println("<h1>Premium Space</h1><br/>");
            //links for Premium Users
            out.println("<a href='/premium/premium.jsp'>Premium</a><br/>");
        }

        if(u.getPermissionLevel()>2){
            out.println("<h1>Admin Space</h1><br/>");
            //links for Admin Users
            out.println("<a href='/admin/listuser'>List of users</a><br/>");
            out.println("<a href='/admin/premiumset.jsp'>Set premium</a><br/>");
        }
        out.println("<a href='/user/logout'>Log Out</a><br/>");
    }
}
