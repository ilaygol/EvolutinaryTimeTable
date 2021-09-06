package Servlets;
import Users.UserManager;
import Utils.ServletUtils;
import Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserManagementServlet extends HttpServlet{

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("text/html;charset=UTF-8");
        UserManager userManager= ServletUtils.getUserManager(getServletContext());
        String username= SessionUtils.getUsername(i_Request);
        if(username!=null) //old username
        {
            i_Response.sendRedirect("pages/homepage.html");
        }
        else //new username
        {
            String newBrowserUserName =i_Request.getParameter("username");
            if(newBrowserUserName ==null || newBrowserUserName.isEmpty())//not legal text
                i_Response.sendRedirect("pages/homepage.html");
            else {
                newBrowserUserName = newBrowserUserName.trim();
                synchronized (this)
                {
                    if(!userManager.isUserExists(newBrowserUserName))
                    {
                        userManager.addUser(newBrowserUserName);
                        i_Request.getSession(true).setAttribute("userName",newBrowserUserName);
                        i_Response.sendRedirect("pages/homepage.html");
                    }
                    else
                    {
                        String error="Error: Username "+newBrowserUserName+" already exist";
                        i_Request.setAttribute("error",error);
                        getServletContext().getRequestDispatcher("/pages/login_error.jsp").forward(i_Request,i_Response);
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
