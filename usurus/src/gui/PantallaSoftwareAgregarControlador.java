package GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ISoftwareDao;
import dao.LicenciaDao;
import dao.SoftwareDao;
import domain.Software;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaSoftwareAgregarControlador implements Initializable {

  private Software software;
  private ISoftwareDao softwareDao = new SoftwareDao();

  @FXML
  private TextField txtnombre;
  @FXML
  private TextField txtVersion;
  @FXML
  private TextField txtId;
  @FXML
  private TextField txtMonto;
  @FXML
  private TextArea txtDescripcion;
  @FXML
  private TextField txtIdioma;
  @FXML
  private TextField txtMarca;
  @FXML
  private ComboBox<String> cbTipo;
  @FXML
  private ComboBox<String> cbOrigen;
  @FXML
  private ComboBox<String> cbLicencia;
  @FXML
  private Button bGuardar;
  @FXML
  private Button bSalir;

  @FXML
  public void cargarPantallaSoftware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaSoftware.fxml"));
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

    Stage stage = (Stage) bSalir.getScene().getWindow();

    stage.close();
  }


  /**
   * Metodo para definir la logintud del textfield.
   * 
   * @param textField el textfield que se ocupara
   * @param tamaño la longitud del textfield
   */
  public void tamañoCampo(TextField textField, int tamaño) {
    textField.setOnKeyTyped(event -> {
      int maxCaracter = tamaño;
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
          if (!newValue.matches("[A-z\\s]")) {
            textField.setText(newValue.replaceAll("[^A-z\\s]", ""));
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

  /**
   * Este metodo sirve para agregar un nuevo Software.
   * 
   * @return boolean para ver si fue agregado
   * @throws ParseException por el cambio de string a date
   */
  @FXML
  public boolean agregarSoftware() throws ParseException {

    boolean agregado = false;

    if (validarTextoVacio() == false) {
      if (softwareDao.existe(txtId.getText()) == false) {

        software = new Software(txtId.getText(), txtnombre.getText(), txtVersion.getText(),
            Double.parseDouble(txtMonto.getText()), txtMarca.getText(), txtIdioma.getText(),
            txtDescripcion.getText(), cbOrigen.getValue(), cbTipo.getValue());
        agregado = softwareDao.agregarSoftware(software, cbLicencia.getValue().toString());

      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Ya existe");
        alert.setContentText("El Software ya existe en 	el registro");

        alert.showAndWait();
      }

    }


    if (agregado) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("El software se ha agregado");

      alert.showAndWait();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Software no agregado");
      alert.setContentText("El software no se ha podido agregado");
    }

    return agregado;
  }


  /**
   * Metodo para validar si los campos se encuentran vacios.
   * 
   * @return boolean para ver si estan vacios o no los campos
   * @throws ParseException cambio de variable string a date
   */
  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = true;

    if (txtnombre.getText().equals("") || txtVersion.getText().equals("")
        || txtIdioma.getText().equals("") || txtId.getText().equals("")
        || cbTipo.getValue().toString().equals("Seleccion..") || txtMonto.getText().equals("")
        || txtDescripcion.equals("") || cbOrigen.getValue().toString().equals("Seleccion..")
        || cbLicencia.getValue().toString().equals("Seleccion..")) {

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

    cbTipo.getItems().addAll("adquirido", "desarrollado", "donado", "particular", "asociado");
    cbOrigen.getItems().addAll("tecnico", "laboratorio", "administrativo", "investigacion");

    LicenciaDao lic = new LicenciaDao();
    List<String> listaIdLicencia = lic.obtenerIdLicencia();

    for (String id : listaIdLicencia) {
      cbLicencia.getItems().add(id);
    }

    cbTipo.setValue("Seleccion..");
    cbOrigen.setValue("Seleccion..");
    cbLicencia.setValue("Seleccion..");

    tipoTextoStringNumerico(txtId);
    tipoTextoString(txtnombre);
    tipoTextoString(txtIdioma);
    tipoTextoString(txtMarca);
    tipoTextoNumerico(txtMonto);
    tipoTextoStringNumerico(txtVersion);

    tamañoCampo(txtId, 10);
    tamañoCampo(txtMonto, 6);
    tamañoCampo(txtnombre, 70);
    tamañoCampo(txtVersion, 10);
    tamañoCampo(txtMarca, 45);
    tamañoCampo(txtIdioma, 45);

  }

}
