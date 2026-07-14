package com.jumpstart.foodorderingsystem.config;

import com.jumpstart.foodorderingsystem.entity.Role;
import com.jumpstart.foodorderingsystem.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {

        return args -> {

            if (roleRepository.findByName("ADMIN").isEmpty()) {

                roleRepository.save(
                        Role.builder()
                                .name("ADMIN")
                                .build()
                );

            }

            if (roleRepository.findByName("CUSTOMER").isEmpty()) {

                roleRepository.save(
                        Role.builder()
                                .name("CUSTOMER")
                                .build()
                );

            }

        };

    }

}