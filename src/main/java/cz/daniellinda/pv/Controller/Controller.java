package cz.daniellinda.pv.Controller;

import cz.daniellinda.pv.Database.Customers.Customers;
import cz.daniellinda.pv.Database.Customers.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CustomersRepo customersRepo;

    @GetMapping("/")
    public ResponseEntity<String> index() throws IOException {
        return new ResponseEntity<>(Files.readString(Path.of("src/main/resources/static/login.html"), StandardCharsets.UTF_8), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam String surname) throws IOException {
        if (checkLogin(name, surname)) {
            return new ResponseEntity<>(Files.readString(Path.of("src/main/resources/static/index.html"), StandardCharsets.UTF_8) + "<script>localStorage.clear();\n" + "localStorage.setItem(\"name\", \"" + name + "\");\n" + "localStorage.setItem(\"surname\",\"" + surname + "\");\n" + "</script>", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bad login", HttpStatus.OK);
        }

    }

    private boolean checkLogin(String name, String surname) {
        List<Customers> list = customersRepo.findAll();
        for (Customers customer : list) {
            if (customer.getName().equals(name + " " + surname)) return true;
        }
        return false;
    }
}
