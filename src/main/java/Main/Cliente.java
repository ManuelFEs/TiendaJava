package Main;

import java.util.ArrayList;
import java.util.UUID;

import Main.Funcionalidades.ObjetoAccion;

public class Cliente extends Persona{
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    //constructores

    public Cliente(){}

    public Cliente(int identificacion) {
        this.identificacion = identificacion;
        this.nombre = "";
        this.direccion = "";
        this.telefono = 0;
    }

    public Cliente(int identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = "";
        this.telefono = 0;
    }

    public Cliente(int identificacion, String nombre, String direccion, int telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //GETTERs

    public ArrayList<Cuenta> getCuentas(){
        return cuentas;
    }

    //métodos

    public void AgregarCuenta(Cuenta nuevaCuenta){
        this.cuentas.add(nuevaCuenta);
    }

    public void BorrarCuenta(int idCuenta){
        cuentas.remove(idCuenta);
    }

    public void ListarCuentas(){
        int i = 0;
        for (Cuenta c : cuentas) {
            i++;
            System.out.print(i+") ");
            System.out.print("Cantidad en cuenta: "+c.getDineroCuenta());
            System.out.println();
        }
        if(i == 0)
            System.out.println("No hay cuentas en este cliente");
    }

    public int BuscarCuentaId(UUID identificacion){
        int i = 0;
        for(Cuenta c : cuentas){
            if(c.getCuentaId() == identificacion)
                return i;
            i++;
        }
        return -1;
    }

    public ObjetoAccion BuscarCuenta(int numEnLista){
        UUID idCuenta = UUID.randomUUID();
        int i = 0;
        for(Cuenta c : cuentas){
            if(i == numEnLista)
                return new ObjetoAccion(c.getCuentaId(), 0);
            i++;
        }
        return new  ObjetoAccion(idCuenta, -1);
    }

}
