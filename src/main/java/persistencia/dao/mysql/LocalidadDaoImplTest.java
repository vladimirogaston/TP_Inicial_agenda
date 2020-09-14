package persistencia.dao.mysql;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import dto.LocalidadDTO;

public class LocalidadDaoImplTest {

	LocalidadDaoImpl dao = new LocalidadDaoImpl();
	
	@Test
	public void testReadAll() {
		cleanDatabase();
		dao.insert(new LocalidadDTO(1, "Texas"));
		Assertions.assertTrue(dao.insert(new LocalidadDTO(1, "Johanesburg")));
		List<LocalidadDTO> lst = dao.readAll();
		Assertions.assertTrue(!lst.isEmpty());
		Assertions.assertTrue(lst.size() == 2);
	}
	
	@Test
	public void testInsert() {
		cleanDatabase();
		LocalidadDTO dto = new LocalidadDTO(1, "Texas");
		Assertions.assertTrue(dao.insert(dto));
		Assertions.assertTrue(dao.insert(new LocalidadDTO(1, "Missippi")));
		Assertions.assertTrue(dao.insert(new LocalidadDTO(1, "Atlanta")));
	}
	
	@Test
	public void testUpdate() {
		cleanDatabase();
		dao.insert(new LocalidadDTO(1, "Texas"));
		LocalidadDTO ret = dao.readAll().get(0);
		ret.setNombre("Exodus");
		Assertions.assertTrue(dao.update(ret));
	}
	
	void cleanDatabase() {
		List<LocalidadDTO> lst = dao.readAll();
		if(!lst.isEmpty()) {
			for(LocalidadDTO loc : lst) {
				dao.delete(loc);
			}
		} 
	}
}
