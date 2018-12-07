package GUI;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import dao.HardwareDao;
import domain.Hardware;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PantallaHardwareBuscarControlador implements Inizializable {
  private HardwareDao hardwareDao = new HardwareDao();

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
  private Button bRegresar;

  @FXML
  private Button bBuscar;

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