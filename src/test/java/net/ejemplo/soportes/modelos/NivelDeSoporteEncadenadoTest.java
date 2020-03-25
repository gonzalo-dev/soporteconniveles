package net.ejemplo.soportes.modelos;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ejemplo.soporte.modelos.Empleado;
import net.ejemplo.soporte.modelos.NivelDeSoporte;
import net.ejemplo.soporte.modelos.NivelDeSoporteEncadenado;
import net.ejemplo.soporte.modelos.NivelDeSoporteEnum;
import net.ejemplo.soporte.modelos.NoHayCapacidadException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonzalo.trejo
 */
public class NivelDeSoporteEncadenadoTest {
    private final String ERROR_SIN_CAPACIDAD =  "En este momento todos nuestros "
            + "operadores se encuentran ocupados, por favor contactese mas tarde";
    @Test
    public void testAsignarUnEmpleadoBasicoCuandoHayDisponibleBasicos(){
        NivelDeSoporte basico = new NivelDeSoporte(1, NivelDeSoporteEnum
                .OPERADOR);
        NivelDeSoporteEncadenado cadena = new NivelDeSoporteEncadenado(basico);
        try {
            Empleado empleadoBasico = cadena.asignar();
            Supplier<String> messageSupplier = ()-> 
                    "El empleado no es basico (operador)";
            assertEquals((Object)empleadoBasico.getNivelDeSoporteEnum(),
                    (Object)NivelDeSoporteEnum.OPERADOR, 
                    messageSupplier);
        } catch (NoHayCapacidadException ex) {
            fail("Se esperaba que hubiera un operador disponible");
        }
    }
    @Test
    public void testAsignarUnEmpleadoSuperiorCuandoNoHayDisponibleBasicos(){
        NivelDeSoporte basico = new NivelDeSoporte(0, NivelDeSoporteEnum
                .SUPERVISOR);
        NivelDeSoporte superior = new NivelDeSoporte(1, NivelDeSoporteEnum
                .GERENTE);
        NivelDeSoporteEncadenado cadena2 = new NivelDeSoporteEncadenado(superior);
        NivelDeSoporteEncadenado cadena1=  new NivelDeSoporteEncadenado(basico, 
                cadena2);
        
        try {
            Empleado empleadoSuperior = cadena1.asignar();
            Supplier<String> messageSupplier = () -> 
                    "El empleado no es superior (gerente)";
            assertEquals((Object)empleadoSuperior.getNivelDeSoporteEnum(), 
                    (Object)NivelDeSoporteEnum.GERENTE, messageSupplier);
        } catch (NoHayCapacidadException ex) {
            fail("se esperaba un empleado superior (gerente)");
        }
        
    }
    
    @Test
    public void testQueInformeErrorDeCapacidadCuandoSeAgotaronLos3NivelesDeSoportes(){
        NivelDeSoporte operador= new NivelDeSoporte(0, NivelDeSoporteEnum
                .OPERADOR);
        NivelDeSoporte supervisor = new NivelDeSoporte(0, NivelDeSoporteEnum
                .SUPERVISOR);
        NivelDeSoporte gerente = new NivelDeSoporte(0, NivelDeSoporteEnum
                .GERENTE);
        NivelDeSoporteEncadenado nivel3 = new NivelDeSoporteEncadenado(gerente);
        NivelDeSoporteEncadenado nivel2 = new NivelDeSoporteEncadenado(
                supervisor, nivel3);
        NivelDeSoporteEncadenado nivel1 = new NivelDeSoporteEncadenado(operador, 
                nivel2);
        
        try{
            Empleado algunEmpleado = nivel1.asignar();
            fail("No deberia haber asignado un empleado");
        }catch(NoHayCapacidadException ex){
            assertEquals(ERROR_SIN_CAPACIDAD, ex.getMessage());
        }
    }
}
