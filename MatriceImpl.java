import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.util.ArrayList;

public class MatriceImpl extends UnicastRemoteObject implements Matrice {

    private List<Utilisateur> utilisateurs;

    public MatriceImpl() throws RemoteException {
        super();
        utilisateurs = new ArrayList<>();
        utilisateurs.add(new Utilisateur("user1", "password1"));
        utilisateurs.add(new Utilisateur("user2", "password2"));
    }

    @Override
    public int[][] somme(int[][] m1, int[][] m2) throws RemoteException {
        int rows = m1.length;
        int columns = m1[0].length;
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    @Override
    public int[][] multiplication(int[][] m1, int[][] m2) throws RemoteException {
        // Implementation code for matrix multiplication
        int m1rows = m1.length;
        int m1columns = m1[0].length;
        int m2rows = m2.length;
        int m2columns = m2[0].length;
        int[][] result = new int[m1rows][m2columns];
        for (int i = 0; i < m1rows; i++) {
            for (int j = 0; j < m2columns; j++) {
                for (int k = 0; k < m1columns; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public int[][] transposee(int[][] m) throws RemoteException {
        // Implementation code for matrix transpose
        int rows = m.length;
        int columns = m[0].length;
        int[][] result = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = m[i][j];
            }
        }
        return result;
    }


    
    @Override
    public boolean authenticate(Utilisateur utilisateur) throws RemoteException {
        for (Utilisateur u : utilisateurs) {
            if (u.getUsername().equals(utilisateur.getUsername()) &&
                u.getPassword().equals(utilisateur.getPassword())) {
                return true;
            }
        }
        return false;

    }
   
}

