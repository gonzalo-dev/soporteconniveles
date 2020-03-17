package net.ejemplo.soporte.config;

import net.ejemplo.soporte.modelos.GestorDeSoporte;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author gonzalo.trejo
 */
@Configuration
public class ConfiguracionSoporte {

    @Bean
    public GestorDeSoporte getGestorDeSoporte(Environment env) {
        
        Integer capacidad1Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.1nivel"));
        Integer capacidad2Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.2nivel"));
        Integer capacidad3Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.3nivel"));

        return new GestorDeSoporte(capacidad1Nivel, capacidad2Nivel, 
                capacidad3Nivel);
    }

}
