package lab3Springmvc;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mallika on 3/14/15.
 */
public class HomePage {
    private static int count = 0;
    String userid = "";

    @NotEmpty (message = "Please enter email")
    @Email (message = "Email format is not valid")
    String email="";

    @NotEmpty (message = "Please enter first name")
    String firstname="";

    @NotEmpty (message = "Please specify password")
    String password="";

    @NotEmpty (message = "Please enter last name")
    String lastname="";

    String address = "";
    String organization = "";
    String aboutyourself = "";

    public HomePage() {
    }

    public HomePage(int count) {
        this.userid = "U-" + count;
    }

    public String getUserid() {
        return userid;
    }

    private static int getCount() {
        return count;
    }

    public static HomePage createNewHomepageWithNewUserId(HomePage homePage) {
        count++;
        homePage.setUserid("U-"+count);
        return homePage;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAboutyourself() {
        return aboutyourself;
    }

    public void setAboutyourself(String aboutyourself) {
        this.aboutyourself = aboutyourself;
    }

}
