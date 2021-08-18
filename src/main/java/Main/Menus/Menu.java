package Main.Menus;

import Main.Cliente;
import Main.Cuenta;
import Main.Pedido;
import Main.Producto;
import Main.Tarjeta;
import Main.Funcionalidades.ObjetoAccion;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

//Esta clase NO está optimizada y debe arreglarse para funcionar más comodamente con una UI que funcione con mouse
//TODO: optimizar


public class Menu {

    private Scanner lector = new Scanner(System.in);
    private GestorPedidos GPedido = new GestorPedidos();
    private GestorClientes GCliente = new GestorClientes();
    private Inventario GInv = new Inventario();
    private Tienda tiendaEnvios = new Tienda();
    private String killProceso = "-a";
    
    public int SiguienteSeleccion(){
        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        int opcion = 0, menuAct = 0;

        //debug
        /*Cliente cliente = new Cliente(1234, "Andres");
        Cuenta cuenta = new Cuenta();
        cuenta.CrearTarjeta(new Tarjeta(1001));
        cliente.AgregarCuenta(cuenta);
        GCliente.Agregar(cliente);

        cliente = new Cliente(1002, "Marta");
        cuenta = new Cuenta();
        cuenta.setDineroCuenta(1000);
        cuenta.CrearTarjeta(new Tarjeta(1002));
        cliente.AgregarCuenta(cuenta);
        GCliente.Agregar(cliente);

        cliente = new Cliente(1003, "Sara");
        cuenta = new Cuenta();
        cuenta.CrearTarjeta(new Tarjeta(1003));
        cliente.AgregarCuenta(cuenta);
        GCliente.Agregar(cliente);

        cliente = new Cliente(1004, "Marco");
        cuenta = new Cuenta();
        cuenta.CrearTarjeta(new Tarjeta(1004));
        cliente.AgregarCuenta(cuenta);
        GCliente.Agregar(cliente);

        cliente = new Cliente(1005, "Vicente");
        cuenta = new Cuenta();
        cuenta.CrearTarjeta(new Tarjeta(1005));
        cliente.AgregarCuenta(cuenta);
        GCliente.Agregar(cliente);

        GInv.AgregarProducto(new Producto(101, "Carro", 10, 100, 10, 2));
        GInv.AgregarProducto(new Producto(102, "Moto", 12, 90, 20, 3));
        GInv.AgregarProducto(new Producto(103, "Casa", 13, 150, 5, 1));
        GInv.AgregarProducto(new Producto(104, "Pato", 14, 40, 80, 20));
        GInv.AgregarProducto(new Producto(105, "Sopa", 15, 30, 100, 50));
        */

        //ciclo de menús
        do{
            opcion = -1;
            switch(menuAct){
                case 0:
                    opcion = menuPrincipal(opcion);
                    switch(opcion){
                        case 1:
                        menuAct=1;
                        break;

                        case 2:
                        menuAct=2;
                        break;

                        case 3:
                        menuAct=3;
                        break;

                        case 4:
                        PasarUnDia();
                        break;

                        case 5:
                        System.out.println("cerrando");
                        break;
                    }
                break;

                case 1:
                    opcion = menuClientes(opcion);

                    switch(opcion){
                        case 1:
                        MenuAnadirCliente();
                        break;
                        case 2:
                        MenuEditarCliente();
                        break;
                        case 3:
                        MenuBuscarCliente(true);
                        break;
                        case 4:
                        MenuEliminarCliente();
                        break;
                        case 5:
                        MenuEditarCuentas();
                        break;
                        case 6:
                        menuAct = 0;
                        break;
                    }
                break;

                case 2:
                opcion = menuProductos(opcion);
                    switch(opcion){
                        case 1:
                        MenuAnadirProducto();
                        break;
                        case 2:
                        MenuEditarProducto();
                        break;
                        case 3:
                        MenuBuscarProducto();
                        break;
                        case 4:
                        MenuListarProductos();
                        break;
                        case 5:
                        MenuEliminarProducto();
                        break;
                        case 6:
                        menuAct = 0;
                        break;
                    }
                break;

                case 3:
                opcion = menuPedidos(opcion);
                    switch(opcion){
                        case 1:
                        MenuAgregarPedido(false);
                        break;
                        case 2:
                        MenuBuscarPedido(1);
                        break;
                        case 3:
                        MenuEditarPedido();
                        break;
                        case 4:
                        MenuEliminarPedido();
                        break;
                        case 5:
                        MenuPagarPedido();
                        break;
                        case 6:
                        menuAct = 0;
                        break;
                    }
                break;
            }
        
        }while(opcion != 5 || menuAct != 0);
        LectorClose();
        return 1;
    }   
    
    //Menú de menús

    public int menuPrincipal(int opcionAct){
        String numeroString;
        boolean EsCorrecto;
        
        int actOpcion = 0, maxOpc = 5;
        do{
            

            System.out.println("┌─────────────┐");
            System.out.println("│  Principal  │");
            System.out.println("└─────────────┘");

            System.out.println("1. Administrar clientes");
            System.out.println("2. Administrar productos");
            System.out.println("3. Administrar pedidos");
            System.out.println("4. Pasar un día");
            System.out.println("5. Cerrar programa");
            numeroString = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            EsCorrecto = EsNumeroCorrecto(numeroString, 1, maxOpc);
            

            if(EsCorrecto)
                actOpcion = Integer.parseInt(numeroString);
            else
                actOpcion = opcionAct;
        }while(actOpcion < 0 || actOpcion > maxOpc);
        return actOpcion;
    }

    //Menú de clientes

