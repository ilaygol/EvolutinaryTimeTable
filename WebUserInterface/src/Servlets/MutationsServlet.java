package Servlets;

import Constants.Constants;
import Manager.LogicEngineManager;
import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//url: LocalHost:8080/TimeTable/pages/algopage/mutation
public class MutationsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String name=i_Request.getParameter("mutationName");
        String probability=i_Request.getParameter("mutationProbability");
        String component=i_Request.getParameter("mutationComponent");
        String tupples=i_Request.getParameter("mutationTupples");
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try
        {
            user.addNewMutationToManager(Integer.parseInt(managerIndex),name,tupples,component,probability);
            i_Response.setStatus(200);
        }catch (RuntimeException exception)
        {
            i_Response.getOutputStream().println(exception.getMessage());
            i_Response.setStatus(400);
        }
    }


    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("application/json");
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(user.getMutationDataListByManagerIndex(Integer.parseInt(managerIndex)));
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
    }

    protected void doDelete(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String mutationIndex=i_Request.getHeader(Constants.MUTATION_INDEX);
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        user.deleteMutationByIndex(Integer.parseInt(managerIndex),Integer.parseInt(mutationIndex));
        i_Response.setStatus(200);
        i_Response.getOutputStream().println("Mutation has been deleted successfully");
    }

    @Override
    protected void doPut(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String name=i_Request.getHeader("mutationName");
        String probability=i_Request.getHeader("mutationProbability");
        String component=i_Request.getHeader("mutationComponent");
        String tupples=i_Request.getHeader("mutationTupples");
        String mutationIndex=i_Request.getHeader(Constants.MUTATION_INDEX);
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
}
