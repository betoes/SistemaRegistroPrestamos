package gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ILicenciaDao;
import dao.LicenciaDao;
import domain.Licencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * Clase para controla eliminar una licencia.
 * 
 * @author Jethran Gomez
 * 
 * @version 1.0
 *
 */
public class PantallaLicenciaEliminarControlador implements Initializable {

  private ILicenciaDao licenciaDao = new LicenciaDao();
  private static final String INFORMACION = "Informacion";

  @FXML
  private TextField txtBuscarId;

  @FXML
  private TextField txtIdLicencia;

  @FXML
  private TextField txtNoLicencia;

  @FXML
  private TextField txtFechaAgregado;

  @FXML
  private TextField txtFechaExpiracion;

  @FXML
  private TextField txtClave;

  @FXML
  private TextField txtProveedor;

  @FXML
  private TextField txtCaracter;

  @FXML
  private TextField txtTipoLicencia;

  @FXML
  private Button btSalir;

  @FXML
  private Button btBuscar;

  @FXML
  private Button btEliminar;

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
      Logger.getLogger(PantallaLicenciaEliminarControlador.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) btBuscar.getScene().getWindow();

    stage.close();
  }

  /**
   * Metodo para buscar una licencia ingresada.
   */
  @FXML
  public void buscarLicencia() {
    String id = txtBuscarId.getText();

    if (!id.equals("")) {
      if (licenciaDao.existe(id)) {

        Licencia licencia = licenciaDao.obtenerLicencia(id);

        txtIdLicencia.setText(licencia.getIdLicencia());
        txtNoLicencia.setText(Integer.toString(licencia.getNumeroLicencias()));
        txtFechaAgregado.setText(regresarFecha(licencia.getFechaInicio()));
        txtFechaExpiracion.setText(regresarFecha(licencia.getFechaFin()));
        txtClave.setText(licencia.getClave());
        txtProveedor.setText(licencia.getProveedor());
        txtCaracter.setText(licencia.getCaracter());
        txtTipoLicencia.setText(licencia.getTipoLicenciamiento());
        btEliminar.setDisable(false);

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
      alert.setContentText("Ingrese un id para buscar y realize la accion buscar");

      alert.showAndWait();
    }

  }

  /**
   * Metodo para eliminar una licencia buscada.
   */
  @FXML
  public void eliminarLicencia() {
    String id = txtIdLicencia.getText();

    if (!id.equals("")) {
      if (licenciaDao.existeHardware(id) || licenciaDao.existeSoftware(id)) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMACION);
        alert.setHeaderText("Ocupado");
        alert.setContentText("El identificador: " + id + " esta ocupado en hardware o software");

        alert.showAndWait();

      } else {
        licenciaDao.eliminarLicencia(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMACION);
        alert.setHeaderText("Licencia Eliminada");
        alert.setContentText("La licencia ha sido eliminada");

        alert.showAndWait();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(INFORMACION);
      alert.setHeaderText("Campo vacio");
      alert.setContentText("Ingrese un id para buscar");

      alert.showAndWait();
    }

  }


  private String regresarFecha(Date fecha) {

    String fechaR;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    fechaR = format.format(fecha);

    return fechaR;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    txtIdLicencia.setDisable(true);
    txtNoLicencia.setDisable(true);
    txtFechaAgregado.setDisable(true);
    txtFechaExpiracion.setDisable(true);
    txtClave.setDisable(true);
    txtProveedor.setDisable(true);
    txtCaracter.setDisable(true);
    txtTipoLicencia.setDisable(true);
    btEliminar.setDisable(true);

  }

}
