package hello;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
public class ConnectionManagerController {

    @RequestMapping("/addRandomUsersToDB")
    public @ResponseBody
    HashMap<Integer, User> addRandomUsersToDB(@RequestParam(value="amount", defaultValue = "1") String amount){
        return ConnectionManager.addRandomUsersToDB(Integer.parseInt(amount));
    }

    @RequestMapping("/getUsersTableFromDBToMap")
    public @ResponseBody
    HashMap<Integer, User> getUsersTableFromDBToMap(){
        return ConnectionManager.getUsersTableFromDBToMap();
    }
}
