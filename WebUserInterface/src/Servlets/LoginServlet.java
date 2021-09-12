package Servlets;
import Constants.Constants;
import Users.UserManager;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/loginpage/login
public class LoginServlet extends HttpServlet{

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        UserManager userManager= ServletUtils.getUserManager(getServletContext());
        String userID= SessionUtils.getUserID(i_Request);
        if(userID!=null) //old client
        {
            i_Response.setStatus(200);
            i_Response.getOutputStream().println(Constants.HOME_PAGE_PATH);
        }
        else //new client
        {
            String newBrowserUserName =i_Request.getParameter(Constants.USERNAME);
            if(newBrowserUserName ==null || newBrowserUserName.isEmpty())//not legal text
            {
                i_Response.setStatus(401);
                i_Response.getOutputStream().println("Error: Please enter username");
            }
            else {
                newBrowserUserName = newBrowserUserName.trim();
                synchronized (this)
                {
                    if(!userManager.isUserExists(newBrowserUserName))
                    {
                        Integer userId=userManager.addUser(newBrowserUserName);
                        i_Request.getSession(true).setAttribute(Constants.USER_ID,userId);
                        i_Response.setStatus(200);
                        i_Response.getOutputStream().println(Constants.HOME_PAGE_PATH);
                    }
                    else
                    {
                        String error="Error: Username "+newBrowserUserName+" already exist";
                        i_Response.setStatus(401);
                        i_Response.getOutputStream().println(error);
                    }
                }
            }


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
