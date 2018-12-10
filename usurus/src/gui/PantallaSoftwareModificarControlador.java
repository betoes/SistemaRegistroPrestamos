package gui;

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

public class PantallaSoftwareModificarControlador implements Initializable {

  private Software software;
  private ISoftwareDao softwareDao = new SoftwareDao();

  private String txtId = "";

  @FXML
  public TextField txtnombre;
  @FXML
  public TextField txtVersion;
  @FXML
  public TextField txtMonto;
  @FXML
  public TextArea txtDescripcion;
  @FXML
  public TextField txtIdioma;
  @FXML
  public TextField txtMarca;
  @FXML
  public ComboBox<String> cbTipo;
  @FXML
  public ComboBox<String> cbOrigen;
  @FXML
  public ComboBox<String> cbLicencia;
  @FXML
  public Button bGuardar;
  @FXML
  public Button bSalir;

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
  public boolean modificarSoftware() throws ParseException {

    boolean modificado = false;

    if (validarTextoVacio() == false) {

      software = new Software(txtId, txtnombre.getText(), txtVersion.getText(),
          Double.parseDouble(txtMonto.getText()), txtMarca.getText(), txtIdioma.getText(),
          txtDescripcion.getText(), cbOrigen.getValue(), cbTipo.getValue());
      modificado = softwareDao.modificarSoftware(software, cbLicencia.getValue().toString());

    }


    if (modificado) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("Agregado");
      alert.setContentText("El registro se ha modificado");

      alert.showAndWait();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Informacion");
      alert.setHeaderText("No se pudó modificar el registro");
      alert.setContentText("El registro no se ha podido modificar");
    }

    return modificado;
  }


  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bSalir.getScene().getWindow();

    stage.close();
  }

  public boolean validarTextoVacio() throws ParseException {

    boolean vacio = true;

    if (txtnombre.getText().equals("") || txtVersion.getText().equals("")
        || txtIdioma.getText().equals("") || cbTipo.getValue().toString().equals("Seleccion..")
        || txtMonto.getText().equals("") || txtDescripcion.equals("")
        || cbOrigen.getValue().toString().equals("Seleccion..")
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

  public void asignarInfo(Software softwareSeleccionado) {
    txtId = softwareSeleccionado.getIdSoftware();
    txtnombre.setText(softwareSeleccionado.getNombre());
    txtVersion.setText(softwareSeleccionado.getVersion());
    txtMonto.setText(String.valueOf(softwareSeleccionado.getMonto()));
    txtDescripcion.setText(softwareSeleccionado.getDescripcion());
    txtIdioma.setText(softwareSeleccionado.getIdioma());
    txtMarca.setText(softwareSeleccionado.getMarca());
    cbOrigen.setValue(softwareSeleccionado.getOrigen());
    cbTipo.setValue(softwareSeleccionado.getTipo());

    ISoftwareDao softwareDao = new SoftwareDao();

    cbLicencia.setValue(softwareDao.obtenerLicenciaSoftware(softwareSeleccionado.getIdSoftware()));

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

    tipoTextoString(txtnombre);
    tipoTextoString(txtIdioma);
    tipoTextoString(txtMarca);
    tipoTextoNumerico(txtMonto);
    tipoTextoStringNumerico(txtVersion);


    tamañoCampo(txtMonto, 6);
    tamañoCampo(txtnombre, 70);
    tamañoCampo(txtVersion, 10);
    tamañoCampo(txtMarca, 45);
    tamañoCampo(txtIdioma, 45);

  }

}


