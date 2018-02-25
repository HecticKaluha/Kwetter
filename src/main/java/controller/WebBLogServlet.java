package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.admin.AddPost;
import controller.command.admin.DeletePost;
import controller.command.admin.EditPost;
import controller.command.admin.GetPost;
import controller.command.admin.ShowAdmin;
import controller.command.admin.ToggleAdvanced;
import controller.command.blog.ListBlogs;
import controller.command.blogDetails.AddComment;
import controller.command.blogDetails.BlogDetails;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebBlogServlet", urlPatterns = {
    "/Admin/*",
    "/Blog/*",
    "/BlogDetails/*"
})
public class WebBLogServlet extends HttpServlet {

    private CommandFactory commandFactory;

    public WebBLogServlet() {
        initCommandFactory();
        registerCommands();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getPathInfo();
        if (userPath == null) {
            userPath = request.getServletPath();
        }

        Command command = this.commandFactory.createCommand(userPath, request, response);
        try {
            command.execute();
        } catch (IllegalArgumentException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "The main servlet controlling the web blog.";
    }
    // </editor-fold>

    private void registerCommands() {
        commandFactory.registerCommand("/Blog", ListBlogs.class);
        commandFactory.registerCommand("/AddComment", AddComment.class);
        commandFactory.registerCommand("/BlogDetails", BlogDetails.class);

        commandFactory.registerCommand("/Admin", ShowAdmin.class);
        commandFactory.registerCommand("/AddPost", AddPost.class);
        commandFactory.registerCommand("/DeletePost", DeletePost.class);
        commandFactory.registerCommand("/EditPost", EditPost.class);
        commandFactory.registerCommand("/GetPost", GetPost.class);
        commandFactory.registerCommand("/ToggleAdvanced", ToggleAdvanced.class);
    }

    private void initCommandFactory() {
        this.commandFactory = CommandFactory.getCommandFactory();
    }
}
