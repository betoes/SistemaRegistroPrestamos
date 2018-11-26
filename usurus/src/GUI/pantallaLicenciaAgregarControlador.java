package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class pantallaLicenciaAgregarControlador implements Initializable {

  @FXML
  private TextField txtidLicencia;

  @FXML
  private TextField txtNoLicencias;

  @FXML
  private DatePicker dpFechaInicio;

  @FXML
  private DatePicker dpFechaFin;

  @FXML
  private TextField txtClave;

  @FXML
  private ComboBox<String> cbProveedor;

  @FXML
  private TextField txtCaracter;

  @FXML
  private ComboBox<String> cbTipoLicencia;

  @FXML
  private Button bGuardar;

  @FXML
  private Button bSalir;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    cbProveedor.getItems().addAll("Microsoft", "Apple");
    cbTipoLicencia.getItems().addAll("Hardware", "Software");

  }

}
