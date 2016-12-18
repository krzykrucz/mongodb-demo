package pl.edu.agh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hector on 17.12.16.
 */

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "home";
    }
}
