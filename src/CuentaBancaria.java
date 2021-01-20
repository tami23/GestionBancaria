
import java.util.HashSet;
import java.util.Set;

public class CuentaBancaria {

    private long numCuenta;
    private Persona titular;
    private Set<Persona> autorizados = new HashSet<>();
    private double saldo = 0;

    public CuentaBancaria(long nCuenta, Persona titular) {
        this.numCuenta = nCuenta;
        this.titular = titular;
    }

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
        boolean retorno = false;

        for (Persona j : autorizados) {
            if (existe(dni).igual(dni)) {
                dni = j.getNif();
                autorizados.remove(j);
                retorno = true;
            }
        }

        return retorno;
    }

    public boolean autorizar(Persona autorizado) {
        boolean retorno = false;
        Persona existe = existe(autorizado.getNif());
        if (existe == null) {
            autorizados.add(autorizado);
            retorno = true;
        }
        return retorno;
    }

    public long getNumCuenta() {
        return numCuenta;
    }

    public Persona getTitular() {
        return titular;
    }

    public String verAutorizados() {
        String retorno = " ";
        if (autorizados.isEmpty() == false) {
            retorno = "Personas autorizadas: " + autorizados;
        } else {
            retorno = "No hay ninguna persona autorizada.";
        }

        return retorno;
    }

    public double getSaldo() {
        return saldo;
    }

    public double ingresar(double saldo) {
        if (saldo > 0) {
            this.saldo += saldo;
        }
        return getSaldo();
    }

    public double sacar(double saldo) {
        if (saldo > 0 && this.saldo >= saldo) {
            this.saldo -= saldo;
        }
        return getSaldo();

    }

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
