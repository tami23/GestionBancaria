
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Banco {
    
    private String nombre;
    private String direccion;
    
    Map<Long, CuentaBancaria> cuentasBancarias = new HashMap<>();
    
// Solamente deberán existir los métodos get/set para los atributos nombre y dirección del banco.     
    
    public String getNombre () {
        return nombre;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
    public void setDireccion (String direccion){
        this.direccion = direccion;
    }
    
/*  CuentaBancaria crearCuenta(String nif, String nombre), 
    crea y almacena una nueva cuenta bancaria con los datos proporcionados. 
    El número de cuenta será asignado consecutivamente empezando en 1000. 
    Regalaremos saldo a nuestros clientes, la cantidad será de forma aleatoria con un máximo de 50€.   */    
    
    public CuentaBancaria crearCuenta (String nif, String nombre){
        double saldo =(int) (Math.random()*50);
        Persona titular = new Persona(nif, nombre);
        long numCuenta = 1000 + cuentasBancarias.size();
        CuentaBancaria cuenta = new CuentaBancaria(numCuenta, titular);
        cuenta.ingresar(saldo);
        return cuentasBancarias.put(numCuenta, cuenta);
    }
    
/*    int  eliminarCuenta(long numCuenta), 
    devuelve 0 si la cuenta ha sido eliminada, -1 si no existe y -2 si tiene saldo.  */    
        
    public int eliminarCuenta (long numCuenta){
        int ret=1;
        if (localizaCC(numCuenta).getSaldo() == 0.00) {
            cuentasBancarias.remove(numCuenta);
            ret= 0;
        }else{
            if (localizaCC(numCuenta)== null) {
            ret= -1;
            }
            if (localizaCC(numCuenta).getSaldo() > 0.00) {
            ret= -2;
            }    
        }
                 
        return ret;
        }
 
/*   Set<CuentaBancaria> listarCuentas(String nif) 
genera un conjunto de cuentas bancarias cuyo titular tiene como nif el  pasado por parámetro.    */  
    
    public Set<CuentaBancaria> listarCuentas(String nif){
        Set<CuentaBancaria> cuentas = new HashSet<>();
        
        for (Entry<Long, CuentaBancaria> iCuentas : cuentasBancarias.entrySet()){
            if (iCuentas.getValue().getTitular().getNif().equalsIgnoreCase(nif)) {
                cuentas.add(iCuentas.getValue());
            }
            
        }
        
        return cuentas;
       
    }
    
/*       CuentaBancaria  localizaCC(long ncuenta),   
deberá localizar el objeto CuentaBancaria a través del parámetro ncuenta pasado, 
sino devolverá null.  */    
    
    public CuentaBancaria localizaCC(long nCuenta){
        
        if (cuentasBancarias.containsKey(nCuenta)) {
            return cuentasBancarias.get(nCuenta);
        }else{
            return null;
        }
    }
        
    }
