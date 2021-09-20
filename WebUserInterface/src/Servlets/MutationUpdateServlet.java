package Servlets;

import Constants.Constants;
import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MutationUpdateServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {

    }







    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String name=i_Request.getParameter("mutationName");
        String probability=i_Request.getParameter("mutationProbability");
        String component=i_Request.getParameter("mutationComponent");
        String tupples=i_Request.getParameter("mutationTupples");
        String mutationIndex=i_Request.getSession().getAttribute(Constants.MUTATION_INDEX).toString();
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try
        {
            user.updateMutationByIndex(Integer.parseInt(managerIndex),Integer.parseInt(mutationIndex),name,tupples,component,probability);
            i_Response.getOutputStream().println("Mutation has been updated successfully");
            i_Response.setStatus(200);
        }
        catch(Exception e) {
            i_Response.getOutputStream().println(e.getMessage());
            i_Response.setStatus(400);
        }
    }
    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String mutationIndex=i_Request.getParameter(Constants.MUTATION_INDEX);
        i_Request.getSession().setAttribute(Constants.MUTATION_INDEX,mutationIndex);
        i_Response.setStatus(200);
    }
}
