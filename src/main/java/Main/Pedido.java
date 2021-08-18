package Main;

import java.util.ArrayList;
import java.util.UUID;

public class Pedido {
    private ArrayList<Producto> productosPedidos = new ArrayList<Producto>(); //NO USAR sort();
    private ArrayList<Integer> cantProductos = new ArrayList<Integer>();    //NO USAR sort();
    private UUID pedidoId = UUID.randomUUID();
    private UUID cuentaIdPedido;
    private String NombreCliente;
    private int identificacionClient;
    private float costeBruto; //sin iva
    private float costeTotal;
    private static int limitePedido = 20;

    public Pedido(){
    }

    //GETTERs Y SETTERs

    public float getCosteBruto() {
        return costeBruto;
    }

    private void setCosteBruto(float costeBruto) {
        this.costeBruto = costeBruto;
    }

    public int getIdentificacionClient() {
        return identificacionClient;
    }

    public void setIdentificacionClient(int identificacionClient) {
        this.identificacionClient = identificacionClient;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public ArrayList<Producto> getProductosPedidos() {
        return productosPedidos;
    }

    public ArrayList<Integer> getCantProductos() {
        return cantProductos;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public UUID getCuentaIdPedido(){
        return cuentaIdPedido;
    }

    public void setCuentaIdPedido(UUID cuentaIdPedido){
        this.cuentaIdPedido = cuentaIdPedido;
    }

    public float getCosteTotal(){
        return costeTotal;
    }

    private float setCosteTotal(float precioTotal) {
        return this.costeTotal = precioTotal;
    }
    //Métodos

    public void AgregarProducto(Producto InventarioProducto, int ProductoCant){
        getProductosPedidos().add(InventarioProducto);
        cantProductos.add(ProductoCant);
    }

    public void EliminarProducto(int Identificacion, int ProductoCant){
        Producto temp;
        int i, codigo;
        for(i = 0; i < getProductosPedidos().size(); i++){
            temp = getProductosPedidos().get(i);
            codigo = temp.getCodigo();
            if(codigo == Identificacion)
                this.getProductosPedidos().remove(i);
                this.cantProductos.remove(i);
        }  
    }

    public boolean DisponibleInv(ArrayList<Producto>InvProducto, int cantSiguenteProd, int identificacionProd){
        Producto temp;
        int i, codigo, cant = 0;
        for(i = 0; i < InvProducto.size(); i++){
            temp = InvProducto.get(i);
            codigo = temp.getCodigo();
            if(codigo == identificacionProd)
                cant = temp.getCantBodega();
        }
        if(cant < cantSiguenteProd)

            return false;
        return true;
    }

    public boolean SuperaLimitePedido(int cantSiguenteProd){
        int i, cont = 0;
        for(i = 0; i < cantProductos.size(); i++){
            cont += cantProductos.get(i);
            
                this.cantProductos.remove(i);
        }   
        cont += cantSiguenteProd;
        if(cont > limitePedido)
            return true;
        return false;
    }

    public void MostrarInfoPedido(){
        int i = 0;
        System.out.println("Id cliente: "+getIdentificacionClient());
        System.out.println("Nombre cliente: "+getNombreCliente());
        System.out.println("id Cuenta Bancaria: "+getCuentaIdPedido());
        System.out.println("id pedido: "+getPedidoId());
        for(Producto p : getProductosPedidos()){
            System.out.print((i+1) +") ");
            System.out.print("Nombre producto: "+p.getNombre());
            System.out.print("  Precio producto: "+p.getPrecioVent());
            System.out.println("  Cantidad comprada: "+getCantProductos().get(i));
            i++;
        }
        System.out.println("Coste sin iva: "+getCosteBruto());
        System.out.println("Coste total: "+getCosteTotal());
        
    }

    public boolean PuedePagar(Cuenta cuentaPaga){
            if(cuentaPaga.getDineroCuenta() > costeTotal)
                return true;
        return false;
    }

    public void ActualizarCosteBruto() {
        int i = 0, precioBruto = 0;
        for (Producto p : getProductosPedidos()){
            precioBruto += (p.getPrecioVent()) * cantProductos.get(i);
            i++;
        }
        this.setCosteBruto(precioBruto);
    }

    public void ActualizarCosteTotal() {
        int i = 0;
        float precioTotal = 0, iva = 0.05f;
        for (Producto p : getProductosPedidos()){
            precioTotal += (p.getPrecioVent()+(p.getPrecioVent() * iva)) * cantProductos.get(i);
            i++;
        }
        setCosteTotal(precioTotal);
    }

    
}