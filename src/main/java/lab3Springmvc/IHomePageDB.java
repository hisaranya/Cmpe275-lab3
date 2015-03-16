package lab3Springmvc;

/**
 * Created by mallika on 3/15/15.
 */
public interface IHomePageDB {
    public HomePage createNewHomepage(HomePage homePage) throws EmailPresentException;
    public HomePage getHomepage(String userid, String password) throws InvalidInputException, IdNotFoundException;
    public boolean updateHomepage(HomePage homePage) throws EmailPresentException, IdNotFoundException;
    public boolean deleteHomepage(String userid) throws IdNotFoundException;
}
