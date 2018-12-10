package gui;

import dao.HardwareDao;
import dao.LicenciaDao;
import domain.Hardware;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * Sirve para iniciar la ventana que registra un hardware.
 * @author Angel Sanchez
 * @version 1.0
 *
 */
public class PantallaHardwareAgregarControlador implements Initializable {

  private String numeroSerie;
  private String tipo;
  private String modelo;
  private String numeroInventario;
  private String estado;
  private String descripcion;
  private Hardware hardware;
  private HardwareDao hardwareDao = new HardwareDao();

  @FXML
  private TextField txtNumeroSerie;

  @FXML
  private ComboBox<String> cbTipo;
  
  @FXML
  private ComboBox<String> cbIdLicencia;

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
  
  /**
   * Inicia los componentes de la pantalla porincipal del hardware.
   */
  @FXML
  public void cargarPantallaHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardware.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareAgregarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }
  
  /**
   * Da funcionamiento al botón Cancelar.
   */
  @FXML
  private void closeButtonAction() {
    Stage stage = (Stage) bsalir.getScene().getWindow();
    stage.close();
  }

  @FXML
  public boolean agregarHardware() {
    boolean agregado = false;

    if (validarTextoVacio() == false) {

      if (hardwareDao.validar(txtNumeroInventario.getText()) == false) {
        numeroSerie = txtNumeroSerie.getText();
        tipo = cbTipo.getValue();
        modelo = txtModelo.getText();
        numeroInventario = txtNumeroInventario.getText();
        estado = cbEstado.getValue();
        descripcion = txtDescripcion.getText();

        hardware = new Hardware(numeroSerie, tipo, modelo, numeroInventario, estado, descripcion);
        agregado = hardwareDao.registrarHardware(hardware);
        
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Ya existe");
        alert.setContentText("El hardware ya existe en el registro");

        alert.showAndWait();
      }

    }

    if (agregado == true) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("El hardware se ha agregado");

      alert.showAndWait();
    }

    return agregado;
  }

  /**
   * Valida que ninún campo esté vacío.
   * @return true Si todos los campos están llenos
   */
  public boolean validarTextoVacio() {

    boolean vacio = true;

    if (txtNumeroSerie.getText().equals("")
        || cbTipo.getSelectionModel().getSelectedItem().equals(null)
        || txtModelo.getText().equals("") || txtNumeroInventario.getText().equals("")
        || cbEstado.getValue().equals("")) {

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Campos vacios");
      alert.setContentText("No puede dejar ningun campo vacio");

      alert.showAndWait();
    } else {
      vacio = false;
    }

    return vacio;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    cbEstado.getItems().addAll("Nuevo", "En resguardo", "De baja", "En mantenimiento");
    cbTipo.getItems().addAll("CPU", "Video proyector", "LAPTOP", "Impresora");
    LicenciaDao licencias = new LicenciaDao();
    List<String> idLicencias = licencias.obtenerIdLicencia();
    for (String id : idLicencias) {
      cbIdLicencia.getItems().add(id);
    }
    
  }

}
