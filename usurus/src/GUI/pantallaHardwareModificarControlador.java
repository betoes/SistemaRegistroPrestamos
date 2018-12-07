package GUI;

import dao.HardwareDao;
import domain.Hardware;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class pantallaHardwareModificarControlador implements Initializable {

  private String numeroSerie;
  private String tipo;
  private String modelo;
  private String numeroInventario;
  private String estado;
  private String descripcion;
  private Hardware hardware;
  private HardwareDao hardwareDao = new HardwareDao();

  @FXML
  private TextField txtBuscarNumeroInventario;

  @FXML
  private Button bBuscar;

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

      numeroSerie = txtNumeroSerie.getText();
      tipo = cbTipo.getValue();
      modelo = txtModelo.getText();
      numeroInventario = txtNumeroInventario.getText();
      estado = cbEstado.getValue();
      descripcion = txtDescripcion.getText();

      hardware = new Hardware(numeroSerie, tipo, modelo, numeroInventario, estado, descripcion);

      hardwareDao.modificarHardware(hardware);
      modificado = true;

    }

    if (modificado == true) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("El hardware se ha agregado");

      alert.showAndWait();
    }

  }

  public boolean validarTextoVacio() {
    boolean vacio = true;

    if (txtNumeroSerie.getText().equals("") 
        || cbTipo.getSelectionModel().getSelectedItem().equals(null)
        || txtModelo.getText().equals("") 
        || txtNumeroInventario.getText().equals("") 
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
    cbTipo.getItems().addAll("Nuevo", "En resguardo", "De baja", "En mantenimiento");
    cbEstado.getItems().addAll("CPU", "Video proyector", "LAPTOP", "Impresora");
  }

}