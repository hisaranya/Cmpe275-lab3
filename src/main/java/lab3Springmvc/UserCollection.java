package lab3Springmvc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by mallika on 3/14/15.
 */
public class UserCollection {
    private static List<User> userList = null;
    private UserCollection() {}
    public static List<User> getUserList() {
        if (userList == null) {
            userList = new ArrayList<User>();
        }
        return userList;
    }

    public static int getListSize() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }
}
