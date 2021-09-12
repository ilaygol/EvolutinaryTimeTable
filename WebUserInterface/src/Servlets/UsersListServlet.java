package Servlets;

import Users.User;
import Users.UserManager;
import Utils.ServletUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//LocalHost:8080/TimeTable/pages/homepage/userList
public class UsersListServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("application/json");
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            UserManager userManager = ServletUtils.getUserManager(getServletContext());
            List<User> users = userManager.getUsers();
            String json = gson.toJson(users);
            out.println(json);
            out.flush();
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
