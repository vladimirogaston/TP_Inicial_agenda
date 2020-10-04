package repositories.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import dto.PaisDTO;
import repositories.DaosFactory;
import repositories.PaisDao;

public class PaisDaoImplTest {
		
	private DaosFactory factory = setup();
	private PaisDao dao = factory.createPaisDAO();
	
	public DaosFactory setup() {
		DataSource ds = new H2DataSource();
		DaosFactory.setFactory(new DaosFactoryImpl(ds.getConnection()));
		return DaosFactory.getFactory();
	}
	
	@Test
	public void testNotNull() {
		assertNotNull(factory);
		assertNotNull(dao);
	}
	
	@Test
	public void testInsertNotFails() {
		assertTrue(dao.insert(new PaisDTO("Peru")));
		assertTrue(dao.insert(new PaisDTO("Argentina")));
		assertTrue(dao.insert(new PaisDTO("Chile")));
		PaisDTO target = PaisDTO.makeTestDTO();
		dao.insert(target);
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			dao.insert(target);
		});
	}
	
	@Test
	public void testUpdate() {
		dao.insert(new PaisDTO("Italy"));
		PaisDTO target =  dao.readByName("Italy");
		target.setNombre("Russia");
		dao.update(target);
		assertNotNull(dao.readByName("Russia"));
		assertNull(dao.readByName("Italy"));
		assertEquals(dao.readByName("Russia").getNombre(), "Russia");
	}
		
	@Test
	public void testReadAll() {
		dao.insert(new PaisDTO("Uruguay"));
		dao.insert(new PaisDTO("Paraguay"));
		dao.insert(new PaisDTO("Colombia"));
		assertEquals(dao.readAll().size(), 3);
		assertNotNull(dao.readByName("Uruguay").getId());
		assertNotNull(dao.readByName("Paraguay"));
		assertNull(dao.readByName("Peru"));
		assertNull(dao.readByName("Chile"));
	}
	
	@Test
	public void testUpdateFails() {
		PaisDTO dtoa =  new PaisDTO("Shark");
		PaisDTO dtob = new PaisDTO("York");
		assertTrue(dao.insert(dtoa));
		assertTrue(dao.insert(dtob));
		PaisDTO target = dao.readByName("Shark");
		target.setNombre("York");
		Assertions.assertThrows(ConstraintViolationException.class, ()->{
			dao.update(target);
		});
		for(PaisDTO dto : dao.readAll()) dao.deleteById(dto.getId());
	}
}