package presentacion.views;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dto.TipoContactoDTO;

class TiposViewTest {

	TiposView view = TiposView.getInstance();
	
	@Test
	void testGetInstance() {
		assertNotNull(view);
	}

	@Test
	void testSetData() {
		view.setData(Arrays.asList(new TipoContactoDTO [] {
			new TipoContactoDTO("Friend"),
			new TipoContactoDTO("Enemy"),
			new TipoContactoDTO("Work")
		}));
		assertEquals(view.getTable().getRowCount(), 3);
	}

	@Test
	void testClearData() {
		view.setData(Arrays.asList(new TipoContactoDTO [] {
				new TipoContactoDTO("Friend"),
				new TipoContactoDTO("Enemy"),
				new TipoContactoDTO("Work")
			}));
		view.clearData();
		assertEquals(view.getTable().getRowCount(), 0);
	}
}
