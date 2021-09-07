package Utils;

import Constants.Constants;
import Users.UserManager;

import javax.servlet.ServletContext;

public class ServletUtils {
     static final Object m_UserManagerLock=new Object();


    public static UserManager getUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_UserManagerLock) {
            if (i_ServletContext.getAttribute(Constants.USER_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.USER_MANAGER,new UserManager());
            }
        }
        return (UserManager) i_ServletContext.getAttribute(Constants.USER_MANAGER);

    }
}
