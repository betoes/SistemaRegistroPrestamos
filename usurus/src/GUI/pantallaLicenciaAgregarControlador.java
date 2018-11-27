package GUI;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import domain.Licencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class pantallaLicenciaAgregarControlador implements Initializable {

  private String idLicencia;
  private int numeroLicencias;
  private Date fechaInicio;
  private Date fechaFin;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;
  private Licencia licencia;

  @FXML
  private TextField txtidLicencia;

  @FXML
  private TextField txtNoLicencias;

  @FXML
  private DatePicker dpFechaAgregado;

  @FXML
  private DatePicker dpFechaExpiracion;

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

  @FXML
  public boolean agregarLicencia() {

    boolean agregado = false;

    idLicencia = txtidLicencia.getText();
    numeroLicencias = Integer.parseInt(txtNoLicencias.getText());



    return agregado;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    cbProveedor.getItems().addAll("Microsoft", "Apple");
    cbTipoLicencia.getItems().addAll("Hardware", "Software");

  }

}
