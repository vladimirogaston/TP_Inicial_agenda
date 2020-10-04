package presentacion.views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

class PersonaViewTest {

	PersonaView view = PersonaView.getInstance();	
	
	@Test
	void testClearData() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		view.setData(target);
		view.clearData();
		assertTrue(view.getData().getNombre().isBlank());
		assertTrue(view.getData().getTelefono().isBlank());
		assertTrue(view.getData().getPais().isBlank());
	}

	@Test
	void testGetData() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		view.setData(target);
		assertNotEquals(view.getData(),target);
	}

	@Test
	void testSetDataTipoContactoDTOArray() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		TipoContactoDTO [] tipos = { new TipoContactoDTO(target.getTipoContacto()) };
		view.setData(tipos);
		view.setData(target);
		assertEquals(target.getTipoContacto(), view.getData().getTipoContacto());
	}

	@Test
	void testSetDataProvinciaDTOArray() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		PaisDTO [] paises = { new PaisDTO(target.getPais())};
		ProvinciaDTO [] provincias = { new ProvinciaDTO(target.getProvincia(), target.getPais()) };
		view.setData(paises);
		view.setData(provincias);
		view.setData(target);
		assertEquals(target.getPais(), view.getData().getPais());
		assertEquals(target.getProvincia(), view.getData().getProvincia());
	}

	@Test
	void testSetDataLocalidadDTOArray() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		LocalidadDTO [] locs = {new LocalidadDTO(1, target.getLocalidad(), target.getProvincia())};
		view.setData(locs);
		view.setData(target);
		assertEquals(view.getData().getLocalidad(), target.getLocalidad());
	}

	@Test
	void testSetDataPaisDTOArray() {
		PersonaDTO target = PersonaDTO.makeTestDto();
		PaisDTO [] paises = { new PaisDTO(target.getPais())};
		view.setData(paises);
		view.setData(target);
		assertEquals(target.getPais(), view.getData().getPais());
	}
}
