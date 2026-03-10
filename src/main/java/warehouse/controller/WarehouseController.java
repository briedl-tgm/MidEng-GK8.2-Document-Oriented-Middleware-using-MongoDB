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

    // --- PRODUCT ENDPOINTS ---

    @PostMapping("/product")
    public ProductData addProduct(@RequestBody ProductData product) {
        return repository.save(product);
    }

    @GetMapping("/product")
    public List<ProductData> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/product/{id}")
    public ProductData getProductById(@PathVariable String id) {
        return repository.findByProductID(id);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable String id) {
        // Löscht alle Einträge dieses Produkts über alle Lager hinweg
        List<ProductData> products = repository.findAll();
        products.stream()
                .filter(p -> p.getProductID().equals(id))
                .forEach(p -> repository.delete(p));
    }

    // --- WAREHOUSE ENDPOINTS ---

    @GetMapping("/warehouse")
    public List<ProductData> getAllWarehouseData() {
        return repository.findAll();
    }

    @GetMapping("/warehouse/{id}")
    public List<ProductData> getWarehouseById(@PathVariable String id) {
        return repository.findByWarehouseID(id);
    }

    @DeleteMapping("/warehouse/{id}")
    public void deleteWarehouse(@PathVariable String id) {
        List<ProductData> data = repository.findByWarehouseID(id);
        repository.deleteAll(data);
    }
}