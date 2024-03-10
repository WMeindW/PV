package cz.daniellinda.pv.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.apache.tomcat.util.json.JSONParser;
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
import java.util.LinkedList;
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
    public ResponseEntity<String> order(@RequestParam String name, @RequestParam String surname, @RequestParam String[] products, @RequestParam Integer[] productNumber) {
        Orders order = new Orders();
        if (checkLogin(name, surname)) {
            List<Customers> list = customersRepo.findAll();
            for (Customers customer : list) {
                if (customer.getName().equals(name + " " + surname)) order.setCustomers(customer);
            }
            order.setDate(Date.valueOf(LocalDate.now()));
            checkItems(products, productNumber, order);
            return new ResponseEntity<>("Ordered", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
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

    @GetMapping("/orders")
    public ResponseEntity<String> orders(@RequestParam String name, @RequestParam String surname) {
        StringBuilder builder = new StringBuilder();
        List<Orders> list = ordersRepo.findAll();
        for (Orders order : list) {
            if (order.getCustomers().getName().equals(name + " " + surname))
                builder.append(order.getDate().toString()).append(",");
        }
        return new ResponseEntity<>(builder.toString(), HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<String> order(@RequestParam String name, @RequestParam String surname, @RequestParam String date) throws JsonProcessingException {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        List<Orders> list = ordersRepo.findAll();
        for (Orders order : list) {
            if (order.getCustomers().getName().equals(name + " " + surname) && order.getDate().toString().equals(date))
                builder.append(mapper.writeValueAsString(order.getItems()));
        }


        return new ResponseEntity<>(builder.toString(), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String name, @RequestParam String surname, @RequestParam String order) {
        if (checkLogin(name, surname)) {
            List<Orders> list = ordersRepo.findAll();
            List<Items> list1 = itemsRepo.findAll();
            for (Orders item : list) {
                if (item.getCustomers().getName().equals(name + " " + surname) && item.getDate().toString().equals(order)) {
                    for (Items item1 : list1) {
                        if (item1.getOrders().equals(item))
                            itemsRepo.delete(item1);
                    }
                    ordersRepo.delete(item);
                }


            }
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
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

    private void checkItems(String[] products, Integer[] productNumber, Orders order) {
        List<Products> productsData = productsRepo.findAll();
        List<Items> items = new LinkedList<>();
        for (int i = 0; i < products.length; i++) {
            Items item = new Items();
            item.setOrders(order);
            item.setNumberOf(Long.valueOf(productNumber[i]));
            for (Products p : productsData) {
                if (p.getName().equals(products[i])) {
                    item.setProducts(p);
                }
            }
            items.add(item);
        }
        ordersRepo.save(order);
        itemsRepo.saveAll(items);
    }
}
