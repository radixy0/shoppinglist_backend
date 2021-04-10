package com.shoppinglist.backend;

public class ItemNotFoundException extends RuntimeException{
    ItemNotFoundException(int id){
        super("Could not find Item "+id);
    }
}
