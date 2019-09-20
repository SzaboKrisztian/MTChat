package dk.kea.stud.chris;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
  private MessageServer messageServer;
  private ServerSocket serverSocket;
  private List<ClientHandler> clients;

  public Server(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      messageServer = new MessageServer();
      clients = new ArrayList<>();
  }

  public Socket getConnection() {
    try {
      return serverSocket.accept();
    } catch (IOException e) {
      return null;
    }
  }

  public MessageServer getMessageServer() {
    return messageServer;
  }

  public void addClient(ClientHandler client) {
    clients.add(client);
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    System.out.print("Enter port [def 12345]: ");
    int port;
    try {
      port = Integer.parseInt(scn.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input; using port 12345");
      port = 12345;
    }

    Server server = null;
    try {
      server = new Server(port);
    } catch (IOException e) {
      System.out.println("Failed binding to port " + port + ". Aborting.");
      System.exit(1);
    }

    Socket newConnection;
    while (true) {
      newConnection = server.getConnection();
      if (newConnection != null) {
        ClientHandler client = new ClientHandler(server.getMessageServer(), newConnection);
        server.addClient(client);
        new Thread(client).start();
      }
    }
  }

}
