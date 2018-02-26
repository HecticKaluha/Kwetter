package controller.command.admin;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Kweet;
import service.BlogService;

public class AddPost extends Command {

    private final BlogService service = new BlogService();

    public AddPost(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        if (author != null && content != null) {
            Kweet kweet = service.addPosting(new Kweet(author, content, new Date()));

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(kweet));
        }
    }
}
