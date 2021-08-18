package Main;

import java.util.UUID;

public class Tarjeta {
    private static UUID numeroTarjeta = UUID.randomUUID();
    private int password;
    
    public Tarjeta(int password){
        this.password = password;
    }

    public boolean CompPassword(int password){
        boolean ans = ((this.password == password) ? true: false);
        return ans;
    }
    
    public void setPassword(int password, int newPass){
        if(this.password == password)
            this.password = newPass;
    }

    public UUID getNumeroTarjeta(){
        return numeroTarjeta;
    }
}
