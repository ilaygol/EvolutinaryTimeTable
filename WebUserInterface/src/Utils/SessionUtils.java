package Utils;

import Constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUserID(HttpServletRequest i_Request)
    {
        String userID=null;
        HttpSession session=i_Request.getSession(false);
        if(session!=null) {
            Object sessionAttribute=session.getAttribute(Constants.USER_ID);
            if(sessionAttribute!=null)
                userID=sessionAttribute.toString();
        }
        return userID;

    }

    public static void removeSession(HttpServletRequest i_Request)
    {
        //invalidate is a function that remove session
        i_Request.getSession().invalidate();
    }
}
