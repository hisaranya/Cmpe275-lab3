package lab3Springmvc;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by mallika on 3/14/15.
 */
public class HomePageCollection {
    private static Hashtable<String, HomePage> homePageList = null;
    private static HashSet<String> emailList = null;
    private HomePageCollection() {}

    public static Hashtable<String, HomePage> getHomePageList() {
        if (homePageList == null) {
            homePageList = new Hashtable<String, HomePage>();
        }
        return homePageList;
    }

    public static HashSet<String> getEmailList() {
        if (emailList == null) {
            emailList = new HashSet<String>();
        }
        return emailList;
    }


}
