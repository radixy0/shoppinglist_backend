package com.shoppinglist.backend;

import org.springframework.data.jpa.repository.JpaRepository;

interface ItemRepository extends JpaRepository<Item, Integer>{

}
