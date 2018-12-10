package gui;

import dao.HardwareDao;
import domain.Hardware;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Clase que da funcionamiento a la pantalla principal del hardware.
 * @author Ángel Sánchez
 * @version 1.0
 *
 */
public class PantallaHardwareControlador implements Initializable {

  @FXML
  private TableView<Hardware> tbHardware;

  @FXML
  private Button bagregar;

  @FXML
  private Button beliminar;

  @FXML
  private Button bmodificar;

  @FXML
  private Button bbuscar;

  @FXML
  private Button bsalir;
  
  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bbuscar.getScene().getWindow();

    stage.close();
  }

  /**
   * Inicia la pantalla para agregar un nuevo registro de hardware.
   */
  @FXML
  public void cargarPantallaAgregarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareAgregar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Iniciar la pantalla para buscar registro en base de datos.
   */
  @FXML
  public void cargarPantallaBuscarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareBuscar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Inicia la pantalla cuando se desea eliminar un registro de hardware.
   */
  @FXML
  public void cargarPantallaEliminarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareEliminar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  
  @FXML
  public void eliminarHardware() {
    Hardware hardware = tbHardware.getSelectionModel().getSelectedItem();
    HardwareDao hardwareDao = new HardwareDao();
    hardwareDao.eliminarHardware(hardware.getNumeroInventario());

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("INFORMACIÓN");
    alert.setHeaderText("Hardware Eliminado");
    alert.setContentText("El hardware ha sido eliminada");

    alert.showAndWait();
  }
  
  @FXML
  public void botonSalir() {
    System.exit(1);
  }

  @FXML
  public void cargarPantallaModificarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareModificar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

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
