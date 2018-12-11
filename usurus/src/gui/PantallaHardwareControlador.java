package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.HardwareDao;
import domain.Hardware;
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

/**
 * Clase que da funcionamiento a la pantalla principal del hardware.
 * 
 * @author Ángel Sánchez
 * @version 1.0
 *
 */
public class PantallaHardwareControlador implements Initializable {

  @FXML
  private TableView<Hardware> tbHardware;

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
  private TextField txtBuscar;

  @FXML
  private void closeButtonAction() {

    Stage stage = (Stage) bbuscar.getScene().getWindow();

    stage.close();
  }

  /**
   * Metodo para buscar un registro de hardware.
   */
  @FXML
  public void buscar() {
    HardwareDao hardwareDao = new HardwareDao();
    ObservableList<Hardware> listaObservable = FXCollections
        .observableArrayList(hardwareDao.obtenerHardware(txtBuscar.getText()));
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);
  }

  /**
   * Da funcionamiento al botón "Buscar".
   */
  public void buscarHardware() {
    HardwareDao hardwareDao = new HardwareDao();
    List<Hardware> lista = new ArrayList<Hardware>();
    lista.add(hardwareDao.obtenerHardware(txtBuscar.getText()));
    ObservableList<Hardware> listaObservable = FXCollections.observableArrayList(lista);
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);
    desplegarTabla();
  }

  /**
   * Metodo para Eliminar un registro en la tabla de hardware.
   */
  @FXML
  public void eliminarHardwareSeleccionado() {
    HardwareDao hardwaredao = new HardwareDao();
    Hardware hardwareSeleccionado;
    hardwareSeleccionado = tbHardware.getSelectionModel().getSelectedItem();
    if (hardwareSeleccionado != null) {
      hardwaredao.eliminarHardware(hardwareSeleccionado.getNumeroInventario());
      desplegarTabla();
    }
  }

  /**
   * Inicia la pantalla para agregar un nuevo registro de hardware.
   */
  @FXML
  public void cargarPantallaAgregarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareAgregar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void cargarPantallaPrincipal() {

    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("pantallaMain.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);

      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void botonSalir() {
    System.exit(1);
  }

  /**
   * Inicia la pantalla para modificar un hardware.
   */
  @FXML
  public void cargarPantallaModificarHardware() {
    Stage stage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("PantallaHardwareModificar.fxml"));
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      closeButtonAction();

    } catch (IOException ex) {
      Logger.getLogger(PantallaHardwareControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    HardwareDao hardwareDao = new HardwareDao();
    ObservableList<Hardware> listaObservable = FXCollections.observableArrayList(hardwareDao.obtenerHardware());
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);
  }

  ObservableList<Hardware> desplegarTabla() {

    HardwareDao hardwareDao = new HardwareDao();
    ObservableList<Hardware> listaObservable = FXCollections.observableArrayList(hardwareDao.obtenerHardware());
    tbHardware.setItems(listaObservable);
    tbHardware.setDisable(false);

    return listaObservable;
  }
}
