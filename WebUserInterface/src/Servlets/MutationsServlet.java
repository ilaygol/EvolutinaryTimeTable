package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//url: LocalHost:8080/TimeTable/pages/algopage/mutation
public class MutationsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
    {
        i_Response.setContentType("text/plain;charset=UTF-8");
        i_Response.setStatus(200);
    }


    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
    {
        i_Response.setContentType("text/plain;charset=UTF-8");
        i_Response.setStatus(200);


    }

    @Override
    protected void doPut(HttpServletRequest i_Request, HttpServletResponse i_Response)
    {}


    @Override
    protected void doDelete(HttpServletRequest i_Request, HttpServletResponse i_Response)
    {}
}
