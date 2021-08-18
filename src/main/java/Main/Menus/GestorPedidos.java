package Main.Menus;

import java.util.ArrayList;
import java.util.UUID;
import Main.Pedido;
import Main.Funcionalidades.ObjetoAccion;

public class GestorPedidos {
    private ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

    GestorPedidos(){
    }

    public void Editar(int index, Pedido pedido){
        listaPedidos.set(index, pedido);
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void Agregar(Pedido pedido){
        listaPedidos.add(pedido);
    }

    public void Quitarpedido(UUID idPedido){
        int index = BuscarPedidoId(idPedido);
        listaPedidos.remove(index);
    }

    public boolean ExistePedido(UUID idPedido){
        for(Pedido p : listaPedidos){
            if(p.getPedidoId() == idPedido)
                return true;
        }   
        return false;
    }

    public int BuscarPedidoId(UUID idPedido){
        int i = 0;
        for(Pedido p : listaPedidos){
            if(p.getPedidoId() == idPedido)
                return i;
            i++;
        }
        return -1;
    }
    
    public ObjetoAccion FiltroPedidosUno(int IdentificacionCliente, int idPedido){
        int i = 0;
        UUID UUIDTemp = UUID.randomUUID();
        for (Pedido p : listaPedidos){
            if(IdentificacionCliente == p.getIdentificacionClient()){
                if(i==idPedido){
                    UUIDTemp = p.getPedidoId();
                    return new ObjetoAccion(UUIDTemp, 0);
                }
                i++;
            }
        }
        return new ObjetoAccion(UUIDTemp, -1);
    }
}
