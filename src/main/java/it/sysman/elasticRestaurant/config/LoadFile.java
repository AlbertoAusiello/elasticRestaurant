package it.sysman.elasticRestaurant.config;

import it.sysman.elasticRestaurant.controller.RestaurantController;
import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
    public class LoadFile{

        @Autowired
        private RestaurantServiceIMPL serviceRestaurant;

        @Autowired
        RestaurantController restaurantController;

        @EventListener
        public void onApplicationEvent(ContextRefreshedEvent event) {
            serviceRestaurant.saveFile();

        }
    }

