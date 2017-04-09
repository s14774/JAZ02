package Zaliczeniowe2.web;

import Zaliczeniowe2.Utils.DummyUserDB;
import Zaliczeniowe2.Utils.SimpleHyperSQL;
import Zaliczeniowe2.Utils.StringUtils;
import Zaliczeniowe2.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = getServletConfig().getServletContext();
        //resp.getWriter().println("Rejestracja");

        String login = req.getParameter("login");
        String passw = req.getParameter("password");
        String passw2 = req.getParameter("confirmpassword");
        String email = req.getParameter("email");

        if (StringUtils.isAnyEmpty(login,passw,passw2,email)
                || !passw.equals(passw2)
                || DummyUserDB.getUserByLogin(application,login)!=null //!
                || DummyUserDB.getUserByEmail(application,email)!=null){ //!
            resp.sendRedirect("/register.jsp");
        }
        else {
            User user = new User(login, StringUtils.makeSHA256(passw), email);
            //DummyUserDB.addUser(user, application);
            SimpleHyperSQL.AddUser(user);
            resp.sendRedirect("/login.jsp");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/register.jsp");
    }
}
