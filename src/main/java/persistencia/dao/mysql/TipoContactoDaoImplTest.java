package persistencia.dao.mysql;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.TipoContactoDTO;

public class TipoContactoDaoImplTest {

	TipoContactoDaoImpl dao = new TipoContactoDaoImpl();
	
	@Test 
	public void testInsert() {
		cleanDatabase();
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "Frienship")));
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "Enemy")));
	}
	
	@Test
	public void readAll() {
		cleanDatabase();
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "Frienship")));
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "Enemy")));
		Assertions.assertTrue(dao.readAll().size() == 2);
	}
	
	@Test 
	void testUpdate() {
		cleanDatabase();
		dao.insert(new TipoContactoDTO(1, "Frienship"));
		TipoContactoDTO dto = dao.readAll().get(0);
		Assertions.assertTrue(dao.update(dto));		
	}
		
	void cleanDatabase() {
		List<TipoContactoDTO> lst = dao.readAll();
		for(TipoContactoDTO tc : lst) {
			dao.delete(tc);
		}
	}
}
