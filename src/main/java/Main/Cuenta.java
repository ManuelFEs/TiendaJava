package Main;

import java.util.ArrayList;
import java.util.UUID;

public class Cuenta {
    private float dineroCuenta;
    private ArrayList<Float> historialPagos = new ArrayList<Float>();
    private Tarjeta tarjetaCredito;
    private UUID cuentaId = UUID.randomUUID();
    
    public Cuenta(){
    }
    
    

    public void CrearTarjeta(Tarjeta tarjetaCredito){
        this.tarjetaCredito = tarjetaCredito;
    }

    public Tarjeta getTarjetaCredito(){
        return tarjetaCredito;
    }

    public UUID getCuentaId(){
        return cuentaId;
    }

    public float getDineroCuenta(){
        return dineroCuenta;
    }

    public void setDineroCuenta(float dinero){
        this.dineroCuenta = dinero;
    }

    public boolean ComprobarPago(UUID numeroTarjeta, int contraTarjeta, float EntradaTransaccion){ //EntradaTransaccion es positivo ingresos, negativo salidas
        if(numeroTarjeta == tarjetaCredito.getNumeroTarjeta()){
            if(tarjetaCredito.CompPassword(contraTarjeta)){
                calcDineroCuenta(EntradaTransaccion);
                return true;
            }
        }
        return false;
    }

    private void calcDineroCuenta(float dinero){
        dinero = this.dineroCuenta + dinero;
        
        setDineroCuenta(dinero);
        AgregarPago(dinero);
    }

    public void AgregarPago(float dinero){  //al instanciar y recibir el primer ingreso guardarlo aquí
        historialPagos.add(dinero);
    }
}
