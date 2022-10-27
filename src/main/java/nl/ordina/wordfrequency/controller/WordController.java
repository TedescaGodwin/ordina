package nl.ordina.wordfrequency.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api
public class WordController {

    @GetMapping
    public String Greet(){
        return "Hello Word";
    }
}
