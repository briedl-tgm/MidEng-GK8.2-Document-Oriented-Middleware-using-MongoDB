package warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import warehouse.model.WarehouseData;
import warehouse.repository.WarehouseRepository;
import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseRepository repository;

    @GetMapping("/warehouse")
    public List<WarehouseData> getAllWarehouses() {
        return repository.findAll();
    }

    @GetMapping("/warehouse/{id}")
    public WarehouseData getWarehouseById(@PathVariable String id) {
        return repository.findByWarehouseID(id);
    }

    @DeleteMapping("/warehouse/{id}")
    public void deleteWarehouse(@PathVariable String id) {
        WarehouseData data = repository.findByWarehouseID(id);
        if (data != null) repository.delete(data);
    }
}