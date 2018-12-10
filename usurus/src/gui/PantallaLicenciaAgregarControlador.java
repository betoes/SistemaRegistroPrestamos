package gui;

import dao.ILicenciaDao;
import dao.LicenciaDao;
import domain.Licencia;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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

/**
 * Esta clase es el controlador de la pantalla para agregar una licencia.
 * 
 * @author Jethran Gomez
 * @version 1.0
 *
 */
public class PantallaLicenciaAgregarControlador implements Initializable {

  private ILicenciaDao licenciaDao = new LicenciaDao();
  private static final String INFORMATION = "Informacion";
  private static final String SELECCTION = "Seleccion..";

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
  private Button btGuardar;

  @FXML
  private Button btSalir;

  /**
   * Esta clase sirve para poder cargar la pantalla principal de licencia.
   * 
   */
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
      Logger.getLogger(PantallaLicenciaAgregarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) btSalir.getScene().getWindow();

    stage.close();
  }

  /**
   * Este metodo sirve para agregar una licencia.
   * 
   * @return boolean para ver si fue agregado
   * @throws ParseException por el cambio de string a date
   */
  @FXML
  public boolean agregarLicencia() throws ParseException {

    boolean agregado = false;

    if (validarTextoVacio()) {
      if (licenciaDao.existe(txtidLicencia.getText())) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMATION);
        alert.setHeaderText("Ya existe");
        alert.setContentText("La licencia ya existe en el registro");

        alert.showAndWait();

      } else {

        if (calcularDias() > 0) {

          String idLicencia = txtidLicencia.getText();
          int numeroLicencias = Integer.parseInt(txtNoLicencias.getText());
          Date fechaInicio = regresarFecha(dpFechaAgregado);
          Date fechaFin = regresarFecha(dpFechaExpiracion);
          String clave = txtClave.getText();
          String proveedor = cbProveedor.getValue();
          String caracter = txtCaracter.getText();
          String tipoLicenciamiento = cbTipoLicencia.getValue();

          Licencia licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin,
              clave, proveedor, caracter, tipoLicenciamiento);

          licenciaDao.agregarLicencia(licencia);
          agregado = true;
        } else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Error");
          alert.setHeaderText("Fecha incorrecta");
          alert.setContentText("Revise las fechas ingresadas\n" + "Al menos un mes de diferencia");

          alert.showAndWait();
        }
      }

    }


    if (agregado) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(INFORMATION);
      alert.setHeaderText("Agregado");
      alert.setContentText("La licencia se ha agregado");

      alert.showAndWait();
    }

    return agregado;
  }

  private Date regresarFecha(DatePicker date) throws ParseException {

    Date fecha;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    LocalDate localDate = date.getValue();

    if (localDate == null) {
      fecha = null;
    } else {
      String dateI = format.format(java.sql.Date.valueOf(localDate.toString()));
      fecha = format.parse(dateI);
    }


    return fecha;
  }

  /**
   * Metodo para validar si los campos se encuentran vacios.
   * 
   * @return boolean para ver si estan vacios o no los campos
   * @throws ParseException cambio de variable string a date
   */
  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = false;

    if (txtidLicencia.getText().equals("") || txtNoLicencias.getText().equals("")
        || txtClave.getText().equals("") || cbProveedor.getValue().equals(SELECCTION)
        || txtCaracter.getText().equals("") || cbTipoLicencia.getValue().equals(SELECCTION)
        || regresarFecha(dpFechaAgregado) == null || regresarFecha(dpFechaExpiracion) == null) {

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(INFORMATION);
      alert.setHeaderText("Campos vacios");
      alert.setContentText("No puede dejar ningun campo vacio");

      alert.showAndWait();
    } else {
      vacio = true;
    }

    return vacio;
  }

  /**
   * Metodo para validar la fecha y calcular cuantos dias tiene de expiracion.
   * 
   * @return int los dias que tiene la licencia
   */
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

  /**
   * Metodo para definir la logintud del textfield.
   * 
   * @param textField el textfield que se ocupara
   * @param tamaño la longitud del textfield
   */
  public void tamanioCampo(TextField textField, int tam) {
    textField.setOnKeyTyped(event -> {
      int maxCaracter = tam;
      if (textField.getText().length() > maxCaracter)
        event.consume();
    });
  }

  /**
   * Metodo para validar que solo permitira numero en el textfield.
   * 
   * @param textField el textfield que se ocupara
   */
  public void tipoTextoNumerico(TextField textField) {
    textField.textProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("[0-9]")) {
            textField.setText(newValue.replaceAll("[^0-9]", ""));
          }
        });
  }

  /**
   * Metodo para validar que solo permitira letras en el textfield.
   * 
   * @param textField el textfield que se ocupara
   */
  public void tipoTextoString(TextField textField) {
    textField.textProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("[A-z]")) {
            textField.setText(newValue.replaceAll("[^A-z]", ""));
          }
        });
  }

  /**
   * Metodo para validar que solo permitira letras y numeros el textfield
   * 
   * @param textField el textfield que se ocupara
   */
  public void tipoTextoStringNumerico(TextField textField) {
    textField.textProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("[\\w]")) {
            textField.setText(newValue.replaceAll("[^\\w]", ""));
          }
        });
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    cbProveedor.getItems().addAll("Microsoft", "Apple");
    cbTipoLicencia.getItems().addAll("FPP", "OEM", "VL");

    cbProveedor.setValue(SELECCTION);
    cbTipoLicencia.setValue(SELECCTION);

    dpFechaAgregado.setEditable(false);
    dpFechaExpiracion.setEditable(false);

    tamanioCampo(txtidLicencia, 9);
    tamanioCampo(txtNoLicencias, 10);
    tamanioCampo(txtClave, 39);
    tamanioCampo(txtCaracter, 20);

    tipoTextoStringNumerico(txtidLicencia);
    tipoTextoNumerico(txtNoLicencias);
    tipoTextoStringNumerico(txtClave);
    tipoTextoString(txtCaracter);
  }
}
