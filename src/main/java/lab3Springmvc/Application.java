package lab3Springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        SpringApplication.run(Application.class, args);
    }



    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute("createuser", new User());
        return "register";
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute User user, Model model) throws UnknownHostException {
        model.addAttribute("createuser", user);
        String userEmail = user.getEmail();
        List<User> userList = UserCollection.getUserList();
        for(User loopUser : userList) {
            if (loopUser.email == userEmail) {
                return "userexists";
            }
        }
        userList.add(user);
        model.addAttribute("getuserid", user.userid);
        System.out.println(user.userid+user.password);
        return "registrationSuccess";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("getuser", new User());
        return "login";
    }

    @RequestMapping(value="/users/homepage", method=RequestMethod.GET)
    public String getHomePage(@RequestParam String user_id, @RequestParam String pwd, Model model, @RequestParam String brief) throws UnknownHostException {
        System.out.println("/"+user_id+"/"+pwd+"/"+brief+"/");
        List<User> userList = UserCollection.getUserList();
        int index = Integer.parseInt(user_id);
        User u = userList.get(index-1);
        System.out.println(u);
        if (u.getUserid().equals(user_id)) {
            if (u.getPassword().equals(pwd)) {
                if (brief.equals("off,on")) {
                    JSONObject myObject = new JSONObject();
                    myObject.put("User Id", u.userid);
                    myObject.put("Email", u.email);
                    myObject.put("First Name", u.firstname);
                    myObject.put("Last Name", u.lastname);
                    myObject.put("Password", u.password);
                    myObject.put("Address", u.address);
                    myObject.put("Organization", u.organization);
                    myObject.put("About You", u.aboutyourself);
                    model.addAttribute("getUser", myObject);
                    return "briefhomepage";
                } else if (brief.equals("off")) {
                    model.addAttribute("getUser", u);
                    return "homepage";
                }
            } else {
                return "invalidlogin";
            }
        }
        model.addAttribute("inputuserid", user_id);
        return "nosuchuserexists";
    }


}
