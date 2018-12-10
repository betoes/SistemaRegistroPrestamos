package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.DictamenDao;
import dao.IDictamenDao;
import domain.Dictamen;
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

public class PantallaMantenimientoControlador implements Initializable {

	@FXML
	private TableView<Dictamen> tbMantenimiento;

	@FXML
	private Button bAgregar;

	@FXML
	private Button bEliminar;

	@FXML
	private Button bImprimir;

	@FXML
	private Button bBuscar;

	@FXML
	private Button bSalir;

	@FXML
	private TextField txtBusqueda;

	/*
	 * Metodo para cargar pantalla Registrar Mantenimiento
	 */
	@FXML
	public void cargarPantallaRegistrar() {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("PantallaRegistrar.fxml"));
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
			closeButtonAction();

		} catch (IOException ex) {
			Logger.getLogger(PantallaSoftwareControlador.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * Metodo para Eliminar un registro en la tabla de Software
	 */
	@FXML
	public void eliminarRegistroSeleccionado() {
		DictamenDao dictamendao = new DictamenDao();
		Dictamen dictamenSeleccionado;
		dictamenSeleccionado = tbMantenimiento.getSelectionModel().getSelectedItem();
		if (dictamenSeleccionado != null) {
			dictamendao.eliminarDictamen(dictamenSeleccionado.getIdDictamen());
			desplegarTabla();
		}
	}

	/*
	 * Metodo para buscar un Registro por su nombre
	 */
	@FXML
	public void buscarPorNombre() {
		IDictamenDao dictamenDao = new DictamenDao();
		ObservableList<Dictamen> listaObservable = FXCollections
				.observableArrayList(dictamenDao.obtenerDictamen(txtBusqueda.getText()));
		tbMantenimiento.setItems(listaObservable);
		tbMantenimiento.setDisable(false);
	}

	/**
	 * Metodo para cargar la pantalla main
	 */
	@FXML
	public void cargarPantallaMain() {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("pantallaMain.fxml"));
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
			closeButtonAction();

		} catch (IOException ex) {
			Logger.getLogger(PantallaLicenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * Metodo para cerrar Ventana
	 */
	@FXML
	private void closeButtonAction() {

		Stage stage = (Stage) bSalir.getScene().getWindow();

		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desplegarTabla();
	}

	ObservableList<Dictamen> desplegarTabla() {

		IDictamenDao dictamenDao = new DictamenDao();
		ObservableList<Dictamen> listaObservable = FXCollections
				.observableArrayList(dictamenDao.obtenerListaDictamen());
		tbMantenimiento.setItems(listaObservable);
		tbMantenimiento.setDisable(false);

		return listaObservable;
	}

}
