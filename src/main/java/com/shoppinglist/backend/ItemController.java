package com.shoppinglist.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    List<Item> newItem(@RequestBody Item newItem){
        repository.save(newItem);
        return all();
    }

    @GetMapping("/items/{id}")
    Item one(@PathVariable int id){
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PutMapping("/items/{id}")
    Item replaceItem(@RequestBody Item newItem, @PathVariable int id){
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setAmount(newItem.getAmount());
                    item.setUser(newItem.getUser());
                    return repository.save(item);
                }).orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/items/{id}")
    List<Item> deleteItem(@PathVariable int id){
        repository.deleteById(id);
        return all();
    }
}
