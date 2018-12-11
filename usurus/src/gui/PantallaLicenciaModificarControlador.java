package gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ILicenciaDao;
import dao.LicenciaDao;
import domain.Licencia;
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
 * Esta clase es el controlador de la pantalla modificar licencia.
 * 
 * @author Jethran Gomez
 * @version 1.0
 * 
 */
public class PantallaLicenciaModificarControlador implements Initializable {

  private Licencia licencia;
  private ILicenciaDao licenciaDao = new LicenciaDao();
  private static final String INFORMACION = "Informacion";
  private static final String SELECCION = "Seleccion..";

  @FXML
  private TextField txtBuscarId;

  @FXML
  private Button btBuscar;

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
  private Button btGuardar;

  @FXML
  private Button btSalir;

  /**
   * Metodo para cargar la pantalla licencia.
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
      Logger.getLogger(PantallaLicenciaModificarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) btBuscar.getScene().getWindow();

    stage.close();
  }

  /**
   * Metodo para buscar una licencia ingresada por el id.
   */
  @FXML
  public void buscarLicencia() {
    String id = txtBuscarId.getText();

    if (!id.equals("")) {
      if (licenciaDao.existe(id)) {
        licencia = licenciaDao.obtenerLicencia(id);

        txtIdLicencia.setText(licencia.getIdLicencia());
        txtNoLicencia.setText(Integer.toString(licencia.getNumeroLicencias()));
        txtClave.setText(licencia.getClave());
        dpFechaAgregado.setValue(LocalDate.parse(regresarFechaString(licencia.getFechaInicio())));
        dpFechaExpiracion.setValue(LocalDate.parse(regresarFechaString(licencia.getFechaFin())));
        cbProveedor.setPromptText(licencia.getProveedor());
        txtCaracter.setText(licencia.getCaracter());
        cbTipoLicencia.setPromptText(licencia.getTipoLicenciamiento());

        txtIdLicencia.setDisable(false);
        txtNoLicencia.setDisable(false);
        txtClave.setDisable(false);
        txtCaracter.setDisable(false);
        btGuardar.setDisable(false);

      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMACION);
        alert.setHeaderText("Sin existencia");
        alert.setContentText("No se cuenta con ningun registro de ese ID: " + id);

        alert.showAndWait();

      }

    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(INFORMACION);
      alert.setHeaderText("Campo vacio");
      alert.setContentText("Agregue un id para buscar");

      alert.showAndWait();
    }
  }


  /**
   * Metodo para modificar una licencia ya buscada.
   * 
   * @throws ParseException por el cambio de string a date
   */
  @FXML
  public void modificarLicencia() throws ParseException {

    if (validarTextoVacio()) {
      if (calcularDias() > 0) {
        String idLicencia = txtIdLicencia.getText();
        int numeroLicencias = Integer.parseInt(txtNoLicencia.getText());
        Date fechaInicio = regresarFecha(dpFechaAgregado);
        Date fechaFin = regresarFecha(dpFechaExpiracion);
        String clave = txtClave.getText();
        String proveedor = cbProveedor.getValue();
        String caracter = txtCaracter.getText();
        String tipoLicenciamiento = cbTipoLicencia.getValue();

        licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave,
            proveedor, caracter, tipoLicenciamiento);

        licenciaDao.modficarLicencia(licencia);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMACION);
        alert.setHeaderText("Agregado");
        alert.setContentText("La licencia se ha agregado");

        alert.showAndWait();
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMACION);
        alert.setHeaderText("Fecha incorrecta");
        alert.setContentText("Revise las fechas ingresadas\n" + "Al menos un mes de diferencia");

        alert.showAndWait();
      }
    }
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


  private String regresarFechaString(Date fecha) {

    String fechaR;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    fechaR = format.format(fecha);

    return fechaR;
  }

  /**
   * Metodo para verificar que los campos estan vacios.
   * 
   * @return boolean para verficar si esta vacio o no los textos
   * @throws ParseException cambiar string a date
   */
  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = false;

    if (txtIdLicencia.getText().equals("") || txtNoLicencia.getText().equals("")
        || txtClave.getText().equals("") || cbProveedor.getValue().equals(SELECCION)
        || txtCaracter.getText().equals("") || cbTipoLicencia.getValue().equals(SELECCION)
        || regresarFecha(dpFechaAgregado) == null || regresarFecha(dpFechaExpiracion) == null) {

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(INFORMACION);
      alert.setHeaderText("Campos vacios");
      alert.setContentText("No puede dejar ningun campo vacio");

      alert.showAndWait();
    } else {
      vacio = true;
    }

    return vacio;
  }

  /**
   * Metodo para calcular los dia que hay entre las fechas.
   * 
   * @return int regresa los dias entre las fechas
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
   * @param tamanio la longitud del textfield
   */
  public void tamanioCampo(TextField textField, int tamanio) {
    textField.setOnKeyTyped(event -> {
      int maxCaracter = tamanio;
      if (textField.getText().length() > maxCaracter) {
        event.consume();
      }
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
   * Metodo para validar que solo permitira letras y numeros el textfield.
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

    cbProveedor.setValue(SELECCION);
    cbTipoLicencia.setValue(SELECCION);

    dpFechaAgregado.setEditable(false);
    dpFechaExpiracion.setEditable(false);

    tamanioCampo(txtBuscarId, 9);
    tamanioCampo(txtIdLicencia, 9);
    tamanioCampo(txtNoLicencia, 10);
    tamanioCampo(txtClave, 39);
    tamanioCampo(txtCaracter, 20);

    tipoTextoStringNumerico(txtBuscarId);
    tipoTextoStringNumerico(txtIdLicencia);
    tipoTextoNumerico(txtNoLicencia);
    tipoTextoStringNumerico(txtClave);
    tipoTextoString(txtCaracter);

    txtIdLicencia.setDisable(true);
    txtNoLicencia.setDisable(true);
    txtClave.setDisable(true);
    txtCaracter.setDisable(true);
    btGuardar.setDisable(true);
  }

}
