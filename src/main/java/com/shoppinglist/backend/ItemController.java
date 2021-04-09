package com.shoppinglist.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ItemController {
    private final ItemRepository repository;

    ItemController(ItemRepository repository){
        this.repository=repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/items")
    List<Item> all(){
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem){
        return repository.save(newItem);
    }
}
