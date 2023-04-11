import java.rmi.*;
import java.rmi.server.*;

public interface Matrice extends Remote {
    public int[][] somme(int[][] m1, int[][] m2) throws RemoteException;
    public int[][] multiplication(int[][] m1, int[][] m2) throws RemoteException;
    public int[][] transposee(int[][] m) throws RemoteException;
    public boolean authenticate(Utilisateur user) throws RemoteException;
}



