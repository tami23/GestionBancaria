import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Banco {

    private String nombre;
    private String direccion;

    Map<Long, CuentaBancaria> cuentasBancarias = new HashMap<>();

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void datosInicio(){
        crearCuenta("25655845L", "Antonio Martinez");
        crearCuenta("26512365H", "Marisa Perez");
        crearCuenta("25655845L", "Antonio Martinez");
        crearCuenta("21532412K", "Luis Gonzalez");
        crearCuenta("31523641S", "Angela Lopez");
        crearCuenta("21532412K", "Luis Gonzalez");
        crearCuenta("31523641S", "Angela Lopez");
        
    }

    public CuentaBancaria crearCuenta(String nif, String nombre) {
        double saldo =(int) (Math.random() * 50);
        Persona titular = new Persona(nif, nombre);
        long numCuenta = 1000 + cuentasBancarias.size();
        CuentaBancaria cuenta = new CuentaBancaria(numCuenta, titular);
        cuenta.ingresar(saldo);
        return cuentasBancarias.put(numCuenta, cuenta);
    }

    public int eliminarCuenta(long numCuenta) {
        int retorno = 5;
        if (localizaCC(numCuenta) == null) {
            retorno = -1;
        } else {
            double saldoCuenta = localizaCC(numCuenta).getSaldo();
            if (saldoCuenta == 0) {
                cuentasBancarias.remove(numCuenta);
                retorno = 0;
            }
            if (saldoCuenta > 0) {
                retorno = -2;
            }
        }

        return retorno;
    }

    public Set<CuentaBancaria> listarCuentas(String nif) {
        Set<CuentaBancaria> cuentas = new HashSet<>();

        for (Entry<Long, CuentaBancaria> iCuentas : cuentasBancarias.entrySet()) {
            if (iCuentas.getValue().getTitular().getNif().equalsIgnoreCase(nif)) {
                cuentas.add(iCuentas.getValue());
            }

        }

        return cuentas;

    }

    public CuentaBancaria localizaCC(long nCuenta) {

        if (cuentasBancarias.containsKey(nCuenta)) {
            return cuentasBancarias.get(nCuenta);
        } else {
            return null;
        }
    }

}
