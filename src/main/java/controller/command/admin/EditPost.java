package controller.command.admin;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Kweet;
import service.BlogService;

public class EditPost extends Command {

    private final BlogService service = new BlogService();

    public EditPost(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        Long postId = Long.parseLong(request.getParameter("id"));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title != null && content != null) {
            Kweet kweet = service.editPosting(postId, author, title, content);

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(kweet));
        }
    }
}
