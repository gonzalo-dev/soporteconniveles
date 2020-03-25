package net.ejemplo.soportes.modelos;

import java.util.logging.Logger;
import net.ejemplo.soporte.SoporteApplication;
import net.ejemplo.soporte.modelos.Empleado;
import net.ejemplo.soporte.modelos.NivelDeSoporteEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * @author gonzalo.trejo
 */
public class EmpleadoTest {

    private static Logger logger = Logger.getLogger(EmpleadoTest.class
            .getName());
    
    private final String SALUDO_OPERADOR = "Hola, soy el operador nivel 1 en que puedo ayudarle.";
    private final String SALUDO_SUPERVISOR = "Hola, soy el supervisor en que puedo ayudarle.";
    private final String SALUDO_GERENTE = "Hola, soy el gerente en que puedo ayudarle.";
    
    @Test
    public void testQueSaludeOperadorAlCrearEmpleadoOperador(){
        logger.info("testQueSaludeOperadorAlCrearEmpleadoOperador");
        Empleado operador = new Empleado(NivelDeSoporteEnum.OPERADOR);
        Assertions.assertEquals(SALUDO_OPERADOR, operador.saludar());
    }
    
    @Test
    public void testQueSaludeSupervisorAlCrearEmpleadoSupervisor(){
        logger.info("testQueSaludeSupervisorAlCrearEmpleadoSupervisor");
        Empleado supervisor = new Empleado(NivelDeSoporteEnum.SUPERVISOR);
        Assertions.assertEquals(SALUDO_SUPERVISOR, supervisor.saludar());   
    }
    
    @Test
    public void testQuesaludeGerenteAlCrearEmpleadoGerente(){
        logger.info("testQuesaludeGerenteAlCrearEmpleadoGerente");
        Empleado gerente = new Empleado(NivelDeSoporteEnum.GERENTE);
        Assertions.assertEquals(SALUDO_GERENTE, gerente.saludar());
    }
}
