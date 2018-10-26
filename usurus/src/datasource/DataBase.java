package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ángel Sánchez
 * @version 1.0
 * @since 2018/10/23
 */
public class DataBase {

  private static Connection connection;

  private static void makeConnection() {
    try {
      String url = "jdbc:mysql//localhost/";
      String dataBase = "usurus";
      String userName = "equipoConstruccion";
      String password = "9988774321";

      connection = (Connection) DriverManager.getConnection(url + dataBase, userName, password);
    } catch (SQLException excepcion) {
      java.util.logging.Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, excepcion);
    }
  }

  public static Connection getDataBaseconnection() {
    makeConnection();
    return DataBase.connection;
  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        if (!connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException excepcion) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, excepcion);
      }
    }
  }
}