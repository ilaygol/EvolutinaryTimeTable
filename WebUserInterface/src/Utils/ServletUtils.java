package Utils;

import Users.UserManager;

import javax.servlet.ServletContext;

public class ServletUtils {

    private static final Object m_UserManagerLock=new Object();

    public static UserManager getUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_UserManagerLock) {
            if (i_ServletContext.getAttribute("userManager") == null) {
                i_ServletContext.setAttribute("userManager",new UserManager());
            }
        }
        return (UserManager) i_ServletContext.getAttribute("userManager");

    }
}
