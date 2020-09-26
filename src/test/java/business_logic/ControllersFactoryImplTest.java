package business_logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import business_logic.local.ControllersFactoryImpl;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;

public class ControllersFactoryImplTest {
	
	@Before
	public void setUp() {
		DaosFactory.setFactory(new DaosFactoryImpl());
		ControllersFactory.setDaosFactory(DaosFactory.getFactory());
		ControllersFactory.setFactory(new ControllersFactoryImpl());
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(ControllersFactory.getFactory());
	}
	
	@Test
	public void testGetPersonaController() {
		assertNotNull(ControllersFactory.getFactory());
	}

	@Test
	public void testGetLocalidadController() {
		assertNotNull(ControllersFactory.getFactory().getLocalidadController());
	}

	@Test
	public void testGetProvinciaController() {
		assertNotNull(ControllersFactory.getFactory().getProvinciaController());
	}

	@Test
	public void testGetPaisController() {
		assertNotNull(ControllersFactory.getFactory().getPaisController());
	}

	@Test
	public void testGetTipoController() {
		assertNotNull(ControllersFactory.getFactory().getTipoController());
	}
}