    public int menuClientes(int opcionAct){
        int actOpcion = 0, maxOpc = 6;
        String numeroString = "0";
        boolean EsCorrecto = false, EsPrimerEjec = true;
        do{

            

            System.out.println("┌────────────┐");
            System.out.println("│  Clientes  │");
            System.out.println("└────────────┘");

            System.out.println("1. Añadir cliente");
            System.out.println("2. Editar cliente");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Borrar cliente");
            System.out.println("5. Editar cuentas");
            System.out.println("6. Regresar al menú anterior");
            
            if(!EsPrimerEjec)
                numeroString = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(!EsPrimerEjec){
                EsCorrecto = EsNumeroCorrecto(numeroString, 1, maxOpc);
            
            if(EsCorrecto)
                actOpcion = Integer.parseInt(numeroString);
            else
                actOpcion = opcionAct;

            }
            else
                EsPrimerEjec = false;

        }while(!EsCorrecto);
        return actOpcion;
    }

    //Agregar cliente

    public void MenuAnadirCliente(){
        String Sidentificacion;
        int identificacion = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
        System.out.println("Escriba uno a uno los datos del cliente");
        System.out.println("En los campos opcionales de click en enter si no desea guardar ningún dato");
        System.out.println("Si desea agregar una cuenta vaya al menú de agregar cuentas");
        
        do{
            System.out.print("Identificacion, solo números: ");
            Sidentificacion = lector.nextLine();
        
            if(Sidentificacion.equals(killProceso))
                return;
        
            if(CompEsInt(Sidentificacion)){
                identificacion = Integer.parseInt(Sidentificacion);
                for (Cliente c : GCliente.getListaClientes()) {
                    if(c.getIdentificacion() == identificacion){
                        System.out.println("No se pueden usar identificaciones repetidas");
                        System.out.println();
                        identificacion = 0;
                    }
                }
            }

        }while(identificacion <= 0);

        String nombre;
        
        System.out.print("Nombre (opcional): ");
        
        nombre = lector.nextLine();
        
        if(nombre.equals(killProceso))
            return;

        String direccion;

        System.out.print("direccion (opcional): ");
        
        direccion = lector.nextLine();

        if(direccion.equals(killProceso))
            return;

        String Stelefono;
        int telefono = -1;
        
        do{
        telefono = 0;
        System.out.print("telefono (opcional), solo números: ");
        Stelefono = lector.nextLine();
        
        if(Stelefono.equals(killProceso))
            return;

        if(Stelefono.equals(""))
            break;
        
        if(CompEsInt(Stelefono))
            telefono = Integer.parseInt(Stelefono);

        }while(telefono <= 0);
        
        
        Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono);
        GCliente.Agregar(cliente);

        ArrayList<Cliente> clientesTemp = GCliente.getListaClientes();
        Cliente clienteTemp = clientesTemp.get(clientesTemp.size()-1);
        System.out.println();
        System.out.println("datos del cliente");
        System.out.println(clienteTemp.getIdentificacion());
        if(!clienteTemp.getNombre().equals(""))
            System.out.println(clienteTemp.getNombre());
        if(!clienteTemp.getDireccion().equals(""))
            System.out.println(clienteTemp.getDireccion());
        if(clienteTemp.getTelefono() != 0)
            System.out.println(clienteTemp.getTelefono());

