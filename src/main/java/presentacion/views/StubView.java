package presentacion.views;

import business_logic.TipoController;
import dto.TipoContactoDTO;

public class StubView {
	
	TipoController controller;
	
	public StubView(TipoController controller) {
		this.controller = controller;
	}
	
	public void excecute() {
		controller.save(new TipoContactoDTO("Lorens"));
		for(TipoContactoDTO dto : controller.readAll()) {
			if(dto.getNombre().contentEquals("Lorens")) {
				controller.delete(dto.getId());
			}
		}
	}
}
