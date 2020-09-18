package persistencia.dao.mysql;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.TipoContactoDTO;

public class TipoContactoDaoImplTest {

	TipoContactoDaoImpl dao = new TipoContactoDaoImpl();

	@Test
	public void testInsert() {
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "t000")));
		Assertions.assertTrue(dao.insert(new TipoContactoDTO(1, "t0001")));
	}

	@Test
	public void readAll() {
		dao.insert(new TipoContactoDTO(1, "t002"));
		dao.insert(new TipoContactoDTO(1, "t003"));
	}

	@Test
	void testUpdate() {
		dao.insert(new TipoContactoDTO(1, "t004"));
		TipoContactoDTO dto = dao.readAll().get(0);
		Assertions.assertTrue(dao.update(dto));
	}

	void cleanDatabase() {
		List<TipoContactoDTO> lst = dao.readAll();
		for (TipoContactoDTO tc : lst) {
			dao.delete(tc);
		}
	}
}
