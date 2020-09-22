package persistencia.dao.interfaz;

public interface DAOAbstractFactory {

	PersonaDAO createPersonaDAO();

	LocalidadDAO createLocalidadDAO();

	TipoContactoDAO createTipoContactoDAO();

	ProvinciaDAO createProvinciaDAO();

	PaisDAO createPaisDAO();
}
