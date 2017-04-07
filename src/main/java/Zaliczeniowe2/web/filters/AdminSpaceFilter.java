package Zaliczeniowe2.web.filters;

import Zaliczeniowe2.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminSpaceFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            httpServletResponse.sendRedirect("/login.jsp");
            return;
        }
        else{
            User u = (User)session.getAttribute("user");
            if(u.getPermissionLevel()<3) {
                httpServletResponse.sendRedirect("/login.jsp");
                return;
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
