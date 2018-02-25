package controller.command.admin;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Posting;
import service.BlogService;

public class AddPost extends Command {

    private final BlogService service = new BlogService();

    public AddPost(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (author != null && title != null && content != null) {
            Posting posting = service.addPosting(new Posting(author, title, content));

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(posting));
        }
    }
}
