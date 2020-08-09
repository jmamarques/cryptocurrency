package cod.currency.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * JMA - 8/8/2020 17:33
 **/
@Controller
public class EmailController {
    @GetMapping("/hello/{page}")
    public String hello(@PathVariable String page, Model model) {
        model.addAttribute("name", "John");
        return page;
    }
}
