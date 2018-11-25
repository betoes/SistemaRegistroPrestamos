package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import datasource.DataBase;
import domain.Licencia;

public class LicenciaDAO implements ILicenciaDAO {

  private List<Licencia> listaLicencias;
  private String query;
  private Connection connection;

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
        Licencia licencia = new Licencia();
        licencia.setIdLicencia(result.getString("idLicencia"));
        licencia.setNumeroLicencias(result.getInt("numeroLicencias"));
        licencia.setFechaInicio(result.getDate("fechaInicio"));
        licencia.setFechaFin(result.getDate("fechaFin"));
        licencia.setClave(result.getString("clave"));
        licencia.setProveedor(result.getString("proveedor"));
        licencia.setCaracter(result.getString("caracter"));
        licencia.setTipoLicenciamiento(result.getString("tipoLicenciamiento"));

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
