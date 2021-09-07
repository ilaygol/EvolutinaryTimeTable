package Utils;

import Constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUsername(HttpServletRequest i_Request)
    {
        String username=null;
        HttpSession session=i_Request.getSession(false);
        if(session!=null) {
            Object sessionAttribute=session.getAttribute(Constants.USERNAME);
            if(sessionAttribute!=null)
                username=sessionAttribute.toString();
        }
        return username;

    }
}
