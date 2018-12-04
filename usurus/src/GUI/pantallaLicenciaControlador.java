package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.LicenciaDAO;
import domain.Licencia;
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

public class pantallaLicenciaControlador implements Initializable {

  @FXML
  private TableView<Licencia> tbLicencia;

  @FXML
  private Button bAgregar;

  @FXML
  private Button bEliminar;

  @FXML
  private Button bModificar;

  @FXML
  private Button bBuscar;

  @FXML
  private Button bSalir;

  @FXML
  public void cargarPantallaAgregarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaAgregar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(pantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaBuscarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaBuscar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(pantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaElimanarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaEliminar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(pantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaModificarLicencia() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaLicenciaModificar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(pantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    LicenciaDAO licenciaDao = new LicenciaDAO();
    ObservableList<Licencia> listaObservable =
        FXCollections.observableArrayList(licenciaDao.obtenerLicencias());
    tbLicencia.setItems(listaObservable);
    tbLicencia.setDisable(false);

  }

}
