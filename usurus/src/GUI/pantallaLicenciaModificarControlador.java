package GUI;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import dao.LicenciaDAO;
import domain.Licencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class pantallaLicenciaModificarControlador implements Initializable {

  private String idLicencia;
  private int numeroLicencias;
  private Date fechaInicio;
  private Date fechaFin;
  private LocalDate localDate;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;
  private Licencia licencia;
  private LicenciaDAO licenciaDao = new LicenciaDAO();

  @FXML
  private TextField txtBuscarId;

  @FXML
  private Button bBuscar;

  @FXML
  private TextField txtIdLicencia;

  @FXML
  private TextField txtNoLicencia;

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
  public void BuscarLicencia() {
    String id = txtBuscarId.getText();

    licencia = licenciaDao.obtenerLicencia(id);


    txtIdLicencia.setText(licencia.getIdLicencia());
    txtNoLicencia.setText(Integer.toString(licencia.getNumeroLicencias()));
    txtClave.setText(licencia.getClave());
    dpFechaAgregado.setValue(LocalDate.parse(regresarFechaString(licencia.getFechaInicio())));
    dpFechaExpiracion.setValue(LocalDate.parse(regresarFechaString(licencia.getFechaFin())));
    cbProveedor.setPromptText(licencia.getProveedor());
    txtCaracter.setText(licencia.getCaracter());
    cbTipoLicencia.setPromptText(licencia.getTipoLicenciamiento());

  }

  @FXML
  public void modificarLicencia() throws ParseException {

    boolean modificado = false;

    if (validarTextoVacio() == false) {

      idLicencia = txtIdLicencia.getText();
      numeroLicencias = Integer.parseInt(txtNoLicencia.getText());
      fechaInicio = regresarFecha(dpFechaAgregado);
      fechaFin = regresarFecha(dpFechaExpiracion);
      clave = txtClave.getText();
      proveedor = cbProveedor.getValue().toString();
      caracter = txtCaracter.getText();
      tipoLicenciamiento = cbTipoLicencia.getValue().toString();

      licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave, proveedor,
          caracter, tipoLicenciamiento);

      licenciaDao.modficarLicencia(licencia);
      modificado = true;

    }

    if (modificado == true) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("La licencia se ha agregado");

      alert.showAndWait();
    }

  }

  private Date regresarFecha(DatePicker date) throws ParseException {

    Date fecha;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    localDate = date.getValue();

    if (localDate == null) {
      fecha = null;
    } else {
      String dateI = format.format(java.sql.Date.valueOf(localDate.toString()));
      fecha = format.parse(dateI);
    }


    return fecha;
  }

  public String regresarFechaString(Date fecha) {

    String fechaR;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    fechaR = format.format(fecha);

    return fechaR;
  }

  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = true;

    if (txtIdLicencia.getText().equals("") || txtNoLicencia.getText().equals("")
        || txtClave.getText().equals("") || cbProveedor.getValue().toString().equals("")
        || txtCaracter.getText().equals("") || cbTipoLicencia.getValue().toString().equals("")
        || regresarFecha(dpFechaAgregado) == null || regresarFecha(dpFechaExpiracion) == null) {

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
    // TODO Auto-generated method stub
    cbProveedor.getItems().addAll("Microsoft", "Apple");
    cbTipoLicencia.getItems().addAll("Hardware", "Software");
  }

}
