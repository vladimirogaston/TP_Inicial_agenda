package presentacion.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class PersonaView extends JDialog {

	static final long serialVersionUID = 1L;
	static PersonaView INSTANCE;
	private JPanel contentPane;
	
	private Integer personaId;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textCalle;
	private JTextField textPiso;
	private JTextField textFieldAltura;
	private JTextField textDpto;
	private JComboBox<String> comboBoxLocalidad;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxTipoContacto;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<String> comboBoxPais;
	private JTextField textEquipo;
	private JTextField textFieldCodigoPostal;
	private JButton btnAgregarPersona;
		
	public static PersonaView getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PersonaView();
		}
		return INSTANCE;
	}

	private PersonaView() {
		super();

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 385, 576);
		setTitle("Agregar Contacto");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 359, 516);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("* Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 157, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("* Telefono");
		lblTelfono.setBounds(10, 42, 113, 14);
		panel.add(lblTelfono);

		textNombre = new JTextField("");
		textNombre.setBounds(169, 8, 164, 24);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textTelefono = new JTextField("");
		textTelefono.setBounds(169, 39, 164, 24);
		panel.add(textTelefono);
		textTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(220, 469, 113, 35);
		panel.add(btnAgregarPersona);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 72, 113, 15);
		panel.add(lblEmail);

		textEmail = new JTextField("");
		textEmail.setBounds(169, 69, 164, 24);
		panel.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblCumpleaos = new JLabel("Fecha de Cumpleaños");
		lblCumpleaos.setBounds(10, 110, 193, 15);
		panel.add(lblCumpleaos);

		JLabel lblTipoContacto = new JLabel("Tipo contacto");
		lblTipoContacto.setBounds(10, 141, 113, 15);
		panel.add(lblTipoContacto);

		comboBoxTipoContacto = new JComboBox<String>();
		comboBoxTipoContacto.setBounds(169, 136, 164, 24);
		panel.add(comboBoxTipoContacto);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 289, 70, 15);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 326, 70, 15);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(156, 372, 70, 15);
		panel.add(lblPiso);

		textCalle = new JTextField("");
		textCalle.setBounds(74, 286, 260, 24);
		panel.add(textCalle);
		textCalle.setColumns(10);

		textPiso = new JTextField("");
		textPiso.setBounds(249, 367, 84, 24);
		panel.add(textPiso);
		textPiso.setColumns(10);

		textFieldAltura = new JTextField("");
		textFieldAltura.setBounds(60, 323, 63, 24);
		panel.add(textFieldAltura);
		textFieldAltura.setColumns(10);

		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(10, 370, 70, 15);
		panel.add(lblDpto);

		textDpto = new JTextField("");
		textDpto.setBounds(49, 367, 95, 24);
		panel.add(textDpto);
		textDpto.setColumns(10);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 246, 70, 15);
		panel.add(lblLocalidad);

		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(169, 241, 164, 24);
		panel.add(comboBoxLocalidad);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(169, 100, 164, 25);
		panel.add(dateChooser);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 211, 70, 15);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setBounds(169, 206, 164, 24);
		panel.add(comboBoxProvincia);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setBounds(169, 171, 164, 24);
		panel.add(comboBoxPais);
		
		JLabel lblPas = new JLabel("País");
		lblPas.setBounds(10, 176, 70, 15);
		panel.add(lblPas);
		
		JLabel lblNewLabel = new JLabel("Equipo de Futbol");
		lblNewLabel.setBounds(10, 420, 169, 20);
		panel.add(lblNewLabel);
		
		textEquipo = new JTextField();
		textEquipo.setBounds(207, 417, 125, 26);
		panel.add(textEquipo);
		textEquipo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo postal");
		lblNewLabel_1.setBounds(133, 323, 113, 20);
		panel.add(lblNewLabel_1);
		
		textFieldCodigoPostal = new JTextField();
		textFieldCodigoPostal.setBounds(247, 320, 86, 24);
		panel.add(textFieldCodigoPostal);
		textFieldCodigoPostal.setColumns(10);
		
		JLabel lblCampoObligatorio = new JLabel("(*) Campos obligatorios");
		lblCampoObligatorio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCampoObligatorio.setForeground(new Color(220, 20, 60));
		lblCampoObligatorio.setBounds(10, 451, 287, 14);
		panel.add(lblCampoObligatorio);

		this.setResizable(false);
		this.setVisible(false);
		this.setModal(true);
	}
	
	public void clearData() {
		personaId = null;
		textNombre.setText("");
		textTelefono.setText("");
		textEmail.setText("");
		textCalle.setText("");
		textFieldAltura.setText("");
		textPiso.setText("");
		textDpto.setText("");
		comboBoxLocalidad.removeAllItems();
		comboBoxProvincia.removeAllItems();
		comboBoxPais.removeAllItems();
		textEquipo.setText("");
		textFieldCodigoPostal.setText("");
		comboBoxTipoContacto.removeAllItems();
		dateChooser.setDate(null);
	}
	
	public PersonaDTO getData() {
		Object loc = comboBoxLocalidad.getSelectedItem();
		String locs = loc != null ? loc.toString() : "";
		Object prov = comboBoxProvincia.getSelectedItem();
		String provs = prov != null ? prov.toString() : "";
		Object pais = comboBoxPais.getSelectedItem();
		String paiss = pais != null ? pais.toString() : "";
		return new PersonaDTO
				.Builder(textNombre.getText(), textTelefono.getText())
				.email(textEmail.getText())
				.id(personaId)
				.fechaNacimiento(dateChooser.getDate())
				.tipoContacto(comboBoxTipoContacto.getSelectedItem().toString())
				.calle(textCalle.getText())
				.altura(textFieldAltura.getText())
				.piso(textPiso.getText())
				.dpto(textDpto.getText())
				.localidad(locs)
				.provincia(provs)
				.codigoPostal(textFieldCodigoPostal.getText())
				.equipoFutbol(textEquipo.getText())
				.pais(paiss)
				.build();
	}
	
	public void setData(TipoContactoDTO [] tipos) {
		assert tipos != null;
		comboBoxTipoContacto.removeAllItems();
		comboBoxTipoContacto.addItem("");
		for (TipoContactoDTO tc : tipos) {
			comboBoxTipoContacto.addItem(tc.getNombre());
		}
	}
	
	public void setData(ProvinciaDTO [] provincias) {
		assert provincias != null;
		comboBoxProvincia.removeAllItems();
		comboBoxProvincia.addItem("");
		for(ProvinciaDTO prov: provincias) {
			comboBoxProvincia.addItem(prov.getNombre());
		}
	}
	
	public void setData(LocalidadDTO [] localidades) {
		assert localidades != null;
		comboBoxLocalidad.removeAllItems();
		comboBoxLocalidad.addItem("");
		for (LocalidadDTO loc : localidades) {
			comboBoxLocalidad.addItem(loc.getNombre());
		}
	}
	
	public void setData(PaisDTO [] paises) {
		assert paises != null;
		comboBoxPais.removeAllItems();
		comboBoxPais.addItem("");
		for(PaisDTO pais: paises) {
			comboBoxPais.addItem(pais.getNombre());
		}
	}
	
	public void setData(PersonaDTO persona) {
		personaId = persona.getId();
		textNombre.setText(persona.getNombre()); 
		textTelefono.setText(persona.getTelefono());
		textEmail.setText(persona.getEmail());
		dateChooser.setDate(persona.getFechaNacimiento());
		textCalle.setText(persona.getCalle());
		textFieldAltura.setText(persona.getAltura());
		textPiso.setText(persona.getPiso());
		textDpto.setText(persona.getDpto());
		textFieldCodigoPostal.setText(persona.getCodigoPostal());
		textEquipo.setText(persona.getEquipoFutbol());
		comboBoxPais.setSelectedItem(persona.getPais());
		comboBoxProvincia.setSelectedItem(persona.getProvincia());		
		comboBoxTipoContacto.setSelectedItem(persona.getTipoContacto());
		comboBoxLocalidad.setSelectedItem(persona.getLocalidad());
	}
		
	public String getNombreProvinciaSeleccionada() {
		Object item = comboBoxProvincia.getSelectedItem();
		return item != null ? item.toString() : "";
	}
	
	public String getNombrePaisSeleccionado() {
		Object item = comboBoxPais.getSelectedItem();
		return item != null ? item.toString() : "";
	}
	
	public void setActionSave(ActionListener listener) {
		assert listener != null;
		btnAgregarPersona.addActionListener(listener);
	}
	
	public void setActionProvinciaSelect(ActionListener listener) {
		assert listener != null;
		comboBoxProvincia.addActionListener(listener);
	}
	
	public void setActionPaisSelect(ActionListener listener) {
		assert listener != null;
		comboBoxPais.addActionListener(listener);
	}
	
	public void open() {
		setVisible(true);
	}
	
	public void close() {
		setVisible(false);
	}
}