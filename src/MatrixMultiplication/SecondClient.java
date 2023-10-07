package MatrixMultiplication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SecondClient {
    public static void main(String[] args) {
        Socket socket;
        ObjectOutputStream objectOutputStream;
        int rows, columns;
        MatrixModel secondMatrix;

        try {
            System.out.println("Second Client Started.");
            socket = new Socket("localhost", 6618);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter rows and columns of the Second Matrix");
            rows = scanner.nextInt();
            columns = scanner.nextInt();

            secondMatrix = new MatrixModel(rows, columns);

            System.out.println("Enter Elements of Second Matrix");
            secondMatrix.setMatrixFromUserInput(scanner);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(secondMatrix);

            objectOutputStream.flush();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}