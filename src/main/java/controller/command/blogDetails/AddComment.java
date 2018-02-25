package controller.command.blogDetails;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Posting;
import service.BlogService;

public class AddComment extends Command {

    private final BlogService service = new BlogService();

    public AddComment(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        Long postId = Long.parseLong(request.getParameter("id"));
        String message = request.getParameter("message");

        if (message != null) {
            Posting posting = service.getPosting(postId);
            posting.addComment(message);

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(posting.getComments()));
        }
    }
}