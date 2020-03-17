package net.ejemplo.soporte.modelos;

/**
 *
 * @author gonzalo
 */
public interface Asignable {
    public Empleado asignar() throws NoHayCapacidadException;
    public void desasignar(Empleado liberado) 
            throws NivelDeSoporteIncorrectoException;
}
    

