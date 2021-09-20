package Servlets;

import Constants.Constants;
import DataTransferClasses.AlgorithmReferenceData;
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
import java.lang.invoke.ConstantCallSite;


//url: LocalHost:8080/TimeTable/pages/algopage/algoReferences
public class AlgoReferenceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("application/json");
        String userID = SessionUtils.getUserID(i_Request);
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        AlgorithmReferenceData algorithmData=user.getAlgorithmReferenceData(Integer.parseInt(managerIndex));
        try(PrintWriter out=i_Response.getWriter()) {
            Gson gson = new Gson();
            String json = gson.toJson(algorithmData);
            out.println(json);
            out.flush();
        }
        i_Response.setStatus(200);
    }


    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        //String initialPopulation, reqGenerations, printingReq, reqFitness, reqTimeInMinutes, crossoverName, i_NumOfCuttingPoints, i_CrossoverComponent, i_SelectionType,i_Percent,i_PTE,i_Elitism;
        String userID = SessionUtils.getUserID(i_Request);
        String managerIndex = SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager = ServletUtils.getPermUserManager(getServletContext());
        User user = permUserManager.getUserByID(userID);
        String generationCheck=i_Request.getParameter(Constants.GENERATIONS_CHECK);
        String timeCheck=i_Request.getParameter(Constants.TIME_CHECK);
        String fitnessCheck=i_Request.getParameter(Constants.FITNESS_CHECK);
        if(generationCheck==null&&timeCheck==null&&fitnessCheck==null) {
            i_Response.setStatus(400);
            String errorMessage="Error, Please choose at least one stopping condition";
            i_Response.getOutputStream().println(errorMessage);
        }
        else
        {
            try {
                user.updateAlgoReferences(Integer.parseInt(managerIndex), i_Request.getParameter(Constants.INITIAL_POPULATION), i_Request.getParameter(Constants.GENERATIONS_TEXT),
                        i_Request.getParameter(Constants.FITNESS_TEXT), i_Request.getParameter(Constants.TIME_TEXT), i_Request.getParameter(Constants.CROSSOVER_TYPE),
                        i_Request.getParameter(Constants.CROSSOVER_CUTTING), i_Request.getParameter(Constants.CROSSOVER_ASPECT), i_Request.getParameter(Constants.SELECTION_TYPE),
                        i_Request.getParameter(Constants.SELECTION_PERCENT), i_Request.getParameter(Constants.SELECTION_PTE), i_Request.getParameter(Constants.SELECTION_ELITISM));
                i_Response.setStatus(200);
            } catch (Exception e) {
                i_Response.getOutputStream().println(e.getMessage());
                i_Response.setStatus(400);
            }
        }

    }


}
