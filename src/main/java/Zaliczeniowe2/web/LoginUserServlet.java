package Zaliczeniowe2.web;

import Zaliczeniowe2.Utils.DummyUserDB;
import Zaliczeniowe2.Utils.SimpleHyperSQL;
import Zaliczeniowe2.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String url="jdbc:hsqldb:hsql://localhost/workdb";
    Connection connection = null;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = getServletConfig().getServletContext();
        String login = req.getParameter("login");
        String passw = req.getParameter("password");

        //User u = DummyUserDB.tryLogin(application,login,passw);
        User u = SimpleHyperSQL.TryLogin(login, passw);
        if(u == null){
            resp.sendRedirect("/login.jsp");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", u);
        resp.sendRedirect("/user/panel");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("/login.jsp");

    	try {
    		
			Class.forName("org.hsqldb.jdbcDriver");
	        connection = DriverManager.getConnection(url);
	        resp.getWriter().print(connection.getMetaData().getTables(null, null, "USERS", null).next());
	    	connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print(e.toString());
		}
    }
}
