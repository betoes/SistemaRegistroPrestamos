package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ISoftwareDao;
import dao.SoftwareDao;
import domain.Software;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaSoftwareControlador implements Initializable {

  @FXML
  private TableView<Software> tbSoftware;

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
  private TextField txtBusqueda;

  /*
   * Metodo para cargar pantalla Agregar Software
   */
  @FXML
  public void cargarPantallaSoftwareAgregar() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaSoftwareAgregar.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaSoftwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * Metodo para Eliminar un registro en la tabla de Software
   */
  @FXML
  public void eliminarSoftwareSeleccionado() {
    SoftwareDao softwaredao = new SoftwareDao();
    Software softwareSeleccionado;
    softwareSeleccionado = tbSoftware.getSelectionModel().getSelectedItem();
    if (softwareSeleccionado != null) {
      softwaredao.eliminarSoftware(softwareSeleccionado.getIdSoftware());
      desplegarTabla();
    }
  }

  /*
   * Metodo para buscar un Registro por su nombre
   */
  @FXML
  public void buscarPorNombre() {
    ISoftwareDao softwareDao = new SoftwareDao();
    ObservableList<Software> listaObservable = FXCollections
        .observableArrayList(softwareDao.obtenerSoftwarePorNombre(txtBusqueda.getText()));
    tbSoftware.setItems(listaObservable);
    tbSoftware.setDisable(false);
  }

  /*
   * Metodo para cargar pantalla Modificar Software
   */
  @FXML
  public void cargarPantallaSoftwareModificar() {

    Software softwareSeleccionado;
    softwareSeleccionado = tbSoftware.getSelectionModel().getSelectedItem();
    if (softwareSeleccionado != null) {
      Stage stage = new Stage();
      try {

        FXMLLoader loader =
            new FXMLLoader(getClass().getResource("PantallaSoftwareModificar.fxml"));

        Parent sceneMain = loader.load();

        PantallaSoftwareModificarControlador pm =
            loader.<PantallaSoftwareModificarControlador>getController();
        pm.asignarInfo(softwareSeleccionado);

        Scene scene = new Scene(sceneMain);
        stage.setScene(scene);
        stage.show();
        closeButtonAction();

      } catch (IOException ex) {
        Logger.getLogger(PantallaSoftwareControlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * Metodo para cargar la pantalla main
   */
  @FXML
  public void cargarPantallaMain() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaMain.fxml"));
      Scene scene = new Scene(root);


      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * Metodo para cerrar Ventana
   */
  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bSalir.getScene().getWindow();

    stage.close();

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    desplegarTabla();

  }

  ObservableList<Software> desplegarTabla() {

    ISoftwareDao softwareDao = new SoftwareDao();
    ObservableList<Software> listaObservable =
        FXCollections.observableArrayList(softwareDao.obtenerSoftwares());
    tbSoftware.setItems(listaObservable);
    tbSoftware.setDisable(false);

    return listaObservable;
  }

}
