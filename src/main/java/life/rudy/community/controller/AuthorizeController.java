package life.rudy.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lining
 */
@Controller
public class AuthorizeController {

    @GetMapping("/callback")
    public String index(@RequestParam(name="code") String code,
                        @RequestParam(name="state") String state) {
        return "index";
    }
}
