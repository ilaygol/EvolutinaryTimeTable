package Utils;

import Constants.Constants;
import Users.TimeTableHostManager;
import Users.UserManager;

import javax.servlet.ServletContext;
import java.sql.Time;

public class ServletUtils {
     private static final Object m_UserManagerLock=new Object();
     private static final Object m_TimeTableHostManagerLock=new Object();



    public static UserManager getUserManager(ServletContext i_ServletContext)
    {
        synchronized (m_UserManagerLock) {
            if (i_ServletContext.getAttribute(Constants.USER_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.USER_MANAGER,new UserManager());
            }
        }
        return (UserManager) i_ServletContext.getAttribute(Constants.USER_MANAGER);

    }


    public static TimeTableHostManager getTimeTableInstances(ServletContext i_ServletContext)
    {
        synchronized (m_TimeTableHostManagerLock) {
            if (i_ServletContext.getAttribute(Constants.TIMETABLE_HOST_MANAGER) == null) {
                i_ServletContext.setAttribute(Constants.TIMETABLE_HOST_MANAGER,new TimeTableHostManager());
            }
        }
        return (TimeTableHostManager) i_ServletContext.getAttribute(Constants.TIMETABLE_HOST_MANAGER);

    }
}
