package Servlets;

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

public class BestSolutionRawServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("application/json");
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(user.getWebLessonDataListRaw(Integer.parseInt(managerIndex)));
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
