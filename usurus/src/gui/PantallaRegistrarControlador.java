package gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.DictamenDao;
import dao.HardwareDao;
import dao.IHardwareDao;
import dao.ITecnicoDao;
import dao.TecnicoDao;
import domain.Dictamen;
import domain.Hardware;
import domain.TecnicoAcademico;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase pantalla registro.
 * 
 * @author Jethran Gomez
 *
 */
public class PantallaRegistrarControlador implements Initializable {

  private static final String INFORMATION = "Informacion";
  private static final String SELECCION = "Seleccion..";

  @FXML
  private TextField txtId;

  @FXML
  private TextField txtFecha;

  @FXML
  private Button bGuardar;

  @FXML
  private Button bSalir;

  @FXML
  private TextArea txtDescripcion;

  @FXML
  private TextArea txtObservacion;

  @FXML
  private ComboBox<String> cbTipo;

  @FXML
  private TableView<Hardware> tbHardware;

  @FXML
  private TableView<TecnicoAcademico> tbTecnico;

  /**
   * Metodo para cargar la pantalla mantenimiento
   */
  @FXML
  public void cargarPantallaMantenimiento() {

    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaMantenimiento.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);

      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bGuardar.getScene().getWindow();
    stage.close();

  }

  /**
   * Metodo para regresar datos en la tabla tecnico
   * 
   * @return listaObservable regresa la lista con los tecnicos
   */
  public ObservableList<TecnicoAcademico> desplegarTablaTecnico() {

    ITecnicoDao tecnicoDao = new TecnicoDao();
    ObservableList<TecnicoAcademico> listaObservable =
        FXCollections.observableArrayList(tecnicoDao.obtenerListaTecnico());
    tbTecnico.setItems(listaObservable);
    tbTecnico.setDisable(false);

    return listaObservable;
  }

  /**
   * Metodo para regresar datos en la tabla hardware
   * 
   * @return listaObservable regresa la lista con los hardwares
   */
  public ObservableList<Hardware> desplegarTablaHardware() {

    IHardwareDao hardwareDao = new HardwareDao();
    ObservableList<Hardware> listaObservable =
        FXCollections.observableArrayList(hardwareDao.obtenerHardware());
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);

    return listaObservable;
  }

  /**
   * Metodo para validar si los campos se encuentran vacios.
   * 
   * @return boolean para ver si estan vacios o no los campos
   * @throws ParseException cambio de variable string a date
   */
  public boolean validarTextoVacio() {

    boolean vacio = false;

    if (txtId.getText().equals("") || txtObservacion.getText().equals("")
        || txtDescripcion.getText().equals("") || cbTipo.getValue().equals(SELECCION)) {

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

  /*
   * Metodo para Seleccionar un tecnico
   */
  @FXML
  public String tecnicoSeleccionado() {
    TecnicoAcademico tecnicoSeleccionado;
    String tecnico = "";
    tecnicoSeleccionado = tbTecnico.getSelectionModel().getSelectedItem();
    if (tecnicoSeleccionado != null) {
      tecnico = tecnicoSeleccionado.getNoPersonal();
    }

    return tecnico;
  }

  /*
   * Metodo para Seleccionar un hardware
   */
  @FXML
  public String hardwareSeleccionado() {
    Hardware hardwareSeleccionado;
    String hardware = "";
    hardwareSeleccionado = tbHardware.getSelectionModel().getSelectedItem();
    if (hardwareSeleccionado != null) {
      hardware = hardwareSeleccionado.getNumeroSerie();
    }

    return hardware;
  }

  /**
   * Metodo para agregar un dictamen
   * 
   * @throws ParseException por el cambio String a date
   */
  @FXML
  public void agregarDicatamen() throws ParseException {

    Date fecha;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    String dateI = formato.format(new Date());
    fecha = formato.parse(dateI);


    if (validarTextoVacio()) {

      String idDictamen = txtId.getText();
      Date fechaDictamen = fecha;
      String tipo = cbTipo.getValue();
      String observacion = txtObservacion.getText();
      String descripcion = txtDescripcion.getText();

      Dictamen dictamen = new Dictamen(idDictamen, fechaDictamen, tipo, observacion, descripcion);

      DictamenDao dictamenDao = new DictamenDao();

      String idHardware = hardwareSeleccionado();
      String idTecnico = tecnicoSeleccionado();

      dictamenDao.registrarDictamen(dictamen, idHardware, idTecnico);

    }
  }

  /**
   * Metodo para definir la logintud del textfield.
   * 
   * @param textField el textfield que se ocupara
   * @param tam la longitud del textfield
   */
  public void tamanioCampo(TextField textField, int tam) {
    textField.setOnKeyTyped(event -> {
      int maxCaracter = tam;
      if (textField.getText().length() > maxCaracter) {
        event.consume();
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

  /**
   * Metodo para validar que solo permitira letras y numeros el area.
   * 
   * @param textArea el text area que se ocupara
   */
  public void tipoTextoStringNumericoArea(TextArea textArea) {
    textArea.textProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("[\\w\\s]")) {
            textArea.setText(newValue.replaceAll("[^\\w\\s]", ""));
          }
        });
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    tamanioCampo(txtId, 9);
    tipoTextoStringNumerico(txtId);
    tipoTextoStringNumericoArea(txtObservacion);
    tipoTextoStringNumericoArea(txtObservacion);

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    String dateHoy = formato.format(new Date());

    txtFecha.setText(dateHoy);
    txtFecha.setDisable(true);

    cbTipo.getItems().addAll("Equipo Computo", "Perifericos", "Equipo Comunicacion");
    cbTipo.setValue(SELECCION);

    desplegarTablaTecnico();
    desplegarTablaHardware();
  }

}
