package prog.unidad05.gestionclientes.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    /**
     * Atributos de la clase clientes
     */
    private String nombre;
    private String apellido;
    private String dni;
    private int empleados;
    private double facturacion;
    private boolean nacionalUe;
    
    /**
     * Metodo privado que valida un DNI
     * @param dni - DNI de la persona. No puede ser null y debe constar de 8 números y una letra. La letra debe ser mayúscula y válida según las normas del DNI
     * @return True si la estructura del dni es correcta
     */
    private boolean validarDni(String dni) {
      String patronDNI = "\\d{8}[a-zA-Z]";
      // Comprobar si el DNI cumple con el patrón
      Pattern pattern = Pattern.compile(patronDNI);
      Matcher matcher = pattern.matcher(dni);
      if (!matcher.matches()) {
        return false;
      }
      // Extraer el número y la letra del DNI
      String numeroString = dni.substring(0, 8);
      int numero = Integer.parseInt(numeroString);
      char letra = calcularLetraDNI(numero);
      // Comparar la letra calculada con la letra proporcionada
      char letraProporcionada = dni.charAt(8);
      return letra == letraProporcionada;
    }
    
  /**
   * Metodo privado que calcula la letra de un DNI
   * @param numeroDNI
   * @return
   */
    private static char calcularLetraDNI(int numeroDNI) {
      String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
      int indiceLetra = numeroDNI % 23;
      return letras.charAt(indiceLetra);
    }
    
    /**
     * Metodo privado que valida los apellidos
     * @param apellido - Apellidos del cliente
     * @return True si los apellidos son válidos
     */
    private boolean validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()){
            return false;
        }
        // Patrón que permite letras mayúsculas y minúsculas, incluyendo la letra "ñ"
        String patron = "^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+( [A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+)*$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(apellido);
        return matcher.matches();
    }

    
    /**
     * Metodo privado que valida los nombres
     * @param nombre - Nombre del cliente
     * @return True si el nombre es válido
     */
    private boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            return false;
          }
        // Patrón que permite letras mayúsculas y minúsculas, incluyendo la letra "ñ"
        String patron = "^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }
    
    public Cliente(String dni, String apellido, String nombre, int empleados, double facturacion, boolean nacionalUe) {
        if (!validarNombre(nombre)) {
          throw new IllegalArgumentException("El nombre no puede ser nulo, vacío o contener solo espacios en blanco");
        }
        if (!validarApellido(apellido)){
          throw new IllegalArgumentException("El apellido no puede ser nulo, vacío o contener solo espacios en blanco");
        }
        if (dni == null || !validarDni(dni)) {
          throw new IllegalArgumentException("El DNI no puede ser nulo, y debe tener 8 numeros seguidos de la letra que le corresponda");
        }
        if (empleados < 0) {
            throw new IllegalArgumentException("Número de empleados. Debe ser superior a cero");
        }
        if (facturacion <= 0) {
              throw new IllegalArgumentException("Facturación anual del cliente. Debe ser superior a cero");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.empleados = empleados;
        this.facturacion = facturacion;
        this.nacionalUe = nacionalUe;
      }
    
    /**
     * Metodo público que obtiene el nombre de pila del cliente
     * @return Nombre de pila del cliente
     */
    public String getNombre() {
      return nombre;
    }

    /**
     * Método público que obtiene los apellidos del cliente
     * @return Apellidos del cliente
     */
    public String getApellidos() {
      return apellido;
    }

    /**
     * Método público que obtiene el DNI del cliente
     * @return DNI del cliente
     */
    public String getNif() {
      return dni;
    }
    
    public int getEmpleados() {
        return empleados;
    }
    
    public double getFacturacion() {
        return facturacion;
    }
    
    public boolean isNacionalUe() {
        return nacionalUe;
    }
}
