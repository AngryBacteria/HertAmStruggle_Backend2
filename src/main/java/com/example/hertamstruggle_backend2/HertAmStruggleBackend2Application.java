package com.example.hertamstruggle_backend2;

import com.example.hertamstruggle_backend2.model.admin.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HertAmStruggleBackend2Application {

    public static Admin admin;

    public static void main(String[] args) {
        admin = new Admin();
        SpringApplication.run(HertAmStruggleBackend2Application.class, args);
    }

}
