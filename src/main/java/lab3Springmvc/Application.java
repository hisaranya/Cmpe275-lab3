package lab3Springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;

import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by mallika on 3/12/15.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@RequestMapping("/labtwo")
public class Application {

    public static void main(String[] args) {
        //setupDummyUser();
        SpringApplication.run(Application.class, args);
    }

    public static void setupDummyUser() {
        HomePage hp = new HomePage();
        hp.setFirstname("M");
        hp.setLastname("D");
        hp.setEmail("m@d.com");
        hp.setPassword("S");
        hp.setAddress("SJ");
        hp.setUserid("1");
        hp.setOrganization("SJSU");
        hp.setAboutyourself("Hi!");

        HomePageCollection.getHomePageList().put(hp.userid, hp);
    }

    @RequestMapping(value="/homepage", method= RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute("createuser", new HomePage());
        return "register";
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute HomePage homePage, Model model) throws UnknownHostException {
        System.out.println(homePage.userid+" "+homePage.email);
        homePage = HomePage.createNewHomepageWithNewUserId(homePage);
        model.addAttribute("createuser", homePage);
        String userEmail = homePage.getEmail();
        HomePageCollection.getHomePageList().put(homePage.userid, homePage);
        model.addAttribute("getuserid", homePage.userid);
        model.addAttribute("regsuc", true);
        return "login";
    }

    @RequestMapping(value="/users/login", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
//        model.addAttribute("getuser", new HomePage());
        model.addAttribute("regsuc", false);
        return "login";
    }

    @RequestMapping(value="/users/homepage", method=RequestMethod.GET)
    public String getHomePage(@RequestParam String user_id, @RequestParam String pwd, Model model, @RequestParam(required = false, defaultValue = "off") String brief) throws UnknownHostException {
        if (HomePageCollection.getHomePageList().containsKey(user_id)) {
            HomePage hp = HomePageCollection.getHomePageList().get(user_id);
            if (hp.password.equals(pwd)) {
                JSONObject myObject = pojoToJsonObject(hp);
                model.addAttribute("javahomepage", hp);
                model.addAttribute("jsonhomepage", myObject);
                model.addAttribute("brief", brief);
                model.addAttribute("update", false);
                return "homepage";
            } else {
                return "invalidlogin";
            }
        } else {
            model.addAttribute("inputuserid", user_id);
            return "nosuchuserexists";
        }
    }

    @RequestMapping(value="/users/update", method=RequestMethod.POST)
    public String updateFromHomePage(@Valid @ModelAttribute HomePage homePage, Model model) throws UnknownHostException {
        HomePageCollection.getHomePageList().put(homePage.userid, homePage);
        model.addAttribute("javahomepage", homePage);
        String brief = "off";
        model.addAttribute("brief", brief);
        model.addAttribute("update", true);
        return "homepage";
    }

    @RequestMapping(value="/users/delete", method=RequestMethod.POST)
    public String deleteHomePage(@RequestParam String userid, Model model) throws UnknownHostException {
        System.out.println("/"+userid+"/");
        HomePageCollection.getHomePageList().remove(userid);
        model.addAttribute("delete", true);
        model.addAttribute("createuser", new HomePage());
        return "register";
    }

    private JSONObject pojoToJsonObject(HomePage u) {
        JSONObject myObject = new JSONObject();
        myObject.put("User Id", u.userid);
        myObject.put("Email", u.email);
        myObject.put("First Name", u.firstname);
        myObject.put("Last Name", u.lastname);
        myObject.put("Password", u.password);
        myObject.put("Address", u.address);
        myObject.put("Organization", u.organization);
        myObject.put("About You", u.aboutyourself);
        return myObject;
    }


}
