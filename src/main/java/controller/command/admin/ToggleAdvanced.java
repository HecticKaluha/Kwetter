package controller.command.admin;

import com.google.gson.Gson;
import controller.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToggleAdvanced extends Command {

    public ToggleAdvanced(HttpServletRequest request, HttpServletResponse response) {
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
                advanced = !(Boolean) advancedObject;
            }
            session.setAttribute("advanced", advanced);
        }

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(advanced));
    }
}
