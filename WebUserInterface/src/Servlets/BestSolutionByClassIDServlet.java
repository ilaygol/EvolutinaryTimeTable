package Servlets;

import Constants.Constants;
import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//url: LocalHost:8080/TimeTable/pages/algopage/classSolution
public class BestSolutionByClassIDServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("application/json");
        String classID=i_Request.getParameter(Constants.CLASS_ID);
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);

        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(user.getBestSolutionByClassID(Integer.parseInt(managerIndex),Integer.parseInt(classID)));
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
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