package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import dao.LicenciaDAO;
import domain.Licencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class pantallaLicenciaControlador implements Initializable {

  @FXML
  private TableView<Licencia> tbvisualizar;

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

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    LicenciaDAO licenciaDao = new LicenciaDAO();
    ObservableList<Licencia> listaObservable =
        FXCollections.observableArrayList(licenciaDao.obtenerLicencias());
    tbvisualizar.setItems(listaObservable);
    tbvisualizar.setDisable(false);

  }

}
