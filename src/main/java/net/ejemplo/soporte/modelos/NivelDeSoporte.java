package net.ejemplo.soporte.modelos;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author gonzalo.trejo
 */
public class NivelDeSoporte implements Asignable{

    private final Integer capacidadInicial;
    private final NivelDeSoporteEnum nivelDeSoporte;
    private Set<Empleado> disponibles;
    private Set<Empleado> ocupados;
    
    /**
     * 
     * @param capacidad es la cantidad de empleados del mismo nivel asignados 
     * para atender clientes
     * @param nivelDeSoporte
     */
    public NivelDeSoporte(Integer capacidad, NivelDeSoporteEnum nivelDeSoporte){
        this.capacidadInicial = capacidad;
        this.nivelDeSoporte = nivelDeSoporte;
        disponibles = new LinkedHashSet<>(capacidad); //--podria interesarme el orden en que fueron adicionados
        ocupados = new HashSet<>(capacidad); //-- no interesa el orden en absoluto
        
        for (int c = 0; c < capacidad; c++) {
            disponibles.add(new Empleado(nivelDeSoporte));
        }
    }
    
    private boolean hayDisponibles(){
        return !disponibles.isEmpty();
    }
    
    @Override
    public synchronized Empleado asignar() throws NoHayCapacidadException {
        if(hayDisponibles()){
            Empleado empleadoAsignado = disponibles.iterator().next();
            disponibles.remove(empleadoAsignado);
            ocupados.add(empleadoAsignado);
            return empleadoAsignado;
        }
        throw new NoHayCapacidadException("Capacidad inicial de " 
                + capacidadInicial + " se ha cubierto");
    }
    
    @Override
    public synchronized void desasignar(Empleado empleadoLiberado) 
            throws NivelDeSoporteIncorrectoException{
        if(nivelDeSoporte.equals(empleadoLiberado.getNivelDeSoporteEnum())){
            ocupados.remove(empleadoLiberado);
            disponibles.add(empleadoLiberado);
        }else{
            throw new NivelDeSoporteIncorrectoException(
                    "el empleado liberado de cargo " +empleadoLiberado.getCargo()
                    + " NO pertenece al nivel " + nivelDeSoporte.getCargo());
        }
    }

}
