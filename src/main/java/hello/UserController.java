package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class UserController {

    @RequestMapping("/addUsersMapToJson")
    public @ResponseBody
    HashMap<Integer, User> addUsersMapToJson(){
        User.addUsersMapToJson();
        return (HashMap<Integer, User>)User.users;
    }

    @RequestMapping("/getUsersFromJson")
    public @ResponseBody
    HashMap<Integer, User> getUsersFromJson() throws IOException, IllegalAccessException {
        User.getUsersFromJson();
        return (HashMap<Integer, User>)User.users;
    }

}
