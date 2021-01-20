
import java.util.HashSet;
import java.util.Set;

public class CuentaBancaria {

    private long numCuenta;
    private Persona titular;
    private Set<Persona> autorizados = new HashSet<>();

    public CuentaBancaria(long nCuenta, Persona titular) {
        this.numCuenta = nCuenta;
        this.titular = titular;
    }

    /*  
    Añade método Persona existe(String dni), que revisa la lista de autorizados buscando una persona 
    con el dni pasado como parámetro y si lo encuentra devuelve esa persona 
    y sino devuelve null. Para saber si dos personas son iguales usa el método igual() de la clase Persona
    
    Mejora el método autorizar para que no permita almacenar más de una vez la misma persona. 
    Haz ese control usando el método existe() anterior. 
    Debe devolver true si ha autorizado a la persona, sino devolver false.
    
    Añade método boolean desautorizar(String dni) que debe comprobar que existe ese dni 
    y eliminarlo de la lista de autorizados, en este caso devuelve true, sino devuelve false.    
     */
    public Persona existe(String dni) {
        Persona retorno = null;
        
            for (Persona i : autorizados) {
                if (i.igual(dni)) {
                    retorno = i;
                }
            }

        return retorno;

    }

    public boolean desautorizar(String dni) {
        boolean ret = false;
        String nif = dni;
        
            for (Persona j : autorizados) {
                if (existe(dni).igual(nif)) {
                    nif = j.getNif();
                    autorizados.remove(j);
                    ret = true;
                }
            }

        return ret;
    }

    public boolean autorizar(Persona autorizado) {   
        boolean retorno = false;
     
        if (autorizados.contains(autorizado)==false) {
            autorizados.add(autorizado);
            retorno = true;
        }        
        return retorno;

    }

    /*  Modifica la clase CuentaBancaria: 
-Completa la clase con métodos get/set de cada atributo según sea adecuado.
Una vez creada la cuenta, no se permitirá modificar los atributos pasados en elconstructor.
-Crea un método String verAutorizados(), que devuelva la información de todos los autorizados.  */
    public long getNumCuenta() {
        return numCuenta;
    }

    public Persona getTitular() {
        return titular;
    }
    

    public String verAutorizados() {
        String retorno = "";
        if (autorizados.isEmpty()==false) {
            retorno = "Personas autorizadas: " + autorizados;
        }else{
            retorno = "No hay personas autorizadas.";
        }
        
        return retorno;
    }


    /*  
Incluye un nuevo atributo saldo para gestionar el dinero que almacena la cuenta. 
Al crear una cuenta el saldo debe ser 0. 
Al mostrarlo/recuperarlo deben aparecer solo 2 decimales.
     */
    double saldo = 0;

    public double getSaldo() {
        return saldo;
    }


    /*
Añade método double ingresar(double)  dinero en la cuenta. 
Se debe comprobar que la cantidad pasada como parámetro no es negativa e incrementar el atributo saldo. 
Se debe devolver el nuevo saldo.
     */
    public double ingresar(double saldo) {
        if (saldo > 0) {
            this.saldo += saldo;
        }
        return getSaldo();
    }


    /*
Añade método double sacar(double) dinero de la cuenta. 
Se debe comprobar que existe el suficiente dinero y que la cantidad pasada como parámetro no es negativa 
y disminuir el atributo saldo en esa cantidad. 
Se debe devolver el nuevo saldo. 
     */
    public double sacar(double saldo) {
        if (saldo > 0 && this.saldo >= saldo) {
            this.saldo -= saldo;
        }
        return getSaldo();

    }

    /*
Define un nuevo método informacionCuenta() que liste información de la cuenta con el siguiente formato:

    "nº de cuenta – nombre Titular
    Personas autorizadas: [nombre (dni) , nombre (dni) …..]   //Solo debe aparecer si hay personas autorizadas
    saldo: XX.XXX,00 €"    
     */
    public String informacionCuenta() {

        String retorno = "";

        if (autorizados.isEmpty() == false) {
            retorno = getNumCuenta() + " - " + getTitular() + "\nPersonas autorizadas: " + verAutorizados()
                    + "\nsaldo: " + getSaldo();
        }
        if (autorizados.isEmpty()) {
            retorno = getNumCuenta() + " - " + getTitular() + "\nsaldo: " + getSaldo();
        }
        return retorno;

    }

}
