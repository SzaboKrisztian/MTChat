package dk.kea.stud.chris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Observer, Runnable {
  private String clientName;
  private final MessageServer messageServer;
//  private Socket connection;
  private PrintWriter out;
  private BufferedReader in;

  public String getName() {
    return this.clientName;
  }

  public ClientHandler(MessageServer messageServer, Socket socket) {
    this.messageServer = messageServer;
//    this.connection = socket;
    try {
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out.println("Name?");
      clientName = in.readLine();
    } catch (IOException e) {
      System.out.println("IO Error on client creation");
    }
  }

  @Override
  public void update() {
    synchronized (messageServer) {
      out.print(messageServer.getLastMessage());
    }
  }

  @Override
  public void run() {
    String input;
    while (true) {
      try {
        input = in.readLine();
        synchronized (messageServer) {
          messageServer.postMessage(new Message(clientName, input));
        }
      } catch (IOException e) {
        System.out.println("Error receiving message");
      }

    }
  }
}
