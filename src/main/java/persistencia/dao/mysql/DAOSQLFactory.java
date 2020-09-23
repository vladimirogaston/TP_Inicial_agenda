/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	public PersonaDAO createPersonaDAO() {
		return new PersonaDAOSQL();
	}

	@Override
	public LocalidadDAO createLocalidadDAO() {
		return new LocalidadDaoImpl();
	}

	@Override
	public TipoContactoDAO createTipoContactoDAO() {
		return new TipoContactoDaoImpl();
	}

	@Override
	public ProvinciaDAO createProvinciaDAO() {
		// TODO Auto-generated method stub
		return new ProvinciaDAOImpl();
	}

	@Override
	public PaisDAO createPaisDAO() {
		return new PaisDAOImpl();
	}
}
