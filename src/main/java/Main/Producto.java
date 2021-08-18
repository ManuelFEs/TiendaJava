package Main;

public class Producto {
    private int codigo;
    private String nombre;
    private float precioComp;
    private float precioVent;
    private int cantBodega;
    private int cantMinBodega;
    

    public Producto(){
    }

    public Producto(int codigo, String nombre, float precioComp, float precioVent, int cantBodega, int cantMinBodega) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioComp = precioComp;
        this.precioVent = precioVent;
        this.cantBodega = cantBodega;
        this.cantMinBodega = cantMinBodega;
    }


    //GETTERs Y SETTERs

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public float getPrecioComp(){
        return precioComp;
    }

    public void setPrecioComp(float precioComp){
        this.precioComp = precioComp;
    }

    public float getPrecioVent(){
        return precioVent;
    }

    public void setPrecioVent(float precioVent){
        this.precioVent = precioVent;
    }

    public int getCantBodega(){
        return cantBodega;
    }

    public void setCantBodega(int cantBodega){
        this.cantBodega = cantBodega;
    }

    public int getCantMinBodega() {
        return cantMinBodega;
    }

    public void setCantMinBodega(int cantMinBodega) {
        this.cantMinBodega = cantMinBodega;
    }
    
    //Métodos

    public static boolean TieneProductoCantMinima(Producto producto){
        if(producto.getCantBodega() < producto.getCantMinBodega())
            return false;
        return true;
    }
}   