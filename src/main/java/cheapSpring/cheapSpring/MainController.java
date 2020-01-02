package cheapSpring.cheapSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/hello")
    public String sendForma() {

        return "search";
    }
    @GetMapping("/search")
    public String sendForm() {

        return "ms.w aparat";
    }

}
