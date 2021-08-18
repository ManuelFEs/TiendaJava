package Main;

public abstract class Persona{
    
    protected int identificacion;
    protected String nombre;
    protected String direccion;
    protected int telefono;
    
    //GETTERs Y SETTERs

    
    public void setIdentificacion(int identificacion){
        this.identificacion = identificacion;
    }
    
    public int getIdentificacion(){
        return identificacion;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public int getTelefono(){
        return telefono;
    }

    public void getTelefono(int telefono){
        this.telefono = telefono;
    }
}

