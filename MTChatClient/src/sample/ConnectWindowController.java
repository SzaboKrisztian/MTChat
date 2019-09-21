package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ConnectWindowController {
  @FXML private TextField name;
  @FXML private TextField address;
  @FXML private TextField port;

  public void quit() {
    Platform.exit();
  }

  public void connect() {
    int nameLength = name.getText().length();
    int portNum;
    String error = "";
    if (nameLength >= 4 && nameLength <= 16) {
      if (address.getText().matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
        try {
          portNum = Integer.parseInt(port.getText());
          if (portNum > 0 && portNum < 65536) {
            error = "port";
          }
        } catch (NumberFormatException e) {
          error = "port";
        }
      } else {
        error = "address";
      }
    } else {
      error = "name";
    }
    if (!error.equals("")) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText(null);

      switch (error) {
        case "name":
          alert.setContentText("Name must be between 4 and 16 characters long.");
          break;
        case "address":
          alert.setContentText("You must input a valid ip address.");
          break;
        case "port":
          alert.setContentText("Port must be an integer between 1 - 65535.");
      }

      alert.showAndWait();
    } else {
      
    }
  }
}
