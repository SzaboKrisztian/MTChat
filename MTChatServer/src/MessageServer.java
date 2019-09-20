import java.util.ArrayList;
import java.util.List;

public class MessageServer implements Observable {
  private List<Observer> observers;
  private List<String> messages;

  public MessageServer() {
    observers = new ArrayList<>();
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
