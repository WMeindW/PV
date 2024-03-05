package cz.daniellinda.pv.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping(value = "/", produces = "text/html")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("<a href=\"/order\">Objednat</a>", HttpStatus.OK);
    }
}
