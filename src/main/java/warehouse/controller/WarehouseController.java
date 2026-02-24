package warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import warehouse.model.ProductData;
import warehouse.repository.WarehouseRepository;

import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseRepository repository;

    // GK: POST /product - Fügt ein Produkt hinzu
    @PostMapping("/product")
    public ProductData addProduct(@RequestBody ProductData product) {
        return repository.save(product);
    }

    // GK: GET /product - Alle Produkte abrufen
    @GetMapping("/product")
    public List<ProductData> getAllProducts() {
        return repository.findAll();
    }

    // GK: GET /warehouse - Alle Lagerstandorte abrufen
    // (In der GK-Stufe interpretieren wir das als Liste aller Bestände)
    @GetMapping("/warehouse")
    public List<ProductData> getAllWarehouseData() {
        return repository.findAll();
    }
}