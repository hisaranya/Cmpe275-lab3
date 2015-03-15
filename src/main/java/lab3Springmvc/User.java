package lab3Springmvc;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by mallika on 3/14/15.
 */
public class User {
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

    public User() {
        this.userid = getUserid();
    }

    public String getUserid() {
        int id = UserCollection.getListSize()+1;
        return "1";
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
