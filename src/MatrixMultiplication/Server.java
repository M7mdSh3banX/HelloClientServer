package MatrixMultiplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        MatrixModel firstMatrix = null, secondMatrix = null, resultMatrix;

        boolean firstClientConnected = false;

        try {
            System.out.println("Waiting for clients...");
            serverSocket = new ServerSocket(6618);

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Connection Established!");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                if (!firstClientConnected) {
                    firstMatrix = (MatrixModel) objectInputStream.readObject();
                    firstClientConnected = true;
                } else {
                    secondMatrix = (MatrixModel) objectInputStream.readObject();
                    resultMatrix = firstMatrix.multiply(secondMatrix);

                    System.out.println("Resultant Matrix:");
                    for (int i = 0; i < resultMatrix.getRows(); i++) {
                        for (int j = 0; j < resultMatrix.getColumns(); j++) {
                            System.out.print(resultMatrix.getElement(i, j) + " ");
                        }
                        System.out.println();
                    }

                    objectOutputStream.close();
                    socket.close();

                    firstClientConnected = false;
                }

                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
