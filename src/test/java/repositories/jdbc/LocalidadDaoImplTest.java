package repositories.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import repositories.DaosFactory;
import repositories.LocalidadDao;

class LocalidadDaoImplTest {

	private DaosFactory factory = setup();
	private LocalidadDao dao = factory.createLocalidadDAO();
	
	public DaosFactory setup() {
		DataSource ds = new H2DataSource();
		DaosFactory.setFactory(new DaosFactoryImpl(ds.getConnection()));
		return DaosFactory.getFactory();
	}
	
	@AfterEach
	void clearAll() {
		for(LocalidadDTO dto: dao.readAll()) {
			dao.deleteById(dto.getId());
		}
	}
	
	@Test
	void testLocalidadDaoImpl() {
		assertNotNull(dao);
	}

	@Test
	void testInsert() {
		ProvinciaDTO provincia = new ProvinciaDTO("Buenos Aires", "");
		DaosFactory.getFactory().createProvinciaDAO().insert(provincia);
		assertTrue(dao.insert(new LocalidadDTO(null, "Villa de Mayo", provincia.getNombre())));
		assertTrue(dao.insert(new LocalidadDTO(null, "Martinez", provincia.getNombre())));
		assertTrue(dao.insert(new LocalidadDTO(null, "San Nicolas", provincia.getNombre())));
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			dao.insert(new LocalidadDTO(null, "Martinez", provincia.getNombre()));
		});
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			dao.insert(new LocalidadDTO(null, "Villa de Mayo", provincia.getNombre()));
		});
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			dao.insert(new LocalidadDTO(null, "San Nicolas", provincia.getNombre()));
		});
		for(LocalidadDTO dto: dao.readAll()) {
			dao.deleteById(dto.getId());
		}
	}
	
	@Test
	void testUpdate() {
		ProvinciaDTO provincia = new ProvinciaDTO("Tucuman", "");
		DaosFactory.getFactory().createProvinciaDAO().insert(provincia);
		dao.insert(new LocalidadDTO(null, "Villa de Mayo", provincia.getNombre()));
		dao.insert(new LocalidadDTO(null, "Martinez", provincia.getNombre()));
		
		assertNotNull(dao.readByName("Martinez"));
		assertNotNull(dao.readByName("Villa de Mayo"));
		
		LocalidadDTO target = dao.readByName("Martinez");
		target.setNombre("Villa de Mayo");
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			dao.update(target);
		});
	}
}