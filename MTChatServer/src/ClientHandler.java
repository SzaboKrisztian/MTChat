import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Observer, Runnable {
  private String clientName;
  private MessageServer messageServer;
  private Socket connection;
  private PrintWriter out;
  private BufferedReader in;

  public ClientHandler(MessageServer messageServer, Socket socket) {
    this.messageServer = messageServer;
    this.connection = socket;
    try {
      this.out = new PrintWriter(socket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
      System.out.println("IO Error on client creation");
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void run() {
    String input;
    while (true) {
      try {
        input = in.readLine();
      } catch (IOException e) {
        System.out.println("Error receiving message");
      }

    }
  }
}
