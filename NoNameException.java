package OOP_UrGame;

import javafx.scene.control.Alert;

public class NoNameException extends Exception {
    public NoNameException() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Sa ei sisestanud oma nime!");
        a.showAndWait();
    }
}
