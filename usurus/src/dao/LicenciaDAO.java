package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import datasource.DataBase;
import domain.Licencia;

public class LicenciaDAO implements ILicenciaDAO {

  private List<Licencia> listaLicencias;
  private String query;
  private Connection connection;
  private Licencia licencia;
  private Date fechaInicio;
  private Date fechaFin;
  private int numeroLicencias;
  private String idLicencia;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;

  public LicenciaDAO() {

  }

  @Override
  public List<Licencia> obtenerLicencias() {

    listaLicencias = new ArrayList<>();
    query = "Select * from licencia";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();

      while (result.next()) {

        idLicencia = result.getString("idLicencia");
        numeroLicencias = result.getInt("numeroLicencias");
        fechaInicio = result.getDate("fechaInicio");
        fechaFin = result.getDate("fechaFin");
        clave = result.getString("clave");
        proveedor = result.getString("proveedor");
        caracter = result.getString("caracter");
        tipoLicenciamiento = result.getString("tipoLicenciamiento");

        licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave,
            proveedor, caracter, tipoLicenciamiento);

        listaLicencias.add(licencia);
      }

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaLicencias;
  }

  @Override
  public Licencia obtenerLicencia(int idLicencia) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean agregarLicencia(Licencia licencia) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean modficarLicencia(Licencia licencia) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean eliminarLicencia(int idLicencia) {
    // TODO Auto-generated method stub
    return false;
  }

}
