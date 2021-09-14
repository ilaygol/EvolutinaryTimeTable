package Servlets;

import Constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: LocalHost:8080/TimeTable/pages/homepage/algo-entry
public class AlgoPageEntry extends HttpServlet {
    private void processRequest(HttpServletRequest i_request, HttpServletResponse i_response) throws IOException {
        i_response.setContentType("text/plain;charset=UTF-8");
        String managerIndex=i_request.getParameter(Constants.MANAGER_INDEX);
        i_request.getSession(true).setAttribute(Constants.MANAGER_INDEX,managerIndex);
        i_response.getOutputStream().println(Constants.ALGO_PAGE);
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
