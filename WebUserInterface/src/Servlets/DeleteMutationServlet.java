package Servlets;
import Constants.Constants;
import Users.PermUserManager;
import Users.User;
import Utils.ServletUtils;
import Utils.SessionUtils;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



//url: LocalHost:8080/TimeTable/pages/algopage/deleteMutation
public class DeleteMutationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response) throws IOException {
        i_Response.setContentType("text/plain;charset=UTF-8");
        String mutationIndex=i_Request.getParameter(Constants.MUTATION_INDEX);
        String userID= SessionUtils.getUserID(i_Request);
        String managerIndex=SessionUtils.getManagerIndex(i_Request);
        PermUserManager permUserManager= ServletUtils.getPermUserManager(getServletContext());
        User user= permUserManager.getUserByID(userID);
        user.deleteMutationByIndex(Integer.parseInt(managerIndex),Integer.parseInt(mutationIndex));
        i_Response.setStatus(200);
        i_Response.getOutputStream().println("Mutation has been deleted successfully");
    }
}
