package Users;

import java.util.*;

public class UserManager {
    private  List<User> m_UsersList;
    private Integer m_Index;

    public UserManager() {
        m_UsersList = new ArrayList<>();
        m_Index=0;
    }

    public synchronized Integer addUser(String i_Username) {
        Integer retID=m_Index;
        m_Index++;
        m_UsersList.add(new User(retID,i_Username ));
        return retID;
    }

    public synchronized void removeUser(Integer i_ID) {
        User deleteUser=null;
        for(User user:m_UsersList)
        {
            if(user.getID()==i_ID) {
                deleteUser = user;
                break;
            }
        }
        m_UsersList.remove(deleteUser);
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
        User wantedUser=null;
        for(User user:m_UsersList)
        {
            if(user.getID()==Integer.parseInt(i_ID)) {
                wantedUser = user;
                break;
            }
        }
        return wantedUser.getUsername();
    }

}
