package controller.command.blog;

import controller.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BlogService;

public class ListBlogs extends Command {

    private final BlogService service = new BlogService();

    public ListBlogs(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        RequestDispatcher blogView = request.getRequestDispatcher("WEB-INF/View/MasterView.jsp");
        request.setAttribute("content", "/WEB-INF/View/Blog.jsp");
        request.setAttribute("postings", service.getPostings());
        blogView.forward(request, response);
    }

}
