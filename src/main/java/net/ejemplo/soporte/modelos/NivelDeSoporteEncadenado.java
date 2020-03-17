package net.ejemplo.soporte.modelos;

/**
 *
 * @author gonzalo.trejo
 */
public class NivelDeSoporteEncadenado implements Asignable{

    private final String ERROR_SIN_CAPACIDAD =  "En este momento todos nuestros "
            + "operadores se encuentran ocupados, por favor contactese mas tarde";

    
    private NivelDeSoporte basico;
    private NivelDeSoporteEncadenado superior;
    
    private NivelDeSoporteEncadenado(){
        
    }
    
    public NivelDeSoporteEncadenado(NivelDeSoporte basico){
        this.basico = basico;
        superior = new NivelDeSoporteEncadenado() {
            @Override
            public Empleado asignar() throws NoHayCapacidadException {
                throw new NoHayCapacidadException(ERROR_SIN_CAPACIDAD);
            }

            @Override
            public void desasignar(Empleado liberado) {
                throw new UnsupportedOperationException("Operacion no soportada."
                        + " Nivel de encadenamiento final"); 
            }
        };
    }
    
    public NivelDeSoporteEncadenado(NivelDeSoporte basico, 
            NivelDeSoporteEncadenado superior){
        this.basico = basico;
        this.superior = superior;
    }

    @Override
    public Empleado asignar() throws NoHayCapacidadException {
        Empleado empleadoAsignado = null;
        try{ 
            empleadoAsignado = basico.asignar();
        }catch(NoHayCapacidadException e){
            empleadoAsignado = superior.asignar();
        }
        return empleadoAsignado;
    }

    @Override
    public void desasignar(Empleado liberado) 
            throws NivelDeSoporteIncorrectoException{
        try{
            basico.desasignar(liberado);
        }catch(NivelDeSoporteIncorrectoException e){
            superior.desasignar(liberado);
        }
    }
    
}
