/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package googlestyle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class VtnAgregarEstilosController implements Initializable {

  @FXML
  private JFXButton buscar;

  @FXML
  private JFXButton analizar;

  @FXML
  private JFXTextArea mensaje;

  @FXML
  private JFXTextField direccion;

  public static void stageController(Stage stage) {
    stage.setResizable(false);
    stage.setOnCloseRequest((WindowEvent t) -> {
      Platform.exit();
      System.exit(0);
    });
  }
 
  public void buscarAction(ActionEvent event) {
    FileChooser fc = new FileChooser();
    File archivoSeleccionado = fc.showOpenDialog(null);

    if (archivoSeleccionado != null) {
      direccion.setText(archivoSeleccionado.getAbsolutePath());
    } else {
      System.out.println("Archivo no valido");
    }
  }  

  public void analizarEstilo(ActionEvent event) {
    if (!direccion.getText().isEmpty()) {
      String dir = direccion.getText();
      String salida = "";
      System.out.println(dir + "\n");
      String command =
              "java -jar C:\\Users\\dell\\Documents\\NetBeansProjects" 
              + "\\GoogleStyle\\googlestyle\\checkstyle.jar -c " 
              + "C:\\Users\\dell\\Documents\\NetBeansProjects" 
              + "\\GoogleStyle\\googlestyle\\google_checks.xml " + dir;
      System.out.println(command);
      Process p;
      try {
        p = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(p.getInputStream()));
        String line = "";
        while ((line = in.readLine()) != null) {
          salida += line + "\n";
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      mensaje.setText(salida);
    } else {
      System.out.println("Por favor escoger un archivo java para analizar\n");
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
