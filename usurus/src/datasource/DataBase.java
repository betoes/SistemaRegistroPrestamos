package datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class DataBase {

  public static Connection connection;
  public static DataBase database;

  private DataBase() {}

  private static void setConnection() {
    Configuracion configuracion = new Configuracion();
    Properties configuracionProperties = null;
    try {
      configuracionProperties = configuracion.loadConfiguration();
      String url = "jdbc:mysql://" + configuracionProperties.getProperty("server") + "/";
      String utc =
          "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      String databaseName = configuracionProperties.getProperty("database");
      String userName = configuracionProperties.getProperty("username");
      String password = configuracionProperties.getProperty("password");

      connection = DriverManager.getConnection(url + databaseName + utc, userName, password);
    } catch (IOException | SQLException ex) {
      java.util.logging.Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Connection getDataBaseConnection() {
    setConnection();
    return connection;

  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        if (!connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

}
