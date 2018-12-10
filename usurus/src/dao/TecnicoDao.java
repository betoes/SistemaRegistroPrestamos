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
import domain.TecnicoAcademico;

public class TecnicoDao implements ITecnicoDao {

	private TecnicoAcademico tecnico;

	private List<TecnicoAcademico> listaTecnico;
	private String query;
	private Connection connection;
	private String numeroPersonal;
	private String nombre;
	private String telefono;
	private String correo;
	private String entidad;

	/**
	 * Metodo para recupera las licencias de la base de datos.
	 */
	@Override
	public List<TecnicoAcademico> obtenerListaTecnico() {

		listaTecnico = new ArrayList<>();
		query = "Select * from software";
		connection = DataBase.getDataBaseConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				numeroPersonal = result.getString("numeroPersonal");
				nombre = result.getString("nombre");
				telefono = result.getString("telefono");
				correo = result.getString("correoElectronico");
				entidad = result.getString("entidadAcademico");

				tecnico = new TecnicoAcademico(numeroPersonal, nombre, telefono, correo, entidad);

				listaTecnico.add(tecnico);
			}

		} catch (SQLException ex) {
			Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DataBase.closeConnection();
		}

		return listaTecnico;
	}
}
