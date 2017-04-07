package Zaliczeniowe2.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/login","/register","/login.jsp","/register.jsp"})
public class UnloggedSpaceFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")!=null){
            httpServletResponse.sendRedirect("/user/panel.jsp");
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
