package business_logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import business_logic.ControllersFactoryImpl;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;

public class ControllersFactoryImplTest {

	@Before
	public void setUp() {
		DaosFactory.setFactory(new DaosFactoryImpl());
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(ControllersFactoryImpl.getInstance());
	}
	
	@Test
	public void testGetPersonaController() {
		assertNotNull(ControllersFactoryImpl.getInstance().getPersonaController());
	}

	@Test
	public void testGetLocalidadController() {
		assertNotNull(ControllersFactoryImpl.getInstance().getLocalidadController());
	}

	@Test
	public void testGetProvinciaController() {
		assertNotNull(ControllersFactoryImpl.getInstance().getProvinciaController());
	}

	@Test
	public void testGetPaisController() {
		assertNotNull(ControllersFactoryImpl.getInstance().getPaisController());
	}

	@Test
	public void testGetTipoController() {
		assertNotNull(ControllersFactoryImpl.getInstance().getTipoController());
	}
}
