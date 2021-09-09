package Servlets;

import Utils.SessionUtils;
import Constants.Constants;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: localHost:8080/TimeTable/index
public class IndexServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_request, HttpServletResponse i_response) throws IOException, ServletException{
        String username= SessionUtils.getUsername(i_request);
        if(username==null){
            i_response.getOutputStream().println(Constants.LOGIN_PAGE_PATH);
        }
        else{
            i_response.getOutputStream().println(Constants.HOME_PAGE_PATH);
        }
        i_response.setStatus(200);
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
