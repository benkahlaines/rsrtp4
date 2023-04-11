import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
public class server {
public static void main(String[] args) {
    try {
       LocateRegistry.createRegistry( 1099);
        MatriceImpl matrice = new MatriceImpl();
        Naming.rebind("Matrice", matrice);
        System.out.println("Serveur prêt");
    } catch (Exception e) {
        System.err.println("Erreur : " + e);
    
    }
}
}