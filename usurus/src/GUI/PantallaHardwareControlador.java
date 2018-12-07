package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.HardwareDao;
import domain.Hardware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PantallaHardwareControlador implements Initializable {

  @FXML
  private TableView<Hardware> tbHardware;

  @FXML
  private Button bAgregar;

  @FXML
  private Button bEliminar;

  @FXML
  private Button bModificar;

  @FXML
  private Button bBuscar;

  @FXML
  private Button bSalir;

  @FXML
  public void cargarPantallaAgregarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaHardwareAgregar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaBuscarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaHardwareBuscar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaElimanarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaHardwareEliminar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaModificarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaHardwareModificar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    HardwareDao hardwareDao = new HardwareDao();
    ObservableList<Hardware> listaObservable =
        FXCollections.observableArrayList(hardwareDao.obtenerHardware());
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);

  }
}
