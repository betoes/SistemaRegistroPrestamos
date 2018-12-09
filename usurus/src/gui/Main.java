package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

  @FXML
  private Button bmantenimiento;

  @FXML
  private Button blicencia;

  @FXML
  private Button bsoftware;

  @FXML
  private Button bhardware;

  @Override
  public void start(Stage stage) throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("pantallaMain.fxml"));

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bsoftware.getScene().getWindow();

    stage.close();
  }

  @FXML
  public void cargarPantallaLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicencia.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
