import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MatrixClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Matrice service = (Matrice) registry.lookup("Matrice");

            // Get user credentials
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");            String password = scanner.nextLine();
            Utilisateur user = new Utilisateur(username, password);

            // Authenticate user
            boolean isAuthenticated = service.authenticate(user);
            if (isAuthenticated) {
                System.out.println("Authentication successful.");

                // Generate random matrices
                int[][] matrixA = generateMatrix();
                int[][] matrixB = generateMatrix();

                // Perform matrix operations
                int[][] sum = service.somme(matrixA, matrixB);
                int[][] product = service.multiplication(matrixA, matrixB);
                int[][] transpose = service.transposee(matrixA);

                // Display results
                System.out.println("Matrix A:");
                printMatrix(matrixA);
                System.out.println("Matrix B:");
                printMatrix(matrixB);
                System.out.println("Matrix sum:");
                printMatrix(sum);
                System.out.println("Matrix product:");
                printMatrix(product);
                System.out.println("Matrix transpose:");
                printMatrix(transpose);
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static int[][] generateMatrix() {
        int size = 3;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Utilisateur implements java.io.Serializable {
    private String username;
    private String password;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
