package dk.kea.stud.chris;

import java.util.*;

public class MessageServer implements Observable {
  private List<Observer> observers;
  private Deque<Message> messages;

  public MessageServer() {
    observers = new ArrayList<>();
    messages = new LinkedList<>();
  }

  public void postMessage(Message message) {
    this.messages.push(message);
    notifyObservers();
  }

  public Message getLastMessage() {
    return messages.peek();
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer: observers) {
      observer.update();
    }
  }
}
