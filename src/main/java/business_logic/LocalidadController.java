package business_logic;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadController extends Controller<LocalidadDTO>{
	
	List<LocalidadDTO> readByProvincia(String provincia);
}
