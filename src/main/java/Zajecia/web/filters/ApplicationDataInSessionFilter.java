package Zajecia.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/add")
public class ApplicationDataInSessionFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("conf")!=null){
            response.getWriter().println("Powtórne wypełnianie zostało zablokowane (F)");
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
