package presentacion;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import business_logic.ConfigurationService;
import presentacion.views.ConfigurationView;

class ConfigurationPresenterTest {

	ConfigurationView view = ConfigurationView.getInstance();
	ConfigurationService service = Mockito.mock(ConfigurationService.class);
	ConfigurationPresenter presenter = new ConfigurationPresenter(service);
	
	@Test
	void testConfigurationPresenter() {
		assertNotNull(presenter);
		assertNotNull(service);
		assertNotNull(view);		
	}
}