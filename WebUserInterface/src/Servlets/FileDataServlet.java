package Servlets;

import Constants.Constants;
import DataTransferClasses.SubjectData;
import DataTransferClasses.TeacherData;
import Manager.InstanceManager;
import Users.TimeTableHostManager;
import Users.User;
import Users.UserManager;
import Utils.ServletUtils;
import Utils.SessionUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

//url: LocalHost:8080/TimeTable/pages/algopage/filedata
public class FileDataServlet extends HttpServlet {
    private void processRequest(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException, ServletException {
        i_Response.setContentType("application/json");
        String data=i_Request.getParameter("DATA");
        switch(data)
        {
            case "SUBJECTS":
                subjectsDataBuilder(i_Request,i_Response);
                break;
            case "TEACHERS":
                teacherDataBuilder(i_Request,i_Response);
                break;
        }

        i_Response.setStatus(200);
    }

    private void teacherDataBuilder(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        //every client when entering page three gets a session that explain which problem (index) he is solving
        //we save that index in managerIndex
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        TimeTableHostManager hostManager=ServletUtils.getTimeTableInstances(getServletContext());
        InstanceManager instanceManager=hostManager.getAllInstances().get(Integer.parseInt(managerIndex));

        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            Set<TeacherData> teacherDataSet=instanceManager.getManager().getTeacherDataSet();
            String json = gson.toJson(teacherDataSet);
            out.println(json);
            out.flush();
        }
    }

    private void subjectsDataBuilder(HttpServletRequest i_Request,HttpServletResponse i_Response) throws IOException {
        //every client when entering page three gets a session that explain which problem (index) he is solving
        //we save that index in managerIndex
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        TimeTableHostManager hostManager=ServletUtils.getTimeTableInstances(getServletContext());
        InstanceManager instanceManager=hostManager.getAllInstances().get(Integer.parseInt(managerIndex));

        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            Set<SubjectData> subjectsSet=instanceManager.getManager().getSubjectsDataSet();
            String json = gson.toJson(subjectsSet);
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
