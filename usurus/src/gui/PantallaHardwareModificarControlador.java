package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.HardwareDao;
import domain.Hardware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaHardwareModificarControlador implements Initializable {

  private Hardware hardware;
  private HardwareDao hardwareDao = new HardwareDao();

  @FXML
  private TextField txtBuscarNumeroInventario;

  @FXML
  private Button bbuscar;

  @FXML
  private TextField txtNumeroSerie;

  @FXML
  private ComboBox<String> cbTipo;

  @FXML
  private TextField txtModelo;

  @FXML
  private TextField txtNumeroInventario;

  @FXML
  private ComboBox<String> cbEstado;

  @FXML
  private TextField txtDescripcion;

  @FXML
  private Button bguardar;

  @FXML
  private Button bsalir;

  @FXML
  public void cargarPantallaHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaHardware.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareModificarControlador.class.getName()).log(Level.SEVERE, 
          null, ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bbuscar.getScene().getWindow();

    stage.close();
  }

  @FXML
  public void buscarHardware() {
    String numeroInventario = txtBuscarNumeroInventario.getText();
    hardware = hardwareDao.obtenerHardware(numeroInventario);

    txtNumeroSerie.setText(hardware.getNumeroSerie());
    cbTipo.setPromptText(hardware.getTipo());
    txtModelo.setText(hardware.getModelo());
    txtNumeroInventario.setText(hardware.getNumeroInventario());
    cbEstado.setPromptText(hardware.getEstado());
    txtDescripcion.setText(hardware.getDescripcion());

  }

  @FXML
  public void modificarHardware() {
    boolean modificado = false;

    if (validarTextoVacio() == false) {

      String numeroSerie = txtNumeroSerie.getText();
      String tipo = cbTipo.getValue();
      String modelo = txtModelo.getText();
      String numeroInventario = txtNumeroInventario.getText();
      String estado = cbEstado.getValue();
      String descripcion = txtDescripcion.getText();

      hardware = new Hardware(numeroSerie, tipo, modelo, numeroInventario, estado, descripcion);

      hardwareDao.modificarHardware(hardware);
      modificado = true;

    }

    if (modificado == true) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Información");
      alert.setHeaderText("Agregado");
      alert.setContentText("El hardware se ha agregado");

      alert.showAndWait();
    }

  }

  public boolean validarTextoVacio() {
    boolean vacio = true;

    if (txtNumeroSerie.getText().equals("")
        || cbTipo.getSelectionModel().getSelectedItem().equals(null)
        || txtModelo.getText().equals("") || txtNumeroInventario.getText().equals("")
        || cbEstado.getValue().equals("")) {

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Campos vacios");
      alert.setContentText("No puede dejar ningún campo vacío");

      alert.showAndWait();
    } else {
      vacio = false;
    }

    return vacio;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    cbTipo.getItems().addAll("Nuevo", "En resguardo", "De baja", "En mantenimiento");
    cbEstado.getItems().addAll("CPU", "Video proyector", "LAPTOP", "Impresora");
  }

}
