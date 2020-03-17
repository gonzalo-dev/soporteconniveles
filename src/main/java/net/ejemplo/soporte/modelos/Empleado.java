package net.ejemplo.soporte.modelos;

import java.util.Objects;

/**
 *
 * @author gonzalo.trejo
 */
public class Empleado {

    private static Long contadorIds = 0L;
    
    private static final String SALUDO_MSJ =
            "Hola, soy el %s en que puedo ayudarle.";
    
    private final Long id;
    private final NivelDeSoporteEnum nivelDeSoporteEnum;
    
    public Empleado(NivelDeSoporteEnum nivelDeSoporte){
        this.id = ++contadorIds;
        this.nivelDeSoporteEnum = nivelDeSoporte;
    }

    public Long getId() {
        return id;
    }

    public String getCargo() {
        return nivelDeSoporteEnum.name();
    }

    public NivelDeSoporteEnum getNivelDeSoporteEnum() {
        return nivelDeSoporteEnum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String saludar(){
        return String.format(SALUDO_MSJ, this.getCargo());
    }
    
}
