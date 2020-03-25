package net.ejemplo.soportes.modelos;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.ejemplo.soporte.modelos.Empleado;
import net.ejemplo.soporte.modelos.NivelDeSoporte;
import net.ejemplo.soporte.modelos.NivelDeSoporteEnum;
import net.ejemplo.soporte.modelos.NivelDeSoporteIncorrectoException;
import net.ejemplo.soporte.modelos.NoHayCapacidadException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonzalo.trejo
 */
public class NivelDeSoporteTest {

    @Test
    public void testQueNoHayaCapacidadCuandoAsignoUnUnicoEmpleado(){
        NivelDeSoporte nivelSupervisor = new NivelDeSoporte(1, 
                NivelDeSoporteEnum.SUPERVISOR);
        try {
            nivelSupervisor.asignar();
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado a un supervisor");
        }
        try {
            nivelSupervisor.asignar();
        } catch (NoHayCapacidadException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testQueHayaCapacidadCuandoAsignoUnEmpleadoConMuchosDisponibles(){
        NivelDeSoporte nivelGerente = new NivelDeSoporte(3, NivelDeSoporteEnum
                .GERENTE);
        try {
            nivelGerente.asignar();
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado un gerente");
        }
        try {
            nivelGerente.asignar();
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asiango un segundo gerente");
        }
    }
    
    @Test
    public void testQueHayaCapacidadCuandoDevuelvoUnicoEmpleado(){
        NivelDeSoporte nivelOperador = new NivelDeSoporte(0, NivelDeSoporteEnum
                .OPERADOR);
        Empleado empleadoLiberado = new Empleado(NivelDeSoporteEnum.OPERADOR);
        try {
            nivelOperador.desasignar(empleadoLiberado);
        } catch (NivelDeSoporteIncorrectoException ex) {
            fail("Deberian ser empleado y nivel de soporte del mismo nivel");
        }
        try {
            nivelOperador.asignar();
        } catch (NoHayCapacidadException ex) {
            fail("Ahora deberia haber capacidad de 1");
        }
    }
    @Test
    public void testErrorDeNivelDeSoporteIncorrectoCuandoDesasignoOtroTipoDeEmpleado(){
        Empleado gerente = new Empleado(NivelDeSoporteEnum.GERENTE);
        NivelDeSoporte nivelOperador = new NivelDeSoporte(0, NivelDeSoporteEnum
                .OPERADOR);
        try{
            nivelOperador.desasignar(gerente);
        }catch(NivelDeSoporteIncorrectoException ex){
            assertTrue(true);
        }
    }
}
