package lab3Springmvc;

import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by mallika on 3/15/15.
 */
public class HomePageDB implements IHomePageDB {
    HashSet<String> emailList = HomePageCollection.getEmailList();
    Hashtable<String, HomePage> homePageList = HomePageCollection.getHomePageList();
    @Override
    public HomePage createNewHomepage(HomePage homePage) throws EmailPresentException {
        if (emailList.contains(homePage.email)) {
            throw new EmailPresentException();
        }
        homePage = HomePage.createNewHomepageWithNewUserId(homePage);
        updateCollections(homePage);
        return homePage;
    }

    @Override
    public HomePage getHomepage(String userid, String password) throws InvalidInputException, IdNotFoundException {
        if (!isPresentUid(userid)) {
            throw new IdNotFoundException();
        }
        HomePage homePage = homePageList.get(userid);
        System.out.println("/"+homePage.userid+"/"+homePage.password+"/");
        if (!homePage.password.equals(password)) {
            throw new InvalidInputException();
        }
        return homePage;
    }

    @Override
    public boolean updateHomepage(HomePage homePage) throws EmailPresentException, IdNotFoundException {
        if (!isPresentUid(homePage.userid)) {
            throw new IdNotFoundException();
        }
        HomePage hp = homePageList.get(homePage.userid);
        if (!hp.email.equals(homePage.email)) {
            if (emailList.contains(homePage.email)) {
                throw new EmailPresentException();
            }
            emailList.remove(hp.email);
        }
        updateCollections(homePage);
        return true;
    }

    @Override
    public boolean deleteHomepage(String userid) throws IdNotFoundException {
        if (!isPresentUid(userid)) {
            throw new IdNotFoundException();
        }
        String email = homePageList.get(userid).email;
        homePageList.remove(userid);
        emailList.remove(email);
        return true;
    }



    private boolean isPresentUid(String userid) {
        if (homePageList.containsKey(userid)) {
            return true;
        }
        return false;
    }

    private void updateCollections(HomePage homePage) {
        homePageList.put(homePage.userid, homePage);
        emailList.add(homePage.email);
    }
}
