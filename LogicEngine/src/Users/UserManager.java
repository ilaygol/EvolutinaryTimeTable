package Users;

import java.util.*;

public class UserManager {
    private  List<User> m_UsersList;

    public UserManager() {
        m_UsersList = new ArrayList<>();
    }

    public synchronized Integer addUser(String i_Username) {
        Integer retID=m_UsersList.size();
        m_UsersList.add(new User(retID,i_Username ));
        return retID;
    }

    public synchronized void removeUser(Integer i_ID) {
        m_UsersList.remove(i_ID.intValue());
    }

    public synchronized List<User> getUsers() {
        return Collections.unmodifiableList(m_UsersList);
    }

    public boolean isUserExists(String i_Username) {

        if(m_UsersList.stream().filter(user->user.getUsername().equals(i_Username)).count()>0)
            return true;
        else
            return false;
    }

    public String getUserNameByID(String i_ID){
        Integer userID=Integer.parseInt(i_ID);
        return m_UsersList.get(userID).getUsername();
    }

}
