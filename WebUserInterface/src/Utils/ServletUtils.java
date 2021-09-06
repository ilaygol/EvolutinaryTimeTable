package Utils;

import Users.UserManager;

import javax.servlet.ServletContext;

public class ServletUtils {
    private static final String USER_MANAGER_ATTRIBUTE_NAME = "userManager";

    private static final Object m_UserManagerLock=new Object();

    public static UserManager getUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_UserManagerLock) {
            if (i_ServletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME) == null) {
                UserManager userManager=new UserManager();
                i_ServletContext.setAttribute(USER_MANAGER_ATTRIBUTE_NAME,userManager);
            }
        }
        return (UserManager) i_ServletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME);

    }
}
