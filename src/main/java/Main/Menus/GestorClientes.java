package Main.Menus;

import Main.Cliente;
import java.util.ArrayList;

public class GestorClientes {
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    GestorClientes(){
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void Editar(int index, Cliente cliente){
        listaClientes.set(index, cliente);
    }

    public void Agregar(Cliente cliente){
        listaClientes.add(cliente);
    }

    public void Quitarcliente(int identificacion){
        int index = BuscarClienteId(identificacion);
        listaClientes.remove(index);
    }

    public void Quitarcliente(String nombreRec){
        int index = BuscarClienteId(nombreRec);
        listaClientes.remove(index);
    }

    public boolean ExisteCliente(int identificacion){
        for(Cliente p : listaClientes){
            if(p.getIdentificacion() == identificacion)
                return true;
        }   
        return false;
    }

    public boolean ExisteCliente(String nombre){
        for(Cliente p : listaClientes){
            if(p.getNombre() == nombre)
                return true;
        }   
        return false;
    }

    public int BuscarClienteId(int identificacion){
        int i = 0;
        for(Cliente p : listaClientes){
            if(p.getIdentificacion() == identificacion)
                return i;
            i++;
        }
        return -1;
    }

    public int BuscarClienteId(String nombreRec){
        int i = 0;
        for(Cliente p : listaClientes){
            if(p.getNombre() == nombreRec)
                return i;
        }
        return -1;
    }
}
