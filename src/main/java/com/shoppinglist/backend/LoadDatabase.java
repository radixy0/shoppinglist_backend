package com.shoppinglist.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository){
        return args -> {
            if(repository.findAll().size() == 0) {
                log.info("Database seems empty, Preloading " + repository.save(new Item("a", 2, "b")));
            }
        };
    }

}
