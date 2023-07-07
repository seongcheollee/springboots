package gpsdraw.springboots.controller;

import gpsdraw.springboots.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RequestMapping("/")
public class MainController {

    @GetMapping("/root")
    public String root() {
        return "root";
    }
    @GetMapping("/get/{var}")
    public String getVariable1(@PathVariable String var){
        return var;
    }


    @GetMapping("/request1")
    public String getVariable2(
            @RequestParam String name,
            @RequestParam String email
    ){
        return name +" " + email;
    }
    // 어떤 값이 들어올 지 모를 때


    @GetMapping("/request2")
    public String getVariable3(
            @RequestParam Map<String ,String> param
            ){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map ->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }


    @GetMapping("/request3")
    public String getVariable4(User user){
        return user.toString();
    }

    // Post -----------------------------------------------------------------------------------------------

    @PostMapping("/post")
    public String postUser(@RequestBody User user){
        return user.toString();
    }



}
