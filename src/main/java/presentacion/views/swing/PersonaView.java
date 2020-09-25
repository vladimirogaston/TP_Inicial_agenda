package presentacion.views.swing;

import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JDateChooser;

public class PersonaView extends JDialog {

	static final long serialVersionUID = 1L;
	static PersonaView INSTANCE;
	JPanel contentPane;
	private Integer personaId;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField textFieldEmail;
	private JTextField textField_1Calle;
	private JTextField textFieldPiso;
	private JTextField textFieldAltura;
	private JTextField textFieldDpto;
	private JComboBox<String> comboBoxLocalidad;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxTipoContacto;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<String> comboBoxPais;
	private JTextField textFieldEquipo;
	private JTextField textFieldCodigoPostal;
	private JButton btnAgregarPersona;
		
	public static PersonaView getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PersonaView();
			return new PersonaView();
		}
		return INSTANCE;
	}

	private PersonaView() {
		super();

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 343, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 604);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField("");
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField("");
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(182, 557, 113, 23);
		panel.add(btnAgregarPersona);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 89, 113, 15);
		panel.add(lblEmail);

		textFieldEmail = new JTextField("");
		textFieldEmail.setBounds(133, 81, 164, 19);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblCumpleaos = new JLabel("Cumpleaños");
		lblCumpleaos.setBounds(12, 122, 124, 15);
		panel.add(lblCumpleaos);

		JLabel lblTipoContacto = new JLabel("Tipo contacto");
		lblTipoContacto.setBounds(10, 203, 124, 15);
		panel.add(lblTipoContacto);

		comboBoxTipoContacto = new JComboBox<String>();
		comboBoxTipoContacto.setBounds(133, 198, 164, 24);
		panel.add(comboBoxTipoContacto);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 347, 70, 15);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 383, 70, 15);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(135, 383, 70, 15);
		panel.add(lblPiso);

		textField_1Calle = new JTextField("");
		textField_1Calle.setBounds(74, 345, 223, 19);
		panel.add(textField_1Calle);
		textField_1Calle.setColumns(10);

		textFieldPiso = new JTextField("");
		textFieldPiso.setBounds(196, 381, 101, 19);
		panel.add(textFieldPiso);
		textFieldPiso.setColumns(10);

		textFieldAltura = new JTextField("");
		textFieldAltura.setBounds(75, 381, 48, 19);
		panel.add(textFieldAltura);
		textFieldAltura.setColumns(10);

		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(10, 418, 70, 15);
		panel.add(lblDpto);

		textFieldDpto = new JTextField("");
		textFieldDpto.setBounds(74, 416, 53, 19);
		panel.add(textFieldDpto);
		textFieldDpto.setColumns(10);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 500, 70, 15);
		panel.add(lblLocalidad);

		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(133, 495, 164, 24);
		panel.add(comboBoxLocalidad);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(135, 120, 159, 26);
		panel.add(dateChooser);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 452, 70, 15);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setBounds(133, 447, 162, 24);
		panel.add(comboBoxProvincia);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setBounds(133, 162, 162, 24);
		panel.add(comboBoxPais);
		
		JLabel lblPas = new JLabel("País");
		lblPas.setBounds(10, 167, 70, 15);
		panel.add(lblPas);
		
		JLabel lblNewLabel = new JLabel("Equipo");
		lblNewLabel.setBounds(11, 248, 107, 20);
		panel.add(lblNewLabel);
		
		textFieldEquipo = new JTextField();
		textFieldEquipo.setBounds(133, 248, 164, 26);
		panel.add(textFieldEquipo);
		textFieldEquipo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo postal");
		lblNewLabel_1.setBounds(10, 294, 113, 20);
		panel.add(lblNewLabel_1);
		
		textFieldCodigoPostal = new JTextField();
		textFieldCodigoPostal.setBounds(133, 291, 164, 26);
		panel.add(textFieldCodigoPostal);
		textFieldCodigoPostal.setColumns(10);

		this.setResizable(false);
		this.setVisible(false);
		this.setModal(true);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}
	
	public String getFieldEmail() {
		return textFieldEmail.getText();
	}

	public Date getFieldFechaDeCumpleaños() {
		return (Date) dateChooser.getDate();
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public JTextField getTextField_1Calle() {
		return textField_1Calle;
	}

	public JTextField getTextFieldPiso() {
		return textFieldPiso;
	}

	public JTextField getTextFieldAltura() {
		return textFieldAltura;
	}

	public JTextField getTextFieldDpto() {
		return textFieldDpto;
	}

	public JComboBox<String> getComboBoxLocalidad() {
		return comboBoxLocalidad;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JComboBox<String> getComboBoxTipoContacto() {
		return comboBoxTipoContacto;
	}

	public JComboBox<String> getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public JTextField getTextFieldEquipo() {
		return textFieldEquipo;
	}

	public JTextField getTextFieldCodigoPostal() {
		return textFieldCodigoPostal;
	}

	public void setPersonaId(Integer id) {
		this.personaId = id;		
	}
}