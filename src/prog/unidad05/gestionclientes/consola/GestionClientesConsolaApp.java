package prog.unidad05.gestionclientes.consola;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import prog.unidad05.gestionclientes.core.*;


public class GestionClientesConsolaApp {
    
    private static final int OPCION_SALIR = 0;
    // Opción Listar libros
    private static final int OPCION_LISTAR = 1;
    // Opción Buscar por titulo
    private static final int OPCION_NUEVO_CLIENTE = 2;
    // Opcion Buscar por autor
    private static final int OPCION_ACTUALIZAR_CLIENTE = 3;
    // Opción Buscar por año de publicación
    private static final int OPCION_ELIMINAR_CLIENTE = 4;
    // Opciones minima y maxima (para comprobar los rangos)
    private static final int OPCION_MINIMA = OPCION_SALIR;
    private static final int OPCION_MAXIMA = OPCION_ELIMINAR_CLIENTE;


    public static void main(String[] args) {
        
        crearArchivo("Clientes.dat");
        GestionClientesConsolaApp app = new GestionClientesConsolaApp();
        app .run();
    }
    
    private static void crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se creo el archivo");
        } catch (FileNotFoundException e) {
            
        }
    }
    
    private void run() {
            int opcionElegida = OPCION_SALIR;
            do {
                // Muestra el menú y obtiene una elección
                opcionElegida = mostrarMenu();
                // Según la opción elegida
                switch (opcionElegida) {
                case OPCION_LISTAR:
                    listarClientes("Clientes.dat");
                    break;
                case OPCION_NUEVO_CLIENTE:
                    añadirCliente("Clientes.dat");
                    break;
                case OPCION_ACTUALIZAR_CLIENTE:
                    actualizarCliente("Clientes.dat");
                    break;
                case OPCION_ELIMINAR_CLIENTE:
                    eliminarCliente("Clientes.dat");
                    break;
                case OPCION_SALIR:
                    break;
                default:
                    // No se debe llegar aqui
                    System.out.println("Error. Opción incorrecta.");
                }
            } while (opcionElegida != OPCION_SALIR);
    }

    private int mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        // Inicializamos la opción elegida a un valor invalido
        int opcion = OPCION_MINIMA - 1;
        // Mientras no se elija una opción correcta
        for (;;) {
            // Mostramos el menu
            System.out.println();
            System.out.println("MENU PRINCIPAL");
            System.out.println("--------------");
            System.out.println("1. Listar clientes");
            System.out.println("2. Nuevo cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Salir del programa");
            System.out.print("Elija una opción (" + OPCION_MINIMA + "-" + OPCION_MAXIMA + "): ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                // Si la opción está en rango se devuelve. Si no se muestra error y se da otra
                // vuelta
                if (opcion >= OPCION_MINIMA && opcion <= OPCION_MAXIMA) {
                    return opcion;
                } else {
                    System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
                            + OPCION_MINIMA + " y " + OPCION_MAXIMA);
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
                        + OPCION_MINIMA + " y " + OPCION_MAXIMA);
            }
        }
    }
    
    private void listarClientes(String nombreArchivo) {
        System.out.println("LISTAR CLIENTES");
        System.out.println("----------------");
        File archivo = new File(nombreArchivo);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null) {
                System.out.println(lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void añadirCliente(String nombreArchivo) {
        System.out.println("AÑADIR CLIENTES");
        System.out.println("----------------");
        File archivo = new File(nombreArchivo);
        Scanner sc = new Scanner(System.in);
        String dniClinete = "";
        String nombreCliente = "";
        String apellidoCliente = "";
        int empleadosCliente = 0;
        double facturacionCliente = 0;
        try {
        System.out.println("Introduzca los datos del nuevo cliente");
        System.out.println("NIF (8 números y la letra correspondiente en mayúsculas): ");
        dniClinete = sc.nextLine();
        System.out.print("Nombre (La letra inicial de cada palabra en mayúsculas):");
        nombreCliente = sc.nextLine();
        System.out.print("Apellidos (Igual que Nombre): ");
        apellidoCliente = sc.nextLine();
        System.out.print("Número de empleados (entero mayor que cero): ");
        empleadosCliente = Integer.parseInt(sc.nextLine());
        System.out.print("Facturación (valor real superior a cero): ");
        facturacionCliente = Integer.parseInt(sc.nextLine());
        System.out.print("¿Es nacional de un pais de la UE? (s/n): ");
        String texto = sc.nextLine();
        boolean nacionalidadCliente = true;
        if (texto.contentEquals("s")) {
            nacionalidadCliente = true;
        } else if (texto.contentEquals("n")) {
            nacionalidadCliente = false;
        } else {
            System.out.println("La opción elegida no es válida. Debe ser una s o una n");
        }
        Cliente cliente = new Cliente(dniClinete, apellidoCliente, nombreCliente, empleadosCliente, facturacionCliente, nacionalidadCliente);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(cliente.getNif() + ": " + cliente.getApellidos() + ", " + cliente.getNombre());
            salida.close();
            System.out.println("Cliente añadido");
        } catch (FileNotFoundException e) {
           System.out.println("No se encontro el archivo");
        } catch (IOException e) {
            System.out.println("No se encontro el archivo");
        }    
        }catch (Exception e) {
            System.out.println("Alguno de los datos introducidos es incorrecto");
            
        }
    }  
    
    private void eliminarCliente(String nombreArchivo) {
        System.out.println("ELIMINAR CLIENTE");
        System.out.println("----------------");
        Scanner sc = new Scanner(System.in);
        try {
        System.out.print("Introduzca el NIF del cliente a eliminar: ");
        String dniCliente = sc.nextLine();
        File archivo = new File(nombreArchivo);
        File archivoTemp = new File("temp.dat");
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo));
                PrintWriter salida = new PrintWriter(new FileWriter(archivoTemp))) {
            String lectura = entrada.readLine();
            while (lectura != null) {
                if (!lectura.startsWith(dniCliente)) {
                    salida.println(lectura);
                }
                lectura = entrada.readLine();
            }
            System.out.println("Cliente eliminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivo.delete();
        archivoTemp.renameTo(archivo);
        
    }catch (Exception e) {
        System.out.println("Alguno de los datos introducidos es incorrecto");
        
    }
    }
    
    private void actualizarCliente(String nombreArchivo) {
        System.out.println("ACTUALIZAR CLIENTE");
        System.out.println("----------------");
        Scanner sc = new Scanner(System.in);
        String dniCliente;
        try {
        System.out.print("Introduzca el NIF del cliente a MODIFICAR: ");
        dniCliente = sc.nextLine();
        File archivo = new File(nombreArchivo);
        File archivoTemp = new File("temp.dat");
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo));
                PrintWriter salida = new PrintWriter(new FileWriter(archivoTemp))) {
            String lectura = entrada.readLine();
            while (lectura != null) {
                if (!lectura.startsWith(dniCliente)) {
                    salida.println(lectura);
                }
                lectura = entrada.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivo.delete();
        archivoTemp.renameTo(archivo);
        String nombreCliente = "";
        String apellidoCliente = "";
        int empleadosCliente = 0;
        double facturacionCliente = 0;
        try {
        System.out.println("Introduzca los datos del nuevo cliente");
        System.out.print("Nombre (La letra inicial de cada palabra en mayúsculas):");
        nombreCliente = sc.nextLine();
        System.out.print("Apellidos (Igual que Nombre): ");
        apellidoCliente = sc.nextLine();
        System.out.print("Número de empleados (entero mayor que cero): ");
        empleadosCliente = Integer.parseInt(sc.nextLine());
        System.out.print("Facturación (valor real superior a cero): ");
        facturacionCliente = Integer.parseInt(sc.nextLine());
        System.out.print("¿Es nacional de un pais de la UE? (s/n): ");
        String texto = sc.nextLine();
        boolean nacionalidadCliente = true;
        if (texto.contentEquals("s")) {
            nacionalidadCliente = true;
        } else if (texto.contentEquals("n")) {
            nacionalidadCliente = false;
        } else {
            System.out.println("La opción elegida no es válida. Debe ser una s o una n");
        }
        Cliente cliente = new Cliente(dniCliente, apellidoCliente, nombreCliente, empleadosCliente, facturacionCliente, nacionalidadCliente);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(cliente.getNif() + ": " + cliente.getApellidos() + ", " + cliente.getNombre());
            salida.close();
            System.out.println("Cliente añadido");
        } catch (FileNotFoundException e) {
           System.out.println("No se encontro el archivo");
        } catch (IOException e) {
            System.out.println("No se encontro el archivo");
        }    
        
    }catch (Exception e) {
        System.out.println("Alguno de los datos introducidos es incorrecto");
        
    }
    }catch (Exception e) {
        System.out.println("Alguno de los datos introducidos es incorrecto");
        
    }
    }
}


