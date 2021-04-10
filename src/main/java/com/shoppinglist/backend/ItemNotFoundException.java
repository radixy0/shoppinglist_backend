package com.shoppinglist.backend;

public class ItemNotFoundException extends RuntimeException{
    ItemNotFoundException(long id){
        super("Could not find Item "+id);
    }
}
