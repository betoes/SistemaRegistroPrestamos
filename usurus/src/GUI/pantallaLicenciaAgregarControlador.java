package GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.LicenciaDAO;
import domain.Licencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class pantallaLicenciaAgregarControlador implements Initializable {

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
  public void cargarPantallaLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicencia.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(pantallaLicenciaAgregarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bSalir.getScene().getWindow();

    stage.close();
  }


  @FXML
  public boolean agregarLicencia() throws ParseException {

    boolean agregado = false;

    if (validarTextoVacio() == false) {
      if (licenciaDao.existe(txtidLicencia.getText()) == false) {
        if (calcularDias() > 0) {

          idLicencia = txtidLicencia.getText();
          numeroLicencias = Integer.parseInt(txtNoLicencias.getText());
          fechaInicio = regresarFecha(dpFechaAgregado);
          fechaFin = regresarFecha(dpFechaExpiracion);
          clave = txtClave.getText();
          proveedor = cbProveedor.getValue().toString();
          caracter = txtCaracter.getText();
          tipoLicenciamiento = cbTipoLicencia.getValue().toString();

          licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave,
              proveedor, caracter, tipoLicenciamiento);

          licenciaDao.agregarLicencia(licencia);
          agregado = true;
        } else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Informacion");
          alert.setHeaderText("Fecha incorrecta");
          alert.setContentText("Revise las fechas ingresadas\n" + "Al menos un mes de diferencia");

          alert.showAndWait();
        }

      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Ya existe");
        alert.setContentText("La licencia ya existe en el registro");

        alert.showAndWait();
      }

    }


    if (agregado == true) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("La licencia se ha agregado");

      alert.showAndWait();
    }

    return agregado;
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

  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = true;

    if (txtidLicencia.getText().equals("") || txtNoLicencias.getText().equals("")
        || txtClave.getText().equals("") || cbProveedor.getValue().toString().equals("Seleccion..")
        || txtCaracter.getText().equals("")
        || cbTipoLicencia.getValue().toString().equals("Seleccion..")
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

  public int calcularDias() {
    int diasARentar = 0;

    LocalDate fechaA = dpFechaAgregado.getValue();
    LocalDate fechaE = dpFechaExpiracion.getValue();

    int anioInicio = fechaA.getYear();
    int anioFin = fechaE.getYear();
    int diaInicio = fechaA.getDayOfYear();
    int diaFin = fechaE.getDayOfYear();

    if (anioInicio < anioFin) {
      diasARentar = 365 - diaInicio;
      for (int i = anioInicio; i < anioFin; i++) {
        diasARentar += 365;
      }
      diasARentar += diaFin;

    } else if (anioInicio < LocalDate.now().getYear() || (diaInicio < LocalDate.now().getDayOfYear()
        && anioInicio == LocalDate.now().getYear())) {
      diasARentar = -1;
    } else {
      diasARentar = diaFin - diaInicio;
    }

    return diasARentar;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    cbProveedor.getItems().addAll("Microsoft", "Apple");
    cbTipoLicencia.getItems().addAll("Hardware", "Software");
    cbProveedor.setValue("Seleccion..");
    cbTipoLicencia.setValue("Seleccion..");

    dpFechaAgregado.setEditable(false);
    dpFechaExpiracion.setEditable(false);
  }

}
