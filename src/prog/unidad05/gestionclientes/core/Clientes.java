package prog.unidad05.gestionclientes.core;

import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private static ProveedorAlmacenamientoClientes proveedorAlmacenamiento;

    public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamiento) {
        if (proveedorAlmacenamiento == null) {
            throw new NullPointerException("proveedorAlmacenamiento cannot be null");
        }
        
        this.proveedorAlmacenamiento = proveedorAlmacenamiento;
    }

    public void addCliente(Cliente cliente) throws ClientesException, ProveedorAlmacenamientoClientesException {
        if (cliente == null) {
            throw new NullPointerException("cliente cannot be null");
        }

        Cliente[] clientes = proveedorAlmacenamiento.getAll();
        for (Cliente c : clientes) {
            if (c != null && c.getNif().equals(cliente.getNif())) {
                throw new ClientesException();
            }
        }

        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente);
        proveedorAlmacenamiento.saveAll(clienteList.toArray(new Cliente[0]));
    }

    public void updateCliente(Cliente cliente) throws ClientesException, ProveedorAlmacenamientoClientesException {
        if (cliente == null) {
            throw new NullPointerException("cliente cannot be null");
        }

        Cliente[] clientes = proveedorAlmacenamiento.getAll();
        boolean found = false;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getNif().equals(cliente.getNif())) {
                clientes[i] = cliente;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ClientesException();
        }
        proveedorAlmacenamiento.saveAll(clientes);
    }


    public static Cliente getByNif(String nif) throws ProveedorAlmacenamientoClientesException {
        Cliente[] clientes = proveedorAlmacenamiento.getAll();
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getNif().equals(nif)) {
                return cliente;
            }
        }
        return null;
    }

    public void visita(VisitadorClientes visitador) throws ProveedorAlmacenamientoClientesException {
        if (visitador == null) {
            throw new NullPointerException("visitador cannot be null");
        }

        Cliente[] clientes = proveedorAlmacenamiento.getAll();
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                visitador.visita(cliente);
            }
        }
    }

    public void removeCliente(String nif) throws ClientesException, ProveedorAlmacenamientoClientesException {
        if (nif == null) {
            throw new NullPointerException("nif cannot be null");
        }

        Cliente[] clientes = proveedorAlmacenamiento.getAll();
        boolean found = false;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getNif().equals(nif)) {
                clientes[i] = null;
                found = true;
            }
        }
        if (!found) {
            throw new ClientesException();
        }

        List<Cliente> clienteList = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c != null) {
                clienteList.add(c);
            }
        }
        proveedorAlmacenamiento.saveAll(clienteList.toArray(new Cliente[0]));
    }
}