        System.out.println("oprima enter para continuar");
        lector.nextLine();
    }

    //Editar cliente

    public void MenuEditarCliente(){
        
        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        
        String SanteriorIdentificacion;
        int anteriorIdentificacion = 0;
        int index = 0;
        do{

            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba uno a uno los datos del cliente");
            System.out.println("En los campos opcionales de click en enter si no desea guardar ningún dato");
            System.out.println();

            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            SanteriorIdentificacion = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SanteriorIdentificacion.equals(killProceso))
                return;

            if(CompEsInt(SanteriorIdentificacion)){
                anteriorIdentificacion = Integer.parseInt(SanteriorIdentificacion);
                index = GCliente.BuscarClienteId(anteriorIdentificacion);
                if(index == -1){
                    anteriorIdentificacion = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
            }
        }while(anteriorIdentificacion <= 0);




        String Sidentificacion;
        int identificacion = 0;
        do{
            System.out.print("Identificacion, solo números: ");
            Sidentificacion = lector.nextLine();

            if(Sidentificacion.equals(killProceso))
                return;

            if(CompEsInt(Sidentificacion)){
                identificacion = Integer.parseInt(Sidentificacion);
                for (Cliente c : GCliente.getListaClientes()) {
                    if(c.getIdentificacion() == identificacion){
                        System.out.println("No se pueden usar identificaciones repetidas");
                        System.out.println();
                        identificacion = 0;
                    }
                }
            }

        }while(identificacion <= 0);

        String nombre;
        
        System.out.print("Nombre (opcional): ");
        
        nombre = lector.nextLine();
        
        if(nombre.equals(killProceso))
                return;

        String direccion;

        System.out.print("dirección (opcional): ");
        
        direccion = lector.nextLine();

        if(direccion.equals(killProceso))
            return;

        String Stelefono;
        int telefono = 0;
        
        
        do{
            System.out.print("telefono(opcional), solo números: ");
            Stelefono = lector.nextLine();

            if(Stelefono.equals(killProceso))
                return;
        
            if(Stelefono.equals(""))
                break;

            if(CompEsInt(Stelefono))
                telefono = Integer.parseInt(Stelefono);
            }while(telefono <= 0);

        
        


        Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono);
        GCliente.Editar(index , cliente);

        Cliente clienteTemp = GCliente.getListaClientes().get(index);
                
        System.out.println(clienteTemp.getIdentificacion());
        if(!clienteTemp.getNombre().equals(""))
            System.out.println(clienteTemp.getNombre());
        if(!clienteTemp.getDireccion().equals(""))
            System.out.println(clienteTemp.getDireccion());
        if(clienteTemp.getTelefono() != 0)
            System.out.println(clienteTemp.getTelefono());

        System.out.println("oprima enter para continuar");
        lector.nextLine();

    }

    //Borrar cliente
    public void MenuEliminarCliente(){
        String Sidentificacion;
        int identificacion = 0;
        int index = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        do{
            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            Sidentificacion = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI


            if(Sidentificacion.equals(killProceso))
                return;
    
            if(CompEsInt(Sidentificacion)){
                identificacion = Integer.parseInt(Sidentificacion);
                index = GCliente.BuscarClienteId(identificacion);
                if(index == -1){
                    identificacion = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
            }
        }while (identificacion <= 0);

        System.out.println("Escriba 'Y' o 'y' si realmente desea borrar el usuario, esta acción NO se puede deshacer");
        String seguro = lector.nextLine();

        if(seguro.equals(killProceso))
                return;

        if(seguro.equals("Y") || seguro.equals("y")){
            GCliente.Quitarcliente(identificacion);
            System.out.println("El cliente fue eliminado");
        }
        else
            System.out.println("El cliente NO fue borrado");

        System.out.println();
        System.out.println("Presione la tecla enter para continuar");
        lector.nextLine();
        
    }

    //Buscar Cliente

    public Cliente MenuBuscarCliente(boolean pausa){
        String Sidentificacion;
        int identificacion = -1;
        int index = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        do{
            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            Sidentificacion = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(Sidentificacion.equals(killProceso))
                return new Cliente();

            if(CompEsInt(Sidentificacion)){
                identificacion = Integer.parseInt(Sidentificacion);
                index = GCliente.BuscarClienteId(identificacion);
                if(index == -1){
                    identificacion = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
                else{
                    Cliente clienteTemp = GCliente.getListaClientes().get(index);
                    
                    if(pausa){
                        System.out.println(clienteTemp.getIdentificacion());
                        if(!clienteTemp.getNombre().equals(""))
                            System.out.println(clienteTemp.getNombre());
                        if(!clienteTemp.getDireccion().equals(""))
                            System.out.println(clienteTemp.getDireccion());
                        if(clienteTemp.getTelefono() != 0)
                            System.out.println(clienteTemp.getTelefono());

                        
                        System.out.println("");
                        System.out.println("Presione enter para continuar");
                        lector.nextLine();
                    }
                    return clienteTemp;
                }
                
            }
        }while (identificacion <= 0);
        return new Cliente();
    }

    //Editar Cuentas
    public void MenuEditarCuentas(){
        Cliente cliente = MenuBuscarCliente(false);
        Cuenta cuenta = new Cuenta();

        String SNumCuenta;
        int i, NumCuenta = 0;
        do{
            i = 0;
            
            System.out.println("Cuentas de "+cliente.getNombre()+": ");
            System.out.println();
            for (Cuenta c : cliente.getCuentas()){
                i++;
                System.out.println(i+") Cantidad: "+c.getDineroCuenta()+" ID de cuenta: "+c.getCuentaId());
            }
            System.out.println((i+1)+") para agregar una cuenta");

            System.out.println("Escriba el número del pedido");
            SNumCuenta = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SNumCuenta.equals(killProceso))
                return;

            if(CompEsInt(SNumCuenta)){
                NumCuenta = Integer.parseInt(SNumCuenta);
                if(NumCuenta == cliente.getCuentas().size()+1){
                    break;
                }
                if(!(NumCuenta <= cliente.getCuentas().size() && NumCuenta > 0)){
                    NumCuenta = -1;
                    System.out.println("Debe escribir un número de los enumeración de cuentas");
                    System.out.println();

                }
                else{
                    cuenta = cliente.getCuentas().get(NumCuenta-1);
                }
            }
        }while(NumCuenta < 0);
        //agregar cuenta
        if(NumCuenta == cliente.getCuentas().size()+1){

            String SValor;
            float valor = 0;
            do{
                System.out.println("Ingrese el valor de la cuenta (opcional)");
                SValor = lector.nextLine();
                
                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(SValor.equals("")){
                    valor = 0;
                }


                if(SValor.equals(killProceso))
                    return;

                if(CompEsInt(SValor)){
                    valor = Integer.parseInt(SValor);

                    if(!(valor >= 0)){
                        valor = -1;
                        System.out.println("La cantidad escrita no es válida");
                        System.out.println();
                    }
                }   
            }while(valor < 0);

            String SContrasena;
            int contrasena = 0;
            do{
                System.out.println("Escriba la contraseña de la tarjeta");
                SContrasena = lector.nextLine();

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(SContrasena.equals(killProceso))
                    return;

                if(CompEsInt(SContrasena)){
                    contrasena = Integer.parseInt(SContrasena);
                    if ((contrasena <= 0)){
                        contrasena = 0;
                        System.out.println("La contraseña debe ser un número positivo");
                    }
                }
            }while(contrasena <= 0);
            cuenta = new Cuenta();
            cuenta.CrearTarjeta(new Tarjeta(contrasena));
            cuenta.setDineroCuenta(valor);
            cuenta.AgregarPago(valor);

            cliente.AgregarCuenta(cuenta);
            return;

        }
        else{
            //borrar cuenta
            String compBorrar;
            System.out.println("Si desea borrar la cuenta, escriba 'YES', 'yes' o 'Yes'");
            compBorrar = lector.nextLine();
        
            if(compBorrar.equals(killProceso))
                return;

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI
            if(compBorrar.equals("YES") || compBorrar.equals("Yes") || compBorrar.equals("yes")){
                System.out.println("Escriba BORRAR en mayúsculas si desea borrar la cuenta, esta acción no se puede deshacer");
                compBorrar = lector.nextLine();
        
                if(compBorrar.equals(killProceso))
                return;

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI
                if(compBorrar.equals("BORRAR")){
                    cliente.BorrarCuenta(cliente.BuscarCuentaId(cuenta.getCuentaId()));
                    System.out.println("La cuenta fue borrada");
                    System.out.println("Pulse enter para continuar");
                    System.out.println();
                    lector.nextLine();
                    return;
                }
            }

        //editar cuenta
        String SValor;
        float valor = 0;
        do{
            System.out.println("Ingrese el valor de la cuenta (opcional)");
            SValor = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SValor.equals("")){
                valor = cuenta.getDineroCuenta();
            }


            if(SValor.equals(killProceso))
                return;

            if(CompEsInt(SValor)){
                valor = Integer.parseInt(SValor);

                if(!(valor >= 0)){
                    valor = -1;
                    System.out.println("La cantidad escrita no es válida");
                    System.out.println();
                }
            }
        }while(valor < 0);

        String SContraActual, SContraNueva, editarT;
        int contraActual = -1, contraNueva = -1;
        System.out.println("Desea editar la tarjeta, escriba 'Y' o 'y'");
        editarT = lector.nextLine();
        
        if(SValor.equals(killProceso))
            return;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI
        if(editarT.equals("y") || editarT.equals("Y")){
            do{
                System.out.println("Escriba la contraseña actual de la tarjeta");
                SContraActual = lector.nextLine();

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(SContraActual.equals(killProceso))
                    return;

                if(CompEsInt(SContraActual)){
                    contraActual = Integer.parseInt(SContraActual);
                    if (!(contraActual <= 0)){
                        if(!(cuenta.getTarjetaCredito().CompPassword(contraActual))){
                        
                        System.out.println("Contraseña Incorrecta");
                        System.out.println();
                        contraActual = 0;
                        }
                    }
                }
            }while(contraActual <= 0);

            do{
                System.out.println("Escriba la NUEVA contraseña de la tarjeta");
                SContraNueva = lector.nextLine();

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(SContraNueva.equals(killProceso))
                    return;

                if(CompEsInt(SContraNueva)){
                    contraNueva = Integer.parseInt(SContraNueva);
                    if ((contraNueva <= 0)){
                        contraNueva = 0;
                        System.out.println("Solo se permiten números positivos");
                    }
                }
            }while(contraNueva <= 0);

            cuenta.getTarjetaCredito().setPassword(contraActual, contraNueva);
        }
        if(valor != cuenta.getDineroCuenta())
            cuenta.AgregarPago(valor);
        cuenta.setDineroCuenta(valor);
        }

    }

    //Menu productos
    public int menuProductos(int opcionAct){
        int actOpcion = 0, maxOpc = 6;
        String numeroString = "0";
        boolean EsCorrecto = false, EsPrimerEjec = true;
        do{
            System.out.println("┌─────────────┐");
            System.out.println("│  Productos  │");
            System.out.println("└─────────────┘");

            System.out.println("1. Añadir producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Buscar producto");
            System.out.println("4. Listar productos");
            System.out.println("5. Borrar producto");
            System.out.println("6. Regresar al menú anterior");
            
            if(!EsPrimerEjec)
                numeroString = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(!EsPrimerEjec){
                EsCorrecto = EsNumeroCorrecto(numeroString, 1, maxOpc);
            
            if(EsCorrecto)
                actOpcion = Integer.parseInt(numeroString);
            else
                actOpcion = opcionAct;

            }
            else
                EsPrimerEjec = false;

        }while(!EsCorrecto);
        return actOpcion;
    }


    //Agregar producto
    public void MenuAnadirProducto(){
        String ScodigoProd;
        int codigoProd = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
        System.out.println("Escriba uno a uno los datos del producto");
        
        do{
            System.out.print("Código del producto, solo números: ");
            ScodigoProd = lector.nextLine();

            if(ScodigoProd.equals(killProceso))
                return;
        
            if(CompEsInt(ScodigoProd)){
                codigoProd = Integer.parseInt(ScodigoProd);
                for (Producto c : GInv.getInventarioProductos()) {
                    if(c.getCodigo() == codigoProd){
                        System.out.println("No se pueden usar códigos repetidas");
                        System.out.println();
                        codigoProd = 0;
                    }
                }
            }

        }while(codigoProd <= 0);

        String nombre;
        do{
            System.out.print("Nombre del producto: ");
            nombre = lector.nextLine();
        
            if(nombre.equals(killProceso))
                return;

        }while(nombre.equals(""));

        String SprecioComp;
        float precioComp = 0;
            
        do{
            System.out.println("Escriba el precio por el que la TIENDA paga el producto");
            System.out.print("Precio, solo números: ");
            SprecioComp = lector.nextLine();
            
            if(SprecioComp.equals(killProceso))
                return;
            
            if(CompEsInt(SprecioComp))
                precioComp = Integer.parseInt(SprecioComp);
    
        }while(precioComp <= 0);

        String SprecioVent;
        float precioVent = 0;
            
        do{
            System.out.println("Escriba el precio de VENTA del producto");
            System.out.print("Precio, solo números: ");
            SprecioVent = lector.nextLine();
            
            if(SprecioVent.equals(killProceso))
                return;
            
            if(CompEsInt(SprecioVent))
                precioVent = Integer.parseInt(SprecioVent);
    
        }while(precioVent <= 0);


        String ScantBodega;
        int cantBodega = 0;
            
        do{
            
            System.out.println("Escriba la cantidad actual del producto en bodega");
            System.out.print("Cantidad, solo números: ");
            ScantBodega = lector.nextLine();
            
            if(ScantBodega.equals(killProceso))
                return;
    
            
            if(CompEsInt(ScantBodega))
                cantBodega = Integer.parseInt(ScantBodega);
    
        }while(cantBodega <= 0);

        String ScantMinBodega;
        int cantMinBodega = 0;
            
        do{
            System.out.println("Escriba la cantidad mínima que puede existir en bodega");
            System.out.print("Cantidad, solo números: ");
            ScantMinBodega = lector.nextLine();
            
            if(ScantMinBodega.equals(killProceso))
                return;
            

            if(CompEsInt(ScantMinBodega))
                cantMinBodega = Integer.parseInt(ScantMinBodega);
            
            

        }while(cantMinBodega < 0);

        
        Producto producto = new Producto(codigoProd, nombre, precioComp, precioVent, cantBodega, cantMinBodega);
        
        GInv.AgregarProducto(producto);

        ArrayList<Producto> prodsTemp = GInv.getInventarioProductos();
        Producto productoTemp = prodsTemp.get(prodsTemp.size()-1);

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        System.out.println("datos del producto");
        System.out.println();
        System.out.println("Código: "+productoTemp.getCodigo());
        System.out.println("Nombre: "+productoTemp.getNombre());
        System.out.println("Precio compra: "+productoTemp.getPrecioComp());
        System.out.println("Precio venta:  "+productoTemp.getPrecioVent());
        System.out.println("Cantidad en almacén: "+productoTemp.getCantBodega());
        System.out.println("Mínimo en almacén:   "+productoTemp.getCantMinBodega());


        System.out.println("oprima enter para continuar");
        lector.nextLine();

        TieneCantMinimaProductos();
    }

    //Editar Producto

    public void MenuEditarProducto(){
        
        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        
        String SanteriorCodigoProd;
        int anteriorCodigoProd = 0;
        int index = 0;
        do{

            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba uno a uno los datos del cliente");
            System.out.println("En los campos opcionales de click en enter si no desea guardar ningún dato");
            System.out.println();

            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            SanteriorCodigoProd = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SanteriorCodigoProd.equals(killProceso))
                return;

            if(CompEsInt(SanteriorCodigoProd)){
                anteriorCodigoProd = Integer.parseInt(SanteriorCodigoProd);
                index = GInv.BuscarProductoId(anteriorCodigoProd);
                if(index == -1){
                    anteriorCodigoProd = 0;
                    System.out.println("El código: escrito no existe");
                    System.out.println();
                }
            }
        }while(anteriorCodigoProd <= 0);

        String ScodigoProd;
        int codigoProd = 0;
        do{
            System.out.print("Código del producto, solo números: ");
            ScodigoProd = lector.nextLine();

            if(ScodigoProd.equals(killProceso))
                return;
        
            if(CompEsInt(ScodigoProd)){
                codigoProd = Integer.parseInt(ScodigoProd);
                for (Producto c : GInv.getInventarioProductos()) {
                    if(c.getCodigo() == codigoProd){
                        System.out.println("No se pueden usar códigos repetidas");
                        System.out.println();
                        codigoProd = 0;
                    }
                }
            }

        }while(codigoProd <= 0);

        String nombre;
        do{
            System.out.print("Nombre del producto: ");
            nombre = lector.nextLine();
        
            if(nombre.equals(killProceso))
                return;

        }while(nombre.equals(""));

        String SprecioComp;
        float precioComp = 0;
            
        do{
            System.out.println("Escriba el precio por el que la TIENDA paga el producto");
            System.out.print("Precio, solo números: ");
            SprecioComp = lector.nextLine();
            
            if(SprecioComp.equals(killProceso))
                return;
            
            if(CompEsInt(SprecioComp))
                precioComp = Integer.parseInt(SprecioComp);
    
        }while(precioComp <= 0);

        String SprecioVent;
        float precioVent = 0;
            
        do{
            System.out.println("Escriba el precio de VENTA del producto");
            System.out.print("Precio, solo números: ");
            SprecioVent = lector.nextLine();
            
            if(SprecioVent.equals(killProceso))
                return;
            
            if(CompEsInt(SprecioVent))
                precioVent = Integer.parseInt(SprecioVent);
    
        }while(precioVent <= 0);


        String ScantBodega;
        int cantBodega = 0;
            
        do{
            
            System.out.println("Escriba la cantidad actual del producto en bodega");
            System.out.print("Cantidad, solo números: ");
            ScantBodega = lector.nextLine();
            
            if(ScantBodega.equals(killProceso))
                return;
    
            
            if(CompEsInt(ScantBodega))
                cantBodega = Integer.parseInt(ScantBodega);
    
        }while(cantBodega <= 0);

        String ScantMinBodega;
        int cantMinBodega = 0;
            
        do{
            System.out.println("Escriba la cantidad mínima que puede existir en bodega");
            System.out.print("Cantidad, solo números: ");
            ScantMinBodega = lector.nextLine();
            
            if(ScantMinBodega.equals(killProceso))
                return;
            
            if(CompEsInt(ScantMinBodega))
                cantMinBodega = Integer.parseInt(ScantMinBodega);

        }while(cantMinBodega < 0);

        
        Producto producto = new Producto(codigoProd, nombre, precioComp, precioVent, cantBodega, cantMinBodega);
        GInv.Editar(index , producto);

        ArrayList<Producto> prodsTemp = GInv.getInventarioProductos();
        Producto productoTemp = prodsTemp.get(index);

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        System.out.println("datos del producto");
        System.out.println();
        System.out.println("Código: "+productoTemp.getCodigo());
        System.out.println("Nombre: "+productoTemp.getNombre());
        System.out.println("Precio compra: "+productoTemp.getPrecioComp());
        System.out.println("Precio venta:  "+productoTemp.getPrecioVent());
        System.out.println("Cantidad en almacén: "+productoTemp.getCantBodega());
        System.out.println("Mínimo en almacén:   "+productoTemp.getCantMinBodega());


        System.out.println("oprima enter para continuar");
        lector.nextLine();

        TieneCantMinimaProductos();
    }

    //Borrar Producto
    public void MenuEliminarProducto(){
        String ScodigoProd;
        int codigoProd = 0;
        int index = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        do{
            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba el código COMPLETAMENTE identico a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            ScodigoProd = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI


            if(ScodigoProd.equals(killProceso))
                return;
    
            if(CompEsInt(ScodigoProd)){
                    codigoProd = Integer.parseInt(ScodigoProd);
                index = GInv.BuscarProductoId(codigoProd);
                if(index == -1){
                    codigoProd = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
            }
        }while (codigoProd <= 0);

        System.out.println("Escriba 'Y' o 'y' si realmente desea borrar el usuario, esta acción NO se puede deshacer");
        String seguro = lector.nextLine();

        if(seguro.equals(killProceso))
                return;

        if(seguro.equals("Y") || seguro.equals("y")){
            GInv.QuitarProducto(codigoProd);
            System.out.println("El cliente fue eliminado");
        }
        else
            System.out.println("El cliente NO fue borrado");

        System.out.println();
        System.out.println("Presione la tecla enter para continuar");
        lector.nextLine();
        
    }

    //Buscar Producto

    public void MenuBuscarProducto(){
        String ScodigoProd;
        int codigoProd = -1;
        int index = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        do{
            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion actual, solo números: ");
            ScodigoProd = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(ScodigoProd.equals(killProceso))
                return;

            if(CompEsInt(ScodigoProd)){
                codigoProd = Integer.parseInt(ScodigoProd);
                index = GInv.BuscarProductoId(codigoProd);
                if(index == -1){
                    codigoProd = 0;
                    System.out.println("El código de producto escrito no existe");
                    System.out.println();
                }
                else{
                    Producto productoTemp = GInv.getInventarioProductos().get(index);
                    
                    System.out.println("Código: "+productoTemp.getCodigo());
                    System.out.println("Nombre: "+productoTemp.getNombre());
                    System.out.println("Precio compra: "+productoTemp.getPrecioComp());
                    System.out.println("Precio venta:  "+productoTemp.getPrecioVent());
                    System.out.println("Cantidad en almacén: "+productoTemp.getCantBodega());
                    System.out.println("Mínimo en almacén:   "+productoTemp.getCantMinBodega());

                    System.out.println("");
                    System.out.println("Presione enter para continuar");
                    lector.nextLine();
                }

            }
        }while (codigoProd <= 0);
    }

    //Listar Productos
    public void MenuListarProductos(){

        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI
        
        GInv.ListarProductos();

        System.out.println("");
        System.out.println("Presione enter para continuar");
        lector.nextLine();

    }


    //Pedidos
    public int menuPedidos(int opcionAct){
        int actOpcion = 0, maxOpc = 6;
        String numeroString = "0";
        boolean EsCorrecto = false, EsPrimerEjec = true;
        do{
            System.out.println("┌─────────────┐");
            System.out.println("│   Pedidos   │");
            System.out.println("└─────────────┘");

            System.out.println("1. Añadir pedido");
            System.out.println("2. Buscar pedido");
            System.out.println("3. Editar pedido");
            System.out.println("4. Borrar pedido");
            System.out.println("5. Pagar pedido");
            System.out.println("6. Regresar al menú anterior");
            
            if(!EsPrimerEjec)
                numeroString = lector.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(!EsPrimerEjec){
                EsCorrecto = EsNumeroCorrecto(numeroString, 1, maxOpc);
            
            if(EsCorrecto)
                actOpcion = Integer.parseInt(numeroString);
            else
                actOpcion = opcionAct;

            }
            else
                EsPrimerEjec = false;

        }while(!EsCorrecto);
        return actOpcion;
    }
    
    

    //Agregar pedido
    public Pedido MenuAgregarPedido(boolean regresaPedido){
        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        //Elegír cliente
        String SIdentificacionCliente;
        int IdentificacionCliente = 0;
        String Nombre;
        int index = 0;

        do{

            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba uno a uno los datos del cliente");
            System.out.println("En los campos opcionales de click en enter si no desea guardar ningún dato");
            System.out.println();

            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificación del cliente actual actual, solo números: ");
            SIdentificacionCliente = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SIdentificacionCliente.equals(killProceso))
                return new Pedido();

            if(CompEsInt(SIdentificacionCliente)){
                IdentificacionCliente = Integer.parseInt(SIdentificacionCliente);
                index = GCliente.BuscarClienteId(IdentificacionCliente);
                if(index == -1){
                    IdentificacionCliente = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
                
            }
        }while(IdentificacionCliente <= 0);

        Cliente clienteEleg = GCliente.getListaClientes().get(index);
        
        Nombre = clienteEleg.getNombre();

        //elegir cuenta de pago
        UUID cuentaId = UUID.randomUUID();
        ObjetoAccion comprobador;
        String SCuentaSelect;
        int CuentaSelect = 0;
        
        do{
            clienteEleg.ListarCuentas();

            System.out.println("Escriba el número de la cuenta seleccionada");

            SCuentaSelect = lector.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SCuentaSelect.equals(killProceso))
                return new Pedido();

            if(CompEsInt(SCuentaSelect)){
                CuentaSelect = Integer.parseInt(SCuentaSelect);
                comprobador = clienteEleg.BuscarCuenta(CuentaSelect-1);
                if(comprobador.accion == -1){
                    CuentaSelect = 0;
                    System.out.println("La cuenta escrita no existe");
                    System.out.println();
                }
                else{
                    cuentaId = (UUID)comprobador.o;
                }
            }
        }while(CuentaSelect <= 0);

        Producto productoTemp = new Producto();
        Pedido pedido = new Pedido();
        String SCantidadProd;
        int CantidadProd = 0;
        int CantidadProdTotal = 0;

        do{
            String SIndexProd;
            int indexProd = 0;
            do{
                System.out.println("para dejar de agregar productos y guardar el pedido escriba -b");
                System.out.println();

                GInv.ListarProductos();

                SIndexProd = lector.nextLine();

                if (SIndexProd.equals("-b")) {
                    pedido.setCuentaIdPedido(cuentaId);
                    pedido.setNombreCliente(Nombre);
                    pedido.setIdentificacionClient(IdentificacionCliente);
                    pedido.ActualizarCosteBruto();
                    pedido.ActualizarCosteTotal();

                    if(pedido.getProductosPedidos().isEmpty()){
                        System.out.print("\033[H\033[2J");
                        System.out.flush(); //comando cls, no universal usa ANSI

                        System.out.println("No se pueden guardar pedidos sin ningún producto pedido");
                        System.out.println("Presione enter para continuar");
                        lector.nextLine();
                    }
                    else{
                        GPedido.Agregar(pedido);
                        return new Pedido();
                    }
                }

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(SIndexProd.equals(killProceso))
                    return new Pedido();

                if(CompEsInt(SIndexProd)){
                    indexProd = Integer.parseInt(SIndexProd);
                    if(indexProd > GInv.getInventarioProductos().size() || indexProd < 1){
                        indexProd = 0;
                        System.out.println("El código de producto escrito no existe");
                        System.out.println();
                    }
                    else{
                            productoTemp = GInv.getInventarioProductos().get(indexProd-1);
                    }
                }
            }while(indexProd <= 0);
        do{
            System.out.println("Escriba la cantidad de productos que va a llevar");
            System.out.println();

            SCantidadProd = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SCantidadProd.equals(killProceso))
                return new Pedido();

                if (SCantidadProd.equals("-b")) {
                    pedido.setCuentaIdPedido(cuentaId);
                    pedido.setNombreCliente(Nombre);
                    pedido.setIdentificacionClient(IdentificacionCliente);
                    pedido.ActualizarCosteBruto();
                    pedido.ActualizarCosteTotal();

                    if(pedido.getProductosPedidos().isEmpty()){
                        System.out.print("\033[H\033[2J");
                        System.out.flush(); //comando cls, no universal usa ANSI

                        System.out.println("No se pueden guardar pedidos sin ningún producto pedido");
                        System.out.println("Presione enter para continuar");
                        lector.nextLine();
                    }
                    else{
                        GPedido.Agregar(pedido);
                        return new Pedido();
                    }
                }

            if(CompEsInt(SCantidadProd)){
                CantidadProd = Integer.parseInt(SCantidadProd);
                if(CantidadProd < 0){
                    System.out.println("La cantidad del producto debe ser un número positivo");
                    System.out.println();
                }
                if(CantidadProdTotal + CantidadProd > 20){
                    CantidadProd = 0;
                    System.out.println("No puede llevar más de 20 productos a la vez");
                    System.out.println();
                }
            }
            }while(CantidadProd <= 0);
            if(CantidadProd > 0){
                pedido.AgregarProducto(productoTemp, CantidadProd);
                CantidadProdTotal += CantidadProd;
                CantidadProd = 0;
            }
        }while(CantidadProdTotal < 20);

        if(CantidadProdTotal == 20){
            System.out.println("Se alcanzó el límite de productos para llevar, se guardará el pedido");
            System.out.println("Presione enter para continuar");
            lector.nextLine();
        }

        pedido.setCuentaIdPedido(cuentaId);
        pedido.setNombreCliente(Nombre);
        pedido.setIdentificacionClient(IdentificacionCliente);
        pedido.ActualizarCosteBruto();
        pedido.ActualizarCosteTotal();
        
        if(regresaPedido)
            return pedido;

        GPedido.Agregar(pedido);
        TieneCantMinimaProductos();

        return pedido;
    }


    //Borrar pedido
    public void MenuEliminarPedido(){
        Pedido pedido = MenuBuscarPedido(0);

        if(pedido.getProductosPedidos().isEmpty())
            return;

        System.out.println("Escriba 'Y' o 'y' si realmente desea borrar el pedido, esta acción NO se puede deshacer");
        String seguro = lector.nextLine();

        if(seguro.equals(killProceso))
            return;

        if(seguro.equals("Y") || seguro.equals("y")){
            GPedido.Quitarpedido(pedido.getPedidoId());
            System.out.println("El pedido fue eliminado");
        }
        else
            System.out.println("El pedido NO fue borrado");

        System.out.println();
        System.out.println("Presione la tecla enter para continuar");
        lector.nextLine();
    }

    //Editar pedido
    public void MenuEditarPedido(){
        Pedido pedidoAct = MenuBuscarPedido(0);

        if(pedidoAct.getProductosPedidos().isEmpty())
            return;

        Pedido pedidoNuev = MenuAgregarPedido(true);

        GPedido.Editar(GPedido.BuscarPedidoId(pedidoAct.getPedidoId()) , pedidoNuev);

        TieneCantMinimaProductos();
    }

    //Buscar pedido
    public Pedido MenuBuscarPedido(int pausa){
        System.out.print("\033[H\033[2J");
        System.out.flush(); //comando cls, no universal usa ANSI

        //Elegír cliente
        String SIdentificacionCliente;
        int IdentificacionCliente = 0;
        int index = 0;

        if(GPedido.getListaPedidos().isEmpty()){
            System.out.println("No hay pedidos, use añadir pedidos para agregar");
            System.out.println("Presione enter para continuar");
            System.out.println();
            lector.nextLine();
            return new Pedido();
        }

        do{
            System.out.println("Si desea regresar al menú anterior escriba '"+killProceso+"' en cualquier momento");
            System.out.println("Escriba uno a uno los datos del cliente");
            System.out.println("En los campos opcionales de click en enter si no desea guardar ningún dato");
            System.out.println();

            System.out.println("Escriba la identificacion COMPLETAMENTE identica a como la escribió");
            System.out.print("Identificacion del cliente, solo números: ");
            SIdentificacionCliente = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SIdentificacionCliente.equals(killProceso))
                return new Pedido();

            if(CompEsInt(SIdentificacionCliente)){
                IdentificacionCliente = Integer.parseInt(SIdentificacionCliente);
                index = GCliente.BuscarClienteId(IdentificacionCliente);
                if(index == -1){
                    IdentificacionCliente = 0;
                    System.out.println("La identificación escrita no existe");
                    System.out.println();
                }
                else{
                    if(!UsuarioTienePedidos(IdentificacionCliente)){
                        IdentificacionCliente = 0;
                        System.out.println("El cliente escrito no tiene pedidos hechos");
                        System.out.println();
                    }
                    
                }
                
            }
        }while(IdentificacionCliente <= 0);
        String SNumPedido;
        int numPedido = 0, i = 0, idPedido = 0;
        ObjetoAccion comprobador;
        do{
            i = 0;
            if (GCliente.getListaClientes().get(index).getNombre().equals(""))
                System.out.println("Pedidos hechos por "+GCliente.getListaClientes().get(index).getNombre()+": ");
            else
                System.out.println("Pedidos hechos por cliente con ID "+GCliente.getListaClientes().get(index).getIdentificacion()+": ");
            System.out.println();
            for (Pedido p : GPedido.getListaPedidos()){
                if(IdentificacionCliente == p.getIdentificacionClient()){
                    i++;
                    System.out.println(i+") "+p.getProductosPedidos().get(0).getNombre()+" "+p.getCantProductos().get(0)+" ... "+p.getPedidoId());
                }
            }
            System.out.println("Escriba el número del pedido");
            SNumPedido = lector.nextLine();
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //comando cls, no universal usa ANSI

            if(SNumPedido.equals(killProceso))
                return new Pedido();

            if(CompEsInt(SNumPedido)){
                numPedido = Integer.parseInt(SNumPedido);
                comprobador = GPedido.FiltroPedidosUno(IdentificacionCliente, numPedido-1);
                if(comprobador.accion == -1){
                    numPedido = 0;
                    System.out.println("Debe escribir un número de los enumeración de pedidos");
                    System.out.println();

                }
                else{
                    idPedido = GPedido.BuscarPedidoId((UUID)comprobador.o);
                }
            }
        }while(numPedido <= 0);
        Pedido pedido = GPedido.getListaPedidos().get(idPedido);
        if(pausa == 1){
            pedido.MostrarInfoPedido();
            System.out.println();
            System.out.println("Presione enter para continuar");
            lector.nextLine();
        }
        return pedido;
    }

    //Pagar pedido
    public void MenuPagarPedido(){
        Pedido pedidoAct = MenuBuscarPedido(0);

        if(pedidoAct.getProductosPedidos().isEmpty())
            return;

        Cuenta cuenta = new Cuenta();
        for(Cliente c : GCliente.getListaClientes()){
            
            if(c.BuscarCuentaId(pedidoAct.getCuentaIdPedido()) != -1)
                cuenta = c.getCuentas().get(c.BuscarCuentaId(pedidoAct.getCuentaIdPedido()));
        }
        boolean puedePagar = pedidoAct.PuedePagar(cuenta);
        try{
        if(puedePagar){
            tiendaEnvios.AgregarPedido(pedidoAct);
            System.out.println("El pedido está listo para el pago");

            String Scontra;
            int contra = 0;
            boolean pedidoCobrado = false;
            do{
                System.out.println("Escriba la contraseña de su tarjeta de crédito para pagar");
                Scontra =  lector.nextLine();

                System.out.print("\033[H\033[2J");
                System.out.flush(); //comando cls, no universal usa ANSI

                if(Scontra.equals(killProceso))
                    return;

                if(CompEsInt(Scontra)){
                    contra = Integer.parseInt(Scontra);
                    pedidoCobrado = tiendaEnvios.CobrarPedido(pedidoAct.getPedidoId(), cuenta, contra);
                    if(!pedidoCobrado){
                        contra = 0;
                        System.out.println("Contraseña incorrecta");
                        System.out.println();

                    }
                    else{
                        System.out.println("Pago exitoso");
                        GPedido.Quitarpedido(pedidoAct.getPedidoId());
                    }
                }
            }while(contra <= 0);
        }
        else{
            System.out.println("El pedido no fue pago, y se debe aumentar el monto en la cuenta para poder pagar");
        }
        }catch(Exception e){
            System.out.println("Hubo un error en el pago, vuelvalo a intentar");
        }
        System.out.println();
        System.out.println("Presione enter para continuar");
        System.out.println();
        lector.nextLine();
    }

    public void PasarUnDia(){
        int i = 0;

        ArrayList<UUID> idPedidoEnvio = new ArrayList<UUID>();

        for(Pedido p : tiendaEnvios.getPedidosEnviados()) {
            idPedidoEnvio.add(p.getPedidoId());
        }

        for(UUID id : idPedidoEnvio){
            tiendaEnvios.ConfirmarPedido(id);
            i++;
        }


        ArrayList<UUID> idPedidoPago = new ArrayList<UUID>();
        
        for(Pedido p : tiendaEnvios.getPedidosPagos()) {
            idPedidoPago.add(p.getPedidoId());
        }

        for(UUID id : idPedidoPago){
            tiendaEnvios.EnviarPedido(id);
        }
        
        System.out.println("Se entregaron "+i+" pedidos el día de hoy");
    }

    //Funciones menús

    public boolean CompEsInt(String numero){
        try {
            Integer.parseInt(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean EsNumeroCorrecto(String numero, int min, int max){
        int numeroInt;
        boolean esNumero = CompEsInt(numero);
        if(!esNumero){
            System.out.println("Debe escribir un número de 1 dígito ej: 3");
            System.out.println();
            return false;
        }
        
        numeroInt = Integer.parseInt(numero);

        if(numeroInt < min || numeroInt > max){
            System.out.println("El número debe ser entre "+ min +" y "+max);
            System.out.println();
            return false;
        }
        
        return true;
    }

    public void TieneCantMinimaProductos(){
        boolean tieneMinimo;
        boolean ejecutado = false;
            for(Producto p : GInv.getInventarioProductos()){
                tieneMinimo = Producto.TieneProductoCantMinima(p);
                if(!tieneMinimo){
                    System.out.println("El producto "+ p.getNombre() +" con id "+ p.getCodigo() +" no tiene suficientes existencias para cumplir el mínimo");
                    ejecutado = true;
                }
            }
            if(ejecutado){
            System.out.println();
            System.out.println("Oprima enter para continuar");
            lector.nextLine();
            }
    }

    public void LectorClose(){
        lector.close();
    }

    public boolean UsuarioTienePedidos(int identificacion){
        int index = GCliente.BuscarClienteId(identificacion);
        int idClienteTemp = GCliente.getListaClientes().get(index).getIdentificacion();
        for(Pedido p : GPedido.getListaPedidos()){
            if(p.getIdentificacionClient() == idClienteTemp)
                return true;
        }
        return false;
    }
}
