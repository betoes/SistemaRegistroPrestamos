package gui;

import dao.HardwareDao;
import domain.Hardware;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaHardwareBuscarControlador implements Initializable {
  private HardwareDao hardwareDao = new HardwareDao();

  @FXML
  private TextField txtBuscarNumeroInventario;
  
  @FXML
  private TextField txtNumeroSerie;

  @FXML
  private TextField txtTipo;

  @FXML
  private TextField txtModelo;

  @FXML
  private TextField txtNumeroInventario;

  @FXML
  private TextField txtEstado;

  @FXML
  private TextField txtDescripcion;

  @FXML
  private Button bregresar;

  @FXML
  private Button bbuscar;
  
  @FXML
  public void cargarPantallaHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("¨PantallaHardware.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareAgregarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }
  
  @FXML
  private void closeButtonAction() {
    Stage stage = (Stage) bregresar.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void buscarHardware() {
    String noInventario = txtNumeroInventario.getText();
    Hardware hardware = hardwareDao.obtenerHardware(noInventario);

    txtNumeroSerie.setText(hardware.getNumeroSerie());
    txtTipo.setText(hardware.getTipo());
    txtModelo.setText(hardware.getModelo());
    txtNumeroInventario.setText(hardware.getNumeroInventario());
    txtEstado.setText(hardware.getEstado());
    txtDescripcion.setText(hardware.getDescripcion());
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }
}
