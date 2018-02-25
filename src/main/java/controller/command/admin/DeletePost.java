package controller.command.admin;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BlogService;

public class DeletePost extends Command {

    private final BlogService service = new BlogService();

    public DeletePost(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        Long postId = Long.parseLong(request.getParameter("id"));

        service.removePosting(postId);

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(postId));
    }
}
