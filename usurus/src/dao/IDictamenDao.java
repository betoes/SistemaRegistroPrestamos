package dao;

import java.util.List;

import domain.Dictamen;

public interface IDictamenDao {

	List<Dictamen> obtenerDictamen(String id);

	List<Dictamen> obtenerListaDictamen();

	boolean registrarDictamen(Dictamen dictamen, String idHardware, String idTecnico);

	boolean eliminarDictamen(String id);

}
