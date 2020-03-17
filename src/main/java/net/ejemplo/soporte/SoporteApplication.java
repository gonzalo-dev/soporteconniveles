package net.ejemplo.soporte;

import net.ejemplo.soporte.modelos.GestorDeSoporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoporteApplication {

    @Autowired
    private GestorDeSoporte gestorDeSoporte;
    
    public static void main(String[] args) {
        SpringApplication.run(SoporteApplication.class, args);
    }

}
