
public class Persona {

    private final String NIF;
    private String nombre;

    public Persona(String nif, String nombre) {
        this.NIF = nif;
        this.nombre = nombre;
    }

    public String getNombre() {

        return nombre;

    }

    public String getNif() {

        return NIF;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*  -Redefine el método toString() para mostrar información de la persona, ejemplo: “Pepe(22333444Z)”  */
    @Override
    public String toString() {
        return String.format("%s (%s)", nombre, NIF);
    }

    public boolean igual(Persona person) {
        boolean retorno = false;
        if (this.NIF.equalsIgnoreCase(person.getNif())) {
            retorno = true;
        }
        return retorno;
    }

    public boolean igual(String nif) {
        boolean igual = false;
        if (this.NIF.equalsIgnoreCase(nif)) {
            igual = true;
        }

        return igual;

    }
}
