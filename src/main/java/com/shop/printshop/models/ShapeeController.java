package com.shop.printshop.models;

import com.shop.printshop.OrderNotFoundException;
import com.shop.printshop.repository.ShapeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class ShapeeController {

    private final ShapeRepository repository;

    public ShapeeController(ShapeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/letters")
    List<Shapee> all() {
        return (List<Shapee>) repository.findAll();
    }

    @GetMapping("/letters/{id}")
    Shapee one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PostMapping("/letters")
    Shapee newPost(@RequestBody Shapee newOrd) {
        return repository.save(newOrd);
    }

    @PutMapping("/letters/{id}")
    Shapee put(@RequestBody Shapee replaceOrder, @PathVariable Long id) {
        return repository.findById(id).map(order -> {
            order = replaceOrder;
            order.setId(id);
            return repository.save(order);
        }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @DeleteMapping("/letters/{id}")
    void del(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
