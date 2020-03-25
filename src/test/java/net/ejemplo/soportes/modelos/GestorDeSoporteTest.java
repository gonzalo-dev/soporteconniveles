package net.ejemplo.soportes.modelos;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ejemplo.soporte.modelos.Empleado;
import net.ejemplo.soporte.modelos.GestorDeSoporte;
import net.ejemplo.soporte.modelos.NivelDeSoporteEnum;
import net.ejemplo.soporte.modelos.NoHayCapacidadException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonzalo.trejo
 */
public class GestorDeSoporteTest {

    private static final String ERROR_SIN_CAPACIDAD = "En este momento todos "
            + "nuestros "
            + "operadores se encuentran ocupados, por favor contactese mas tarde";

    private static GestorDeSoporte gestor;

    @BeforeEach
    public void setup() {
        gestor = new GestorDeSoporte(1, 1, 1);
    }

    @AfterAll
    public static void cleanUp() {
        gestor = null;
        System.gc();
    }

    @Test
    public void testObtenerOperadorCuandoEstanDisponibles() {

        try {
            Empleado operador = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = ()
                    -> "El empleado asignado no es operador";
            assertEquals((Object) NivelDeSoporteEnum.OPERADOR, (Object)operador
                    .getNivelDeSoporteEnum(),messageSupplier);
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado un operador");
        }
    }

    @Test
    public void testObtenerSupervisorCuandoNoHayOperadoresDisponibles() {
        try{
            gestor.solicitarSoporte();
        }catch (NoHayCapacidadException ex) {
            fail("Deberia haber asigando un operador");
        }
        try{
            Empleado supervisor = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = ()
                    -> "El empleado asignado no es supervisor";
            assertEquals((Object)NivelDeSoporteEnum.SUPERVISOR, (Object)
                    supervisor.getNivelDeSoporteEnum(), messageSupplier);
        }catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado un supervisor");
        }
    }

    @Test
    public void testObtenerGerenteCuandoNoHayOperadoresNiSupervisoresDisponibles() {
        try{
            gestor.solicitarSoporte();
            gestor.solicitarSoporte();
        }catch (NoHayCapacidadException ex) {
            fail("Deberia haber asigando un operador o supervisor");
        }
        try{
            Empleado supervisor = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = ()
                    -> "El empleado asignado no es gerente";
            assertEquals(NivelDeSoporteEnum.GERENTE,
                    supervisor.getNivelDeSoporteEnum(), messageSupplier);
        }catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado un gerente");
        }
    }

    @Test
    public void testErrorDeTodosLosOperadoresOcupadosCuandoNoHayOperadoresNiSupervisoresNiGerentesDisponibles() {

        try {
            for (int i = 0; i < 3; i++) {
                gestor.solicitarSoporte();
            }
        } catch (NoHayCapacidadException ex) {
            fail("Deberia asignar 1 empleado para cada uno de los 3 niveles de "
                    + "soporte");
        }
        try {
            gestor.solicitarSoporte();
        } catch (NoHayCapacidadException ex) {
            assertEquals(ERROR_SIN_CAPACIDAD, ex.getMessage());
        }
    }

    @Test
    public void testObtenerOperadorCuandoConcluyeElMismoOperador() {
        Empleado unOperador = null;
        try {
            unOperador = gestor.solicitarSoporte();
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado un operador");
        }
        gestor.concluirSoporte(unOperador);
        Empleado segundoOperador;
        try {
            segundoOperador = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = () -> 
                    "Deberia haberse asignado un operador";
            assertEquals(NivelDeSoporteEnum.OPERADOR, 
                    segundoOperador.getNivelDeSoporteEnum(), messageSupplier);
            assertTrue(segundoOperador.equals(unOperador), 
                    "Son operadores diferentes");
        } catch (NoHayCapacidadException ex) {
            fail("Deberia haber asignado el operador que fue devuelto");
        }
    }

    @Test
    public void testObtenerUnSupervisorCuandoConcluyeElMismoSupervisorYNoHayOperadoresDisponibles() {
        Empleado unSupervisor= null;
        try{
            gestor.solicitarSoporte();
            unSupervisor = gestor.solicitarSoporte();
        }catch(NoHayCapacidadException ex){
            fail("Deberia haber asignado 2 empleados");
        }
        gestor.concluirSoporte(unSupervisor);
        Empleado segundoSupervisor = null;
        try{
            segundoSupervisor = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = () -> 
                    "Deberia haberse asignado un supervisor";
            assertEquals(NivelDeSoporteEnum.SUPERVISOR, segundoSupervisor
                    .getNivelDeSoporteEnum(), messageSupplier);
            assertTrue(segundoSupervisor.equals(unSupervisor), 
                    "Son supervisores diferentes");
        }catch(NoHayCapacidadException ex){
            fail("Deberia haber asignado al supervisor que fue devuelto");
        }
    }

    @Test
    public void testObtenerUnGerenteCuandoConcluyeElMismoGerenteYNoHayOperadoresNiSupervisoresDisponibles() {
        Empleado unGerente= null;
        try{
            gestor.solicitarSoporte();
            gestor.solicitarSoporte();
            unGerente = gestor.solicitarSoporte();
        }catch(NoHayCapacidadException ex){
            fail("deberia haber asignado 3 empleados");
        }
        gestor.concluirSoporte(unGerente);
        Empleado segundoGerente = null;
        try{
            segundoGerente = gestor.solicitarSoporte();
            Supplier<String> messageSupplier = () -> 
                    "Deberia haberse asignado un gerente";
            assertEquals(NivelDeSoporteEnum.GERENTE, segundoGerente
                    .getNivelDeSoporteEnum(), messageSupplier);
            
            assertTrue(segundoGerente.equals(unGerente), "Son gerentes distintos");
        }catch(NoHayCapacidadException ex){
            fail("Deberia haber asignado al gerente que fue devuelto");
        }
    }

}
