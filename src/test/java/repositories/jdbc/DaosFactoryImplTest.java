package repositories.jdbc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import repositories.DaosFactory;

public class DaosFactoryImplTest {

	DaosFactory factory;
	
	@Before
	public void setUp() {
		DaosFactory.setFactory(new DaosFactoryImpl());
		factory = DaosFactory.getFactory();
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(factory);
	}
	
	@Test
	public void testCreatePersonaDAO() {
		assertNotNull(factory.createPersonaDAO());
	}

	@Test
	public void testCreateLocalidadDAO() {
		assertNotNull(factory.createLocalidadDAO());
	}

	@Test
	public void testCreateTipoContactoDAO() {
		assertNotNull(factory.createTipoContactoDAO());
	}

	@Test
	public void testCreateProvinciaDAO() {
		assertNotNull(factory.createProvinciaDAO());
	}

	@Test
	public void testCreatePaisDAO() {
		assertNotNull(factory.createPaisDAO());
	}
}
