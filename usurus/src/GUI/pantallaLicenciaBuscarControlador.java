package GUI;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import dao.LicenciaDAO;
import domain.Licencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class pantallaLicenciaBuscarControlador implements Initializable {

  private LicenciaDAO licenciaDao;

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
  private Button bRegresar;

  @FXML
  private Button bBuscar;

  @FXML
  public void buscarLicencia() {

    String id = txtBuscarId.getText();
    System.out.println(id);

    Licencia licencia = licenciaDao.obtenerLicencia(id);

    txtIdLicencia.setText(licencia.getIdLicencia());
    txtNoLicencia.setText(Integer.toString(licencia.getNumeroLicencias()));
    txtFechaAgregado.setText(regresarFecha(licencia.getFechaInicio()));
    txtFechaExpiracion.setText(regresarFecha(licencia.getFechaFin()));
    txtClave.setText(licencia.getClave());
    txtProveedor.setText(licencia.getProveedor());
    txtCaracter.setText(licencia.getCaracter());
    txtTipoLicencia.setText(licencia.getTipoLicenciamiento());

  }

  public String regresarFecha(Date fecha) {

    String fechaR;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    fechaR = format.format(fecha);

    return fechaR;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

  }

}
