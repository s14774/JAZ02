package Zaliczeniowe2.web.filters;

import Zaliczeniowe2.Utils.DummyUserDB;
import Zaliczeniowe2.Utils.StringUtils;
import Zaliczeniowe2.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/premiumset")
public class PremiumSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = getServletConfig().getServletContext();

        String login = req.getParameter("login");
        String premium = req.getParameter("premium");

        if (!StringUtils.isEmpty(login)){
            User u = DummyUserDB.getUserByLogin(application,login);
            if(u==null) {
                resp.sendRedirect("/admin/premiumset.jsp");
            }
            else{
                if(!StringUtils.isEmpty(premium)) {
                    u.setPermissionLevel(2);
                    DummyUserDB.updateUser(u,application);
                }
                else{
                    u.setPermissionLevel(1);
                    DummyUserDB.updateUser(u,application);
                }
                resp.sendRedirect("/admin/listuser");
            }
        }
        else{
            resp.sendRedirect("/admin/premiumset.jsp");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/admin/premiumset.jsp");
    }
}
