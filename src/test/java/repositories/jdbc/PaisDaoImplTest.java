package repositories.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import dto.PaisDTO;

public class PaisDaoImplTest {
		
	@Test
	public void testInsert() {
		DataSource ds = new MySqlDataSource();
		PaisDaoImpl dao = new PaisDaoImpl(ds.getConnection());
		assertTrue(dao != null);
		assertTrue(dao.insert(new PaisDTO("target")));
		ds.CloseConnection();
	}
}