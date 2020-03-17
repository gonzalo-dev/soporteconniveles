package net.ejemplo.soporte.modelos;

/**
 *
 * @author gonzalo
 */
public enum NivelDeSoporteEnum {
    OPERADOR("operador nivel 1"), SUPERVISOR("supervisor"), GERENTE("gerente");
    
    private String cargo;
    
    private NivelDeSoporteEnum(String cargo){
        this.cargo = cargo;
    }
    
    public String getCargo(){
        return cargo;
    }
    
}
