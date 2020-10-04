package business_logic;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaController extends Controller<ProvinciaDTO, Integer> {
	
	List<ProvinciaDTO> readByPais(String pais);
}
