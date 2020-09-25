/**
 * 
 */
package repositories.jdbc;

import repositories.DaosFactory;
import repositories.LocalidadDao;
import repositories.PaisDao;
import repositories.PersonaDao;
import repositories.ProvinciaDao;
import repositories.TipoContactoDao;

public class DaosFactoryImpl extends DaosFactory {

	PersonaDao personaDao;
	LocalidadDao localidadDao;
	PaisDao paisDao;
	ProvinciaDao provinciaDao;
	TipoContactoDao tipoDao;
	
	public DaosFactoryImpl() {
		super();
		personaDao = new PersonaDaoImpl();
		localidadDao = new LocalidadDaoImpl();
		paisDao = new PaisDaoImpl();
		provinciaDao = new ProvinciaDaoImpl();
		tipoDao = new TipoContactoDaoImpl();
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
