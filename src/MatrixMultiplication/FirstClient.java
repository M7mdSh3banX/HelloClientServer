package MatrixMultiplication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FirstClient {
    public static void main(String[] args) {
        Socket socket;
        ObjectOutputStream objectOutputStream;
        int rows, columns;
        MatrixModel firstMatrix;

        try {
            System.out.println("First Client Started.");
            socket = new Socket("localhost", 6618);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter rows and columns of the First Matrix");
            rows = scanner.nextInt();
            columns = scanner.nextInt();

            firstMatrix = new MatrixModel(rows, columns);

            System.out.println("Enter Elements of First Matrix");
            firstMatrix.setMatrixFromUserInput(scanner);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(firstMatrix);

            objectOutputStream.flush();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}