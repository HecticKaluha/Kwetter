package controller.command.blogDetails;

import controller.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BlogService;

public class BlogDetails extends Command {

    private BlogService service = new BlogService();

    public BlogDetails(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        RequestDispatcher blogDetailsView = request.getRequestDispatcher("WEB-INF/View/MasterView.jsp");
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("posting", service.getPosting(id));
        request.setAttribute("postings", service.getPostings());
        request.setAttribute("content", "/WEB-INF/View/BlogDetails.jsp");
        blogDetailsView.forward(request, response);
    }
}