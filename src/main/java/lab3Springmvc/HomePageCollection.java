package lab3Springmvc;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by mallika on 3/14/15.
 */
public class HomePageCollection {
    private static Hashtable<String, HomePage> homePageList = null;
    private HomePageCollection() {}
    public static Hashtable<String, HomePage> getHomePageList() {
        if (homePageList == null) {
            homePageList = new Hashtable<String, HomePage>();
        }
        return homePageList;
    }

    public static int getListSize() {
        if (homePageList != null) {
            return homePageList.size();
        }
        return 0;
    }
}
