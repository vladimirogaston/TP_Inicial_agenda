/**
 * 
 */
package repositories.jdbc;

import java.sql.Connection;

import repositories.DaosFactory;
import repositories.LocalidadDao;
import repositories.PaisDao;
import repositories.PersonaDao;
import repositories.ProvinciaDao;
import repositories.TipoContactoDao;

public class DaosFactoryImpl extends DaosFactory {

	private PersonaDao personaDao;
	private LocalidadDao localidadDao;
	private PaisDao paisDao;
	private ProvinciaDao provinciaDao;
	private TipoContactoDao tipoDao;
	
	public DaosFactoryImpl(Connection connection) {
		personaDao = new PersonaDaoImpl(connection);
		localidadDao = new LocalidadDaoImpl(connection);
		paisDao = new PaisDaoImpl(connection);
		provinciaDao = new ProvinciaDaoImpl(connection);
		tipoDao = new TipoContactoDaoImpl(connection);
	}
	
	@Override
	public PersonaDao createPersonaDAO() {
		return personaDao;
	}

	@Override
	public LocalidadDao createLocalidadDAO() {
		return localidadDao;
	}

	@Override
	public TipoContactoDao createTipoContactoDAO() {
		return tipoDao;
	}

	@Override
	public ProvinciaDao createProvinciaDAO() {
		return provinciaDao;
	}

	@Override
	public PaisDao createPaisDAO() {
		return paisDao;
	}
}
