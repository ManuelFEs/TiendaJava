package Main.Menus;

import java.util.ArrayList;

import Main.Producto;

public class Inventario {
    private ArrayList<Producto> inventarioProductos = new ArrayList<Producto>();

    Inventario(){
    }

    public ArrayList<Producto> getInventarioProductos() {
        return inventarioProductos;
    }

    public void AgregarProducto(Producto producto){
        inventarioProductos.add(producto);
    }

    public void Editar(int index, Producto producto){
        inventarioProductos.set(index, producto);
    }

    public void QuitarProducto(int identificacion){
        int index = BuscarProductoId(identificacion);
        inventarioProductos.remove(index);
    }

    public void QuitarProducto(String nombreRec){
        int index = BuscarProductoId(nombreRec);
        inventarioProductos.remove(index);
    }

    public boolean ExisteProducto(int identificacion){
        for(Producto p : inventarioProductos){
            if(p.getCodigo() == identificacion)
                return true;
        }   
        return false;
    }

    public boolean ExisteProducto(String nombre){
        for(Producto p : inventarioProductos){
            if(p.getNombre() == nombre)
                return true;
        }   
        return false;
    }

    public int BuscarProductoId(int identificacion){
        int i = 0;
        for(Producto p : inventarioProductos){
            if(p.getCodigo() == identificacion)
                return i;
            i++;
        }
        return -1;
    }

    public int BuscarProductoId(String nombreRec){
        int i = 0;
        for(Producto p : inventarioProductos){
            if(p.getNombre() == nombreRec)
                return i;
        }
        return -1;
    }

    public int ProductoMayorCantId(){
        int mayor = -1;
        Producto returned = new Producto();
        for(Producto p : inventarioProductos){
            if(p.getCantBodega() > mayor){
                returned = p;
                mayor = returned.getCantBodega();
            }
        }
        return returned.getCodigo();
    }
    
    public void ListarProductos(){
        int i = 0;
        for (Producto p : inventarioProductos) {
            i++;
            System.out.print(i+") ");
            System.out.print("código: "+p.getCodigo());
            System.out.print("  nombre: "+p.getNombre());
            System.out.print("  coste: "+p.getPrecioVent());
            System.out.print("  cantidad en almacén: "+p.getCantBodega());
            System.out.println();
        }
    }
}
