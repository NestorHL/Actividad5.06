package prog.unidad05.gestionclientes.core;

import java.util.List;

public interface ProveedorAlmacenamientoClientes {
    
    void saveAll(Cliente[] clientes) throws ProveedorAlmacenamientoClientesException;
    
    Cliente[] getAll() throws ProveedorAlmacenamientoClientesException;

   
}
