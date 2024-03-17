package prog.unidad05.gestionclientes.impl;

import java.io.*;
import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;

public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {
    private String nombreArchivo;

    public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
        if (rutaFichero == null) {
            throw new NullPointerException("La ruta del archivo no puede ser nula.");
        }
        this.nombreArchivo = rutaFichero;
    }

    @Override
    public Cliente[] getAll() {
        Cliente[] clientes = new Cliente[0];
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            clientes = (Cliente[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de clientes no existe.");
        } catch (IOException | ClassNotFoundException e) {
        }
        return clientes;
    }

    @Override
    public void saveAll(Cliente[] clientes) throws ProveedorAlmacenamientoClientesException{
        if (clientes == null) {
            throw new NullPointerException("El array de clientes no puede ser nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(clientes);
            System.out.println("Datos de clientes guardados en el archivo " + nombreArchivo);
        } catch (IOException e) {
        }
    }
}
