package net.ejemplo.soportes.modelos;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ejemplo.soporte.SoporteApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonzalo.trejo
 */
public class GestorDeSoporteGeneralTest {

    private static net.ejemplo.soporte.modelos.GestorDeSoporte gestorDeSoporte;
    private static Logger logger = Logger.getLogger(SoporteApplication.class
            .getName());
    
    @BeforeEach
    public void crearGestor(){
        gestorDeSoporte = new net.ejemplo.soporte.modelos.GestorDeSoporte(1, 10, 
                3);
    }
    
    @AfterEach
    public void eliminarGestor(){
        gestorDeSoporte = null;
        System.gc();
    }
    
    @Test
    public void testSiempreAsigno(){
        logger.log(Level.INFO, "Inicio testSiempreAsigno");
        for (int i = 0; i < 20; i++) {
            net.ejemplo.soporte.modelos.Empleado empleado;
            try {
                empleado = gestorDeSoporte.solicitarSoporte();
                System.out.println(empleado.saludar());
            } catch (net.ejemplo.soporte.modelos.NoHayCapacidadException ex) {
                System.out.println(ex.getMessage());
            }
        }
        logger.log(Level.INFO, "Fin testSiempreAsigno");
    }

    @Test
    public void testSiempreAsignoTodosYSoloDesasignoAlOperador(){
        logger.log(Level.INFO, "Inicio testSiempreAsignoTodosYSoloDesasignoAlOperador");
        Set<net.ejemplo.soporte.modelos.Empleado> empleadosOcupados = 
                new HashSet<>(20);
        for (int i = 0; i < 20; i++) {
            net.ejemplo.soporte.modelos.Empleado empleado, empleadoDesocupado;
            try {
                empleado = gestorDeSoporte.solicitarSoporte();
                System.out.println(empleado.saludar());
                empleadosOcupados.add(empleado);
                if(i%3 == 0){
                    empleadoDesocupado = empleadosOcupados.iterator().next();
                    System.out.println("Se desasigna: " + empleadoDesocupado
                            .getCargo());
                    gestorDeSoporte.concluirSoporte(empleadoDesocupado);
                }
            } catch (net.ejemplo.soporte.modelos.NoHayCapacidadException ex) {
                System.out.println(ex.getMessage());
            }
        }
        logger.log(Level.INFO, "Fin testSiempreAsignoTodosYSoloDesasignoAlOperador");
    }
    


    
}
