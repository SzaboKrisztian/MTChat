package dk.kea.stud.chris;

import java.time.LocalDateTime;

public class Message {
  private String author;
  private String content;
  private LocalDateTime timestamp;

  public Message(String author, String content) {
    this.author = author;
    this.content = content;
    this.timestamp = LocalDateTime.now();
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
