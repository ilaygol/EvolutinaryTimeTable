package Servlets;

import Users.UserManager;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/homepage/logout
public class LogoutServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        UserManager userManager= ServletUtils.getUserManager(getServletContext());
        String userID= SessionUtils.getUserID(i_Request);
        if(userID!=null)
        {
            userManager.removeUser(Integer.parseInt(userID));
            SessionUtils.removeSession(i_Request);
            i_Response.sendRedirect(i_Request.getContextPath()+"/index.html");
        }
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
