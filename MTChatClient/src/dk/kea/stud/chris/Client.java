package dk.kea.stud.chris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;
  private final Deque<Message> messages = new LinkedList<>();

  public boolean connect(String address, int port) {
    try {
      clientSocket = new Socket(address, port);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public boolean disconnect() {
    try {
      in.close();
      out.close();
      clientSocket.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public String sendMessage(String message) {
    if (clientSocket == null || out == null || in == null) {
      return "Client not connected.";
    } else {
      out.println(message);
      try {
        return in.readLine();
      } catch (IOException e) {
        return "I/O error on server response.";
      }
    }
  }

  public String getMessage() {
    try {
      return in.readLine();
    } catch (IOException e) {
      return null;
    }
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    System.out.print("Enter address [def 127.0.0.1]: ");

    String address = scn.nextLine();
    if (address.equals("")) {
      address = "127.0.0.1";
    }

    System.out.print("Enter port [def 12345]: ");
    int port;
    String input = scn.nextLine();

    if (input.equals("")) {
      port = 12345;
    } else {
      try {
        port = Integer.parseInt(scn.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input; using port 12345");
        port = 12345;
      }
    }

    Client client = new Client();
    if (client.connect(address, port)) {
      System.out.println("Successfully connected to " + address + ":" + port);
      input = "";
      while (!input.equals("/quit")) {
        input = scn.nextLine();
        System.out.println("Server reply: " + client.sendMessage(input));
      }
      client.disconnect();
    } else {
      System.out.println("Error connecting.");
    }
  }
}
