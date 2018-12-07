package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import dao.HardwareDao;
import domain.Hardware;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
  private TextField txtModelo;

  @FXML
  private TextField txtNumeroInventario;

  @FXML
  private ComboBox<String> cbEstado;

  @FXML
  private TextField txtDescripcion;

  @FXML
  private Button bGuardar;

  @FXML
  private Button bSalir;

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

        hardwareDao.registrarHardware(hardware);
        agregado = true;
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

  }

}
