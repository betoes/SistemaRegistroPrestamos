package dao;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datasource.DataBase;
import domain.Hardware;

/**
 *
 * @author Ángel Sánchez
 * @version  1.0
 * @since 2018/10/23
 */
public class HardwareDao implements IHardwareDao {
  private Connection connection;
  private String query;
  ArrayList<Hardware> hardware = new ArrayList<Hardware>();

  public HardwareDao() {
    
  }

  @Override
  public boolean registrarHardware(Hardware nuevo) {
    query = "INSERT INTO hardware VALUES (?,?,?,?,?,?)";
    connection = DataBase.getDataBaseConnection();
    
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, nuevo.getNumeroSerie());
      statement.setString(2, nuevo.getTipo());
      statement.setString(3, nuevo.getDescripcion());
      statement.setString(4, nuevo.getEstado());
      statement.setString(5, nuevo.getModelo());
      statement.setString(6, nuevo.getNumeroInventario());
      
      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(Hardware.class.getName()).log(Level.SEVERE, "My message", ex);
    } finally {
      DataBase.closeConnection();
    }
    
    return false;
  }

  @Override
  public void verSoftware(Hardware hw) {
    connection = DataBase.getDataBaseConnection();
    query = "SELECT * FROM hardware WHERE numeroInventario = ?";

    try {
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setString(1, hw.getNumeroInventario());
	    
	    ResultSet result = statement.executeQuery();
	    result.next();
      String numeroSerie = result.getString("serie");
	    String tipo = result.getString("tipo");
	    String modelo = result.getString("modelo");
	    String numeroInventario = result.getString("numeroInventario");
	    String estado = result.getString("estado");
	    String descripcion = result.getString("descripcion");
	  } catch (SQLException ex) {
	    Logger.getLogger(HardwareDao.class.getName()).log(Level.SEVERE, "Error en la consulta", ex);
	  } finally {
	    DataBase.closeConnection();
	  }
  }

	@Override
	public void eliminarSoftware() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarSoftware() {
		// TODO Auto-generated method stub
		
	}
  
}
