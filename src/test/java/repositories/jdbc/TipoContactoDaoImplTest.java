package repositories.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.TipoContactoDTO;
import repositories.DaosFactory;
import repositories.TipoContactoDao;

class TipoContactoDaoImplTest {

	private DaosFactory factory = setup();
	private TipoContactoDao dao = factory.createTipoContactoDAO();
	
	public DaosFactory setup() {
		DataSource ds = new H2DataSource();
		DaosFactory.setFactory(new DaosFactoryImpl(ds.getConnection()));
		return DaosFactory.getFactory();
	}
	
	@AfterEach
	void clearAll() {
		for(TipoContactoDTO dto: dao.readAll()) {
			dao.deleteById(dto.getId());
		}
	}
	
	@Test
	void testTipoContactoDaoImpl() {
		assertNotNull(dao);
	}
	
	@Test
	void testInsert() {
		assertTrue(dao.insert(new TipoContactoDTO("Amigo")));
		assertTrue(dao.insert(new TipoContactoDTO("Enemigo")));
		assertTrue(dao.insert(new TipoContactoDTO("Familia")));
		Assertions.assertThrows(ConstraintViolationException.class, ()->{
			dao.insert(new TipoContactoDTO("Amigo"));
		});
		Assertions.assertThrows(ConstraintViolationException.class, ()->{
			dao.insert(new TipoContactoDTO("Enemigo"));
		});
		Assertions.assertThrows(ConstraintViolationException.class, ()->{
			dao.insert(new TipoContactoDTO("Familia"));
		});
	}

	@Test
	void testUpdate() {
		dao.insert(new TipoContactoDTO("Amigo"));
		dao.insert(new TipoContactoDTO("Enemigo"));
		TipoContactoDTO target = dao.readByName("Amigo");
		target.setNombre("Trabajo");
		assertTrue(dao.update(target));
	}

	@Test
	void testDeleteById() {
		dao.insert(new TipoContactoDTO("Amigo"));
		TipoContactoDTO target = dao.readByName("Amigo");
		assertTrue(dao.deleteById(target.getId()));
	}

	@Test
	void testReadByID() {
		dao.insert(new TipoContactoDTO("Amigo"));
		TipoContactoDTO target = dao.readByName("Amigo");
		assertNotNull(dao.readByID(target.getId()));
	}

	@Test
	void testReadAll() {
		dao.insert(new TipoContactoDTO("Escuela"));
		dao.insert(new TipoContactoDTO("Vecino"));
		assertEquals(dao.readAll().size(), 2);
	}

	@Test
	void testReadByName() {
		dao.insert(new TipoContactoDTO("NN"));
		assertNotNull(dao.readByName("NN"));
	}
}