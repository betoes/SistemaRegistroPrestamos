package gui;

import dao.LicenciaDao;
import domain.Licencia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


/*
 * Esta class controla la pantalla principal de licencia.
 * 
 * @author: Jethran Gomez
 * @version: 1.0
 *
 */
public class PantallaLicenciaControlador implements Initializable {

  @FXML
  private TableView<Licencia> tbLicencia;

  @FXML
  private Button bagregar;

  @FXML
  private Button beliminar;

  @FXML
  private Button bmodificar;

  @FXML
  private Button bbuscar;

  @FXML
  private Button bsalir;

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bbuscar.getScene().getWindow();

    stage.close();
  }

  /**
   * Metodo para cargar la pantalla para agregar un licencia.
   * 
   */
  @FXML
  public void cargarPantallaAgregarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaAgregar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para cargar la pantalla de buscar licencia.
   */
  @FXML
  public void cargarPantallaBuscarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaBuscar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para cargar la pantalla de eliminar licencia.
   */
  @FXML
  public void cargarPantallaElimanarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaEliminar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para cargar la pantalla de modificar licencia.
   */
  @FXML
  public void cargarPantallaModificarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaModificar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    LicenciaDao licenciaDao = new LicenciaDao();
    ObservableList<Licencia> listaObservable =
        FXCollections.observableArrayList(licenciaDao.obtenerLicencias());
    tbLicencia.setItems(listaObservable);
    tbLicencia.setDisable(false);

  }

}
