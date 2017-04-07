package Zajecia.web;

import Zajecia.Repositories.ConferenceApplicationRepository;
import Zajecia.Repositories.DummyApplicationRepository;
import Zajecia.domain.ConferenceApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add")
public class AddApplicantServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private ConferenceApplication retriveApplicationFromRequest(HttpServletRequest req){
        ConferenceApplication result = new ConferenceApplication();
        result.setName(req.getParameter("name"));
        result.setSurname(req.getParameter("surname"));
        result.setAdvertisement(req.getParameter("info"));
        result.setEmail(req.getParameter("email"));
        result.setEmployment(req.getParameter("employment"));
        return result;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ConferenceApplication application = retriveApplicationFromRequest(req);
        ConferenceApplicationRepository repository = new DummyApplicationRepository();

        HttpSession session = req.getSession();
        if(session.getAttribute("conf")!=null){
            resp.getWriter().println("Powtórne wypełnianie zostało zablokowane");
            return;
        }
        session.setAttribute("conf",application);

        repository.add(application);
        resp.sendRedirect("success.jsp");
    }
}
