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
import domain.Software;

/**
 * Clase que implementa los metodos de la interfaz ISoftwareDao para dar el
 * funcionamiento adecuado a la conexión con la base de datos.
 * 
 * @author José Alberto
 * @version 1.0
 */
public class SoftwareDao implements ISoftwareDao {

	private List<Software> listaSoftware;
	private String query;
	private Connection connection;
	private Software software;
	private String idSoftware;
	private String nombre;
	private String version;
	private double monto;
	private String marca;
	private String idioma;
	private String descripcion;
	private String origen;
	private String tipo;

	public SoftwareDao() {

	}

	/**
	 * Metodo para recupera las licencias de la base de datos.
	 */
	@Override
	public List<Software> obtenerSoftwares() {

		listaSoftware = new ArrayList<>();
		query = "Select * from software";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				idSoftware = result.getString("idSoftware");
				nombre = result.getString("nombre");
				version = result.getString("version");
				monto = result.getDouble("monto");
				marca = result.getString("marca");
				idioma = result.getString("idioma");
				descripcion = result.getString("descripcion");
				origen = result.getString("origen");
				tipo = result.getString("tipo");

				software = new Software(idSoftware, nombre, version, monto, marca, idioma, descripcion, origen, tipo);

				listaSoftware.add(software);
			}

		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return listaSoftware;
	}

	/**
	 * Metodo para recupera una licencia de la base de datos.
	 * 
	 * @param id de la licencia a recuperar
	 */
	@Override
	public Software obtenerSoftware(String nombreSoftware) {

		query = "Select * from software where nombre = ?";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nombreSoftware);

			ResultSet result = statement.executeQuery();
			result.next();

			idSoftware = result.getString("idSoftware");
			nombre = result.getString("nombre");
			version = result.getString("version");
			monto = result.getDouble("monto");
			marca = result.getString("marca");
			idioma = result.getString("idioma");
			descripcion = result.getString("descripcion");
			origen = result.getString("origen");
			tipo = result.getString("tipo");

			software = new Software(idSoftware, nombre, version, monto, marca, idioma, descripcion, origen, tipo);

		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return software;
	}

	public String obtenerLicenciaSoftware(String softwareId) {
		String licencia = "";
		query = "Select Licencia_idLicencia from software where idSoftware = ?";
		connection = DataBase.getDataBaseConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, softwareId);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				licencia = result.getString("Licencia_idLicencia");
			}

		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}
		return licencia;

	}

	@Override
	public List<Software> obtenerSoftwarePorNombre(String nombre) {
		listaSoftware = new ArrayList<>();
		query = "select * from software where nombre LIKE ?";

		connection = DataBase.getDataBaseConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + nombre + "%");

			ResultSet result = statement.executeQuery();
			while (result.next()) {

				idSoftware = result.getString("idSoftware");
				nombre = result.getString("nombre");
				version = result.getString("version");
				monto = result.getDouble("monto");
				marca = result.getString("marca");
				idioma = result.getString("idioma");
				descripcion = result.getString("descripcion");
				origen = result.getString("origen");
				tipo = result.getString("tipo");

				software = new Software(idSoftware, nombre, version, monto, marca, idioma, descripcion, origen, tipo);

				listaSoftware.add(software);
			}

		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return listaSoftware;
	}

	/**
	 * Metodo para agregar una software a la base de datos.
	 * 
	 * @param software objeto de tipo Software y idLicencia de tipo String
	 */
	@Override
	public boolean agregarSoftware(Software software, String idLicencia) {

		boolean agregado = false;
		query = "insert into software values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, software.getIdSoftware());
			statement.setDouble(2, software.getMonto());
			statement.setString(3, software.getNombre());
			statement.setString(4, software.getVersion());
			statement.setString(5, software.getMarca());
			statement.setString(6, software.getIdioma());
			statement.setString(7, software.getOrigen().toLowerCase());
			statement.setString(8, software.getTipo().toLowerCase());
			statement.setString(9, software.getDescripcion());
			statement.setString(10, idLicencia);

			statement.executeUpdate();
			agregado = true;
		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}
		return agregado;
	}

	/**
	 * Metodo para modificar una licencia de la base de datos.
	 * 
	 * @param licencia objeto de tipo licencia
	 */
	@Override
	public boolean modificarSoftware(Software software, String idLicencia) {

		boolean editado = false;
		query = "UPDATE software set nombre = ?, version = ?, monto = ?, marca = ?,"
				+ " idioma = ?, descripcion = ?, origen = ?, tipo = ?, Licencia_idLicencia = ? where idSoftware = ?";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, software.getNombre());
			statement.setString(2, software.getVersion());
			statement.setDouble(3, software.getMonto());
			statement.setString(4, software.getMarca());
			statement.setString(5, software.getIdioma());
			statement.setString(6, software.getDescripcion());
			statement.setString(7, software.getOrigen());
			statement.setString(8, software.getTipo());
			statement.setString(9, idLicencia);
			statement.setString(10, software.getIdSoftware());

			statement.executeUpdate();

			editado = true;
		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return editado;
	}

	/**
	 * metodo para eliminar una licencia de la base de datos.
	 * 
	 * @param id de la licencia a eliminar
	 */
	@Override
	public boolean eliminarSoftware(String id) {

		boolean borrado = false;
		query = "Delete from software where idSoftware = ?";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);

			statement.executeUpdate();

			borrado = true;
		} catch (SQLException ex) {
			Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return borrado;
	}

	/**
	 * Metodo para verificar si existe una licencia.
	 * 
	 * @param id de la licencia
	 */
	@Override
	public boolean existe(String id) {

		boolean existe = false;
		query = "Select * from software where idSoftware = ?";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);

			ResultSet result = statement.executeQuery();

			existe = result.next();

		} catch (SQLException ex) {
			Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return existe;
	}

}
