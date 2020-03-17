package net.ejemplo.soporte.modelos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gonzalo.trejo
 */
public class GestorDeSoporte {

    private final String ERROR_SIN_CAPACIDAD =  "En este momento todos nuestros "
            + "operadores se encuentran ocupados, por favor contactese mas tarde";
    
    private final NivelDeSoporte operadores;
    private final NivelDeSoporte supervisores;
    private final NivelDeSoporte gerentes;
    
    private final NivelDeSoporteEncadenado primerNivelDeSoporte;
    
    public GestorDeSoporte(Integer capacidadOperadores, 
            Integer capacidadSupervisores, Integer capacidadGerentes){
        
        operadores = new NivelDeSoporte(capacidadOperadores, NivelDeSoporteEnum
                .OPERADOR);
        supervisores = new NivelDeSoporte(capacidadSupervisores, 
                NivelDeSoporteEnum.SUPERVISOR);
        gerentes = new NivelDeSoporte(capacidadGerentes, NivelDeSoporteEnum
                .GERENTE);
        
        NivelDeSoporteEncadenado tercerNivelDeSoporte = 
                new NivelDeSoporteEncadenado(gerentes);
        NivelDeSoporteEncadenado segundoNivelDeSoporte = 
                new NivelDeSoporteEncadenado(supervisores, tercerNivelDeSoporte);
        primerNivelDeSoporte = new NivelDeSoporteEncadenado(operadores,
                segundoNivelDeSoporte);
    }
    
    public Empleado solicitarSoporte() 
            throws NoHayCapacidadException{
        Empleado empleadoAsignado = primerNivelDeSoporte.asignar();
        
        return empleadoAsignado;
    }
    
    public void concluirSoporte(Empleado unEmpleado){
        try {
            primerNivelDeSoporte.desasignar(unEmpleado);
        } catch (NivelDeSoporteIncorrectoException ex) {
            Logger.getLogger(GestorDeSoporte.class.getName()).log(Level.SEVERE, 
                    "Se repornaron mas empleados que los conocidos", ex);
        }
    }
    
}
