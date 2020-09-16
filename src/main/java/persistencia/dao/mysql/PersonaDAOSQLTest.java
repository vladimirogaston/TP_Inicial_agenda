package persistencia.dao.mysql;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class PersonaDAOSQLTest {

	PersonaDAOSQL dao = new PersonaDAOSQL();
	LocalidadDaoImpl locdao = new LocalidadDaoImpl();
	TipoContactoDaoImpl tipodao = new TipoContactoDaoImpl();
	
	
	@Test
	public void testInsert() {
	}
	
	void cleanAuxDatabases() {
		List<TipoContactoDTO> dtos = tipodao.readAll();
		for(TipoContactoDTO dto : dtos) tipodao.delete(dto);
		List<LocalidadDTO> locdtos = locdao.readAll();
		for(LocalidadDTO dto : locdtos) locdao.delete(dto);
	}
	
	void cleanDatabase() {
		List<PersonaDTO> lst = dao.readAll();
		for(PersonaDTO dto: lst) dao.delete(dto);
	}
}
