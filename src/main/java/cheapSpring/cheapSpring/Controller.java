package cheapSpring.cheapSpring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


}

