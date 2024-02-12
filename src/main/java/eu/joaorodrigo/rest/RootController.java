package eu.joaorodrigo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public String apiRoot() {
        return "Hello, world!";
    }
}
