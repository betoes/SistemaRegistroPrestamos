package GUI;

import dao.HardwareDao;
import domain.Hardware;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PantallaHardwareEliminarControlador implements Initializable {

  private HardwareDao hardwareDao = new HardwareDao();

  @FXML
  private TextField txtNumeroSerie;

  @FXML
  private TextField txtTipo;

  @FXML
  private TextField txtModelo;
  
  @FXML
  private TextField txtBuscarNumeroInventario;
  
  @FXML
  private TextField txtNumeroInventario;

  @FXML
  private TextField txtEstado;

  @FXML
  private TextField txtDescripcion;

  @FXML
  private Button bSalir;

  @FXML
  private Button bBuscar;

  @FXML
  private Button bEliminar;

  @FXML
  public void buscarHardware() {
    String numeroInventario = txtBuscarNumeroInventario.getText();
    Hardware hardware = hardwareDao.obtenerHardware(numeroInventario);

    txtNumeroSerie.setText(hardware.getNumeroSerie());
    txtTipo.setText(hardware.getTipo());
    txtModelo.setText(hardware.getModelo());
    txtNumeroInventario.setText(hardware.getNumeroInventario());
    txtEstado.setText(hardware.getEstado());
    txtDescripcion.setText(hardware.getDescripcion());
  }

  @FXML
  public void eliminarHardware() {
    String numeroInventario = txtNumeroInventario.getText();

    hardwareDao.eliminarHardware(numeroInventario);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
  }

}