package repositories.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.PaisDTO;
import dto.ProvinciaDTO;
import repositories.DaosFactory;
import repositories.ProvinciaDao;

class ProvinciaDaoImplTest {

	private DaosFactory factory = setup();
	private ProvinciaDao dao = factory.createProvinciaDAO();
	
	public DaosFactory setup() {
		DataSource ds = new H2DataSource();
		DaosFactory.setFactory(new DaosFactoryImpl(ds.getConnection()));
		return DaosFactory.getFactory();
	}
	
	@Test
	void testProvinciaDaoImpl() {
		assertNotNull(dao);
	}

	@Test
	void testInsert() {
		DaosFactory.getFactory().createPaisDAO().insert(new PaisDTO("Argentina"));
		ProvinciaDTO target = new ProvinciaDTO("Buenos Aires", "Argentina");
		ProvinciaDTO target2 = new ProvinciaDTO("Catamarca", "");
		assertTrue(dao.insert(target));
		assertTrue(dao.insert(target2));
		clearAll();
	}
	
	@Test
	void TestUpdate() {
		dao.insert(new ProvinciaDTO("Prov1", ""));
		dao.insert(new ProvinciaDTO("Pro2", ""));
		ProvinciaDTO target = dao.readByName("Prov1");
		target.setPais("");
		ProvinciaDTO target2 = dao.readByName("Pro2");
		target2.setPais("");
		target.setNombre(target2.getNombre());
		Assertions.assertThrows(ConstraintViolationException.class,()->{
				dao.update(target);
			}
		);
		clearAll();
	}
	
	void clearAll() {
		for(ProvinciaDTO prov : dao.readAll()) dao.deleteById(prov.getId());
	}
}