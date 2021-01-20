
public class Persona {

    private final String NIF;
    private String nombre;

    public Persona(String nif, String nombre) {
        this.NIF = nif;
        this.nombre = nombre;
    }

   

/*  Completa la clase con métodos get/set de cada atributo. 
    Una vez creada la persona, no se permitirá cambiar su nif.    */ 
    
    public String getNombre() {

        return nombre;

    }

    public String getNif() {

        return NIF;
    }
    
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
/*  -Redefine el método toString() para mostrar información de la persona, ejemplo: “Pepe(22333444Z)”  */
    
    @Override
    public String toString(){
        return String.format("%s (%s)",nombre,NIF);
    }
    
/*  -Añade método boolean igual(Persona person), que devuelve true si el nif de la persona 
    en la que está el método, y el del parámetro person, son iguales.  */    
    
    public boolean igual (Persona person){
        boolean retorno = false;
        if (this.NIF.equalsIgnoreCase(person.getNif())) {
            retorno = true;
        }
        return retorno;
    }
    
/*  -Añade método boolean igual(String nif), 
    que devuelve true si el nif de la persona en la que está el método es igual al parámetro nif.   */    

    public boolean igual (String nif){
        boolean igual = false;
        if (this.NIF.equalsIgnoreCase(nif)) {
            igual = true;
        }
        
        return igual;
        
    }
}
