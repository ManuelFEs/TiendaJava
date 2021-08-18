package Main.Menus;

import java.util.ArrayList;
import java.util.UUID;

import Main.Cuenta;
import Main.Pedido;

public class Tienda {
    private ArrayList<Pedido> pedidosPendientes = new ArrayList<Pedido>();
    private ArrayList<Pedido> pedidosPagos = new ArrayList<Pedido>();
    private ArrayList<Pedido> pedidosEnviados = new ArrayList<Pedido>();
    private ArrayList<Pedido> pedidosConfirmados = new ArrayList<Pedido>();
    private ArrayList<Pedido> histAprobados = new ArrayList<Pedido>();
    private ArrayList<Pedido> histCobrados = new ArrayList<Pedido>();
    private ArrayList<Pedido> histEnviados = new ArrayList<Pedido>();
    private ArrayList<Pedido> histConfirmados = new ArrayList<Pedido>();
    private ArrayList<Pedido> histRechazados = new ArrayList<Pedido>();
    
    public Tienda(){
    }

    //GETTERs

    public ArrayList<Pedido> getPedidosPagos(){
        return pedidosPagos;
    }

    public ArrayList<Pedido> getPedidosPendientes() {
        return pedidosPendientes;
    }

    public ArrayList<Pedido> getPedidosEnviados() {
        return pedidosEnviados;
    }

    public ArrayList<Pedido> getPedidosConfirmados() {
        return pedidosConfirmados;
    }

    public ArrayList<Pedido> getHistAprobados() {
        return histAprobados;
    }

    public ArrayList<Pedido> getHistRechazados() {
        return histRechazados;
    }

    public ArrayList<Pedido> getHistCobrados() {
        return histCobrados;
    }

    public ArrayList<Pedido> getHistEnviados() {
        return histEnviados;
    }

    public ArrayList<Pedido> getHistConfirmados() {
        return histConfirmados;
    }

    //Métodos

    public void AgregarPedido(Pedido pedido){
        pedidosPendientes.add(pedido);
        
    }

    public boolean CobrarPedido(UUID pedidoId, Cuenta cuenta, int contraTarjeta){
        int index = BuscarPedidoId(pedidoId, pedidosPendientes);

        if(pedidoId == pedidosPendientes.get(index).getPedidoId()){
            if(cuenta.ComprobarPago(cuenta.getTarjetaCredito().getNumeroTarjeta(), contraTarjeta, pedidosPendientes.get(index).getCosteTotal()*-1)){
            pedidosPagos.add(pedidosPendientes.get(index));
            histAprobados.add(pedidosPendientes.get(index));
            return true;
            }
            else{
                histRechazados.add(pedidosPendientes.get(index));
                pedidosPendientes.remove(index);
                return false;
            }
        }
        else{
            
            return false;
        }
    }

    public void EnviarPedido(UUID pedidoId){
        int index = BuscarPedidoId(pedidoId, pedidosPagos);
        pedidosEnviados.add(pedidosPagos.get(index));
        histEnviados.add(pedidosPagos.get(index));
        pedidosPagos.remove(index);
    }

    public void ConfirmarPedido(UUID pedidoId){
        int index = BuscarPedidoId(pedidoId, pedidosEnviados);
        pedidosConfirmados.add(pedidosEnviados.get(index));
        histConfirmados.add(pedidosEnviados.get(index));
        pedidosEnviados.remove(index);
    }

    public int BuscarPedidoId(UUID identificacion, ArrayList<Pedido> Pedidos){
        int i = 0;
        for(Pedido p : Pedidos){
            if(p.getPedidoId() == identificacion)
                return i;
            i++;
        }
        return -1;
    }
}
