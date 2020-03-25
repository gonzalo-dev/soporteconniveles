package net.ejemplo.soporte.config;

import java.util.logging.Logger;
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

    private static Logger logger = Logger.getLogger(ConfiguracionSoporte
            .class.getName());
    @Bean
    public GestorDeSoporte gestorDeSoporte(Environment env) {
        Integer capacidad1Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.1nivel"));
        Integer capacidad2Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.2nivel"));
        Integer capacidad3Nivel = Integer.valueOf(env.getRequiredProperty(
                "capacidad.3nivel"));
        StringBuilder logMsg = new StringBuilder("Creando Gestor de soporte con ")
                .append(capacidad1Nivel)
                .append(" empleados operadores de nivel 1, luego ")
                .append(capacidad2Nivel)
                .append(" supervisores y finalmente ")
                .append(capacidad3Nivel)
                .append(" gerentes.");
        logger.info(logMsg.toString());

        return new GestorDeSoporte(capacidad1Nivel, capacidad2Nivel, 
                capacidad3Nivel);
    }

}
