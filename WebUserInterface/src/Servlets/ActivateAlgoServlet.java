package Servlets;

import Constants.Constants;
import Users.PermUserManager;
import Users.SolvingUserManager;
import Users.TimeTableHostManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/algopage/activate
public class ActivateAlgoServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String userID = SessionUtils.getUserID(i_Request);
        String username=SessionUtils.getUsername(i_Request);
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        TimeTableHostManager hostManager=ServletUtils.getTimeTableInstances(getServletContext());
        try{
            checkArguments(i_Request,user,managerIndex);
            hostManager.addSolverToSolvingManager(Integer.parseInt(managerIndex),Integer.parseInt(userID),username);

        }catch(RuntimeException e)
        {
            i_Response.getOutputStream().println(e.getMessage());
            i_Response.setStatus(400);
        }

    }
    private void checkArguments(HttpServletRequest i_Request,User user,String i_ManagerIndex)
    {
        Integer initialPopulation = user.getInitialPopulationByIndex(Integer.parseInt(i_ManagerIndex));
        Boolean isFileLoaded=user.getIsFileLoaded(Integer.parseInt(i_ManagerIndex));
        String showEvery = i_Request.getParameter(Constants.SHOW_EVERY);
        if(!isFileLoaded)
            throw new RuntimeException("Error: Please enter Algorithm preferences first");
        if(showEvery.isEmpty())
            throw new RuntimeException("Error: Please enter show every value");
        Integer showEveryInt;
        try{
            showEveryInt=Integer.parseInt(showEvery);
        }catch(Exception e){
            throw new RuntimeException("Error: Show every must be a number");
        }
        if(showEveryInt>initialPopulation)
            throw new RuntimeException("Error: Show every cant be bigger than initial population");

    }

    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
}
