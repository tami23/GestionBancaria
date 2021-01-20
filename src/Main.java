//  FALTA transferir saldo para eliminar

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static DecimalFormat formatea = new DecimalFormat("###,###.##");
    static Banco banco1 = new Banco();

    public static void main(String[] args) {
        banco1.setNombre("ING Mislata");
        banco1.setDireccion("Calle Mayor 25");
        banco1.datosInicio();

        do {
            String respuesta = menu();
            switch (respuesta) {
                case "1": //CREAR CUENTA
                    crearCuenta();
                    break;
                case "2": //ELIMINAR CUENTA
                    eliminarCuenta();
                    break;
                case "3": //LISTAR CUENTAS     
                    listarCuentas();
                    break;
                case "4": //OPERAR
                    operativa();
                    break;
                case "0": //SALIR
                    System.out.println("Gracias por usar nuestra aplicacion");
                    return;
                default:
                    System.out.println("Debe seleccionar un numero correcto");
            }

        } while (true);

    }

    public static void crearCuenta() {
        System.out.println("Introduce el nif del titular: ");
        String nif = sc.nextLine();
        System.out.println("Introduce el nombre del titular: ");
        String nombre = sc.nextLine();
        CuentaBancaria cuenta = banco1.crearCuenta(nif, nombre);
        System.out.println("Numero de cuenta: " + cuenta.getNumCuenta() + ". Saldo de regalo: " + formatea.format(cuenta.getSaldo()) + "€");
    }
    
    public static void eliminarCuenta() {
        System.out.println("Numero de la cuenta a eliminar: ");
        long numCuenta = Long.parseLong(sc.nextLine());
        if (banco1.eliminarCuenta(numCuenta) == 0) {
            System.out.println("La cuenta se ha eliminado correctamente.");
        } else {
            if (banco1.eliminarCuenta(numCuenta) == -1) {
                System.out.println("La cuenta no existe.");
            }
            if (banco1.eliminarCuenta(numCuenta) == -2) {
                System.out.println("La cuenta tiene saldo. Transfieralo a otra cuenta para eliminarla.");
            }
        }
    }

    public static void listarCuentas() {
        System.out.println("Introduzca el nif del titular: ");
        String nif = sc.nextLine();
        Set<CuentaBancaria> lista;

        lista = banco1.listarCuentas(nif);
        if (lista.isEmpty()) {
            System.out.println("No existe ninguna cuenta con el nif " + nif);
        } else {
            for (CuentaBancaria cuenta : banco1.listarCuentas(nif)) {
                System.out.println("Titular: " + cuenta.getTitular() + " Numero de cuenta: " + cuenta.getNumCuenta() + " Saldo: " + formatea.format(cuenta.getSaldo()) + "€");
            }
        }
    }

    public static void operativa() {
        System.out.println("Introduzca el numero de cuenta: ");
        long nCuenta = Long.parseLong(sc.nextLine());
        CuentaBancaria cuenta = banco1.localizaCC(nCuenta);

        do {
            String respuesta = menuOperativa();
            switch (respuesta) {
                case "1": //INGRESAR DINERO
                    ingresar(cuenta);
                    break;
                case "2": //SACAR DINERO
                    sacar(cuenta);
                    break;
                case "3": //INFORMACION DE LA CUENTA       
                    informacion(cuenta);
                    break;
                case "4": //AUTORIZAR
                    autorizar(cuenta);
                    break;
                case "5": //DESAUTORIZAR
                    desautorizar(cuenta);
                    break;
                case "0": //VOLVER
                    return;
                default:
                    System.out.println("Debe seleccionar un numero correcto");
            }

        } while (true);

    }

    public static String menu() {
        String respuesta;
        System.out.println("ING MISLATA -- GESTION BANCARIA");
        System.out.println("1- Crear cuenta.");
        System.out.println("2- Eliminar cuenta.");
        System.out.println("3- Listar cuentas.");
        System.out.println("4- Operar sobre una cuenta.");
        System.out.println("0- Salir\n");
        respuesta = sc.nextLine();

        return respuesta;
    }

    public static String menuOperativa() {
        String respuesta;
        System.out.println("ING MISLATA -- GESTION DE CUENTA BANCARIA");
        System.out.println("1- Ingresar dinero.");
        System.out.println("2- Sacar dinero.");
        System.out.println("3- Información de la cuenta.");
        System.out.println("4- Autorizar a una persona.");
        System.out.println("5- Desautorizar a una persona.");
        System.out.println("0- Volver\n");
        respuesta = sc.nextLine();

        return respuesta;
    }

    public static void autorizar(CuentaBancaria cuenta) {
        System.out.println("Nif de la persona que desea autorizar: ");
        String dni = sc.nextLine();
        System.out.println("Nombre de la persona que desea autorizar: ");
        String nombre = sc.nextLine();
        Persona autorizado = new Persona(dni, nombre);
        if (cuenta.autorizar(autorizado)) {
            System.out.println("Se ha autorizado a: " + autorizado.getNombre());
        } else {
            System.out.println("La persona ya está autorizada.");
        }

    }

    public static void desautorizar(CuentaBancaria cuenta) {
        System.out.println("Nif de la persona que desea desautorizar: ");
        String dni = sc.nextLine();
        if (cuenta.desautorizar(dni) == true) {
            System.out.println("Se ha desautorizado correctamente.");
        } else {
            System.out.println("La persona no está autorizada.");
        }
    }

    public static void ingresar(CuentaBancaria cuenta) {
        System.out.println("INGRESAR DINERO");
        System.out.println("¿Cuánto dinero desea ingresar?");
        double saldo = Double.parseDouble(sc.nextLine());
        if (saldo > 0) {
            System.out.println("Se ha ingresado: " + formatea.format(saldo) + "€. Su saldo total es de: " + formatea.format(cuenta.ingresar(saldo)) + "€\n");
        } else {
            System.out.println("Introduce una cantidad correcta.\n");
        }

    }

    public static void sacar(CuentaBancaria cuenta) {
        System.out.println("SACAR DINERO");
        System.out.println("¿Cuánto dinero desea sacar?");
        double saldo = Double.parseDouble(sc.nextLine());
        if (saldo > 0 && cuenta.getSaldo() >= saldo) {
            System.out.println("Se ha sacado: " + formatea.format(saldo) + "€. Su saldo total es de: " + formatea.format(cuenta.sacar(saldo)) + "€\n");
        } else {
            System.out.println("No hay suficiente dinero en la cuenta para sacar " + formatea.format(saldo) + "€.\n");
        }
    }

    public static void informacion(CuentaBancaria cuenta) {
        System.out.println("VER INFORMACION\nAquí tiene la información solicitada");
        System.out.println("Nº cuenta: " + cuenta.getNumCuenta() + " - " + cuenta.getTitular());
        System.out.println(cuenta.verAutorizados());
        System.out.println("Saldo: " + formatea.format(cuenta.getSaldo()) + "€\n");

    }

}
