package controller.command.admin;

import controller.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.BlogService;

public class ShowAdmin extends Command {

    private final BlogService service = new BlogService();

    public ShowAdmin(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean advanced = false;
        if (session.isNew()) {
            session.setAttribute("advanced", advanced);
        } else {
            Object advancedObject = session.getAttribute("advanced");
            if (advancedObject != null) {
                advanced = (Boolean) advancedObject;
            }
        }

        RequestDispatcher adminView = request.getRequestDispatcher("WEB-INF/View/MasterView.jsp");
        request.setAttribute("content", "/WEB-INF/View/Admin.jsp");
        request.setAttribute("postings", service.getPostings());
        request.setAttribute("advanced", advanced);
        adminView.forward(request, response);
    }
}
