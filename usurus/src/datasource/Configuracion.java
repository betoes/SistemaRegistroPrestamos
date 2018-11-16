package datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuracion { 
  public Properties loadConfiguration() throws FileNotFoundException, IOException {        
    Properties properties = new Properties();
    properties.load(this.getClass().getResourceAsStream("configuracion.properties"));
        
    return properties;
  }
}