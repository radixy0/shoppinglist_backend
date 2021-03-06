package com.shoppinglist.backend;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    private String name;
    private int amount;
    private String user;

    Item(){}
    Item(String name, int amount, String user){
        this.name=name;
        this.amount=amount;
        this.user=user;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name, this.amount, this.user);
    }

    @Override
    public String toString(){
        return "Item{" + "name='" + this.name + "', amount=" + this.amount + ", user='" + this.user + "'}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
