package dto;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonaDTOTest {

	@Test
	public void testGetNombre() {
		PersonaDTO dto = new PersonaDTO.Builder("xx", "33").build();
		assertNotNull(dto.getNombre());
	}
}
