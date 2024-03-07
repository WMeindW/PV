package cz.daniellinda.pv.Controller;

import cz.daniellinda.pv.Database.CustomerTypes.CustomerTypes;
import cz.daniellinda.pv.Database.CustomerTypes.CustomerTypesRepo;
import cz.daniellinda.pv.Database.Customers.Customers;
import cz.daniellinda.pv.Database.Customers.CustomersRepo;
import cz.daniellinda.pv.Database.Items.Items;
import cz.daniellinda.pv.Database.Items.ItemsRepo;
import cz.daniellinda.pv.Database.Orders.Orders;
import cz.daniellinda.pv.Database.Orders.OrdersRepo;
import cz.daniellinda.pv.Database.Products.Products;
import cz.daniellinda.pv.Database.Products.ProductsRepo;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private CustomerTypesRepo customersTypesRepo;
    @Autowired
    private ItemsRepo itemsRepo;
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ProductsRepo productsRepo;

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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String phone, @RequestParam String type) throws IOException {
        if (!checkLogin(name, surname)) {
            Customers customer = new Customers();
            customer.setName(name + " " + surname);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setCustomerTypes(checkCustomerTypes(type));
            customersRepo.save(customer);
            return new ResponseEntity<>(Files.readString(Path.of("src/main/resources/static/index.html"), StandardCharsets.UTF_8) + "<script>localStorage.clear();\n" + "localStorage.setItem(\"name\", \"" + name + "\");\n" + "localStorage.setItem(\"surname\",\"" + surname + "\");\n" + "</script>", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bad register", HttpStatus.OK);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> order(@RequestParam String name, @RequestParam String surname, @RequestParam String[] itemName, @RequestParam Integer[] productNumber) {
        Orders order = new Orders();
        if (checkLogin(name, surname)) {
            List<Customers> list = customersRepo.findAll();
            for (Customers customer : list) {
                if (customer.getName().equals(name + " " + surname)) order.setCustomers(customer);
            }
            order.setDate(Date.valueOf(LocalDate.now()));
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<String> products() {
        List<Products> products = productsRepo.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Products product : products) {
            stringBuilder.append(product.getName()).append(",");
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    private boolean checkLogin(String name, String surname) {
        List<Customers> list = customersRepo.findAll();
        for (Customers customer : list) {
            if (customer.getName().equals(name + " " + surname)) return true;
        }
        return false;
    }

    private CustomerTypes checkCustomerTypes(String type) {
        for (CustomerTypes customerType : customersTypesRepo.findAll()) {
            if (customerType.getName().equals(type)) return customerType;
        }
        CustomerTypes types = new CustomerTypes();
        types.setName(type);
        if (type.equals("Firm")) types.setTaxFree(true);
        customersTypesRepo.save(types);
        return types;
    }
}
