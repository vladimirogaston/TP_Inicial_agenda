package presentacion.views.swing;

import java.awt.Color;
import java.awt.Font;
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
	private JPanel contentPane;
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
	private JButton btnAgregarPersona;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<String> comboBoxPais;
	private JTextField textFieldEquipo;
	private JTextField textFieldCodigoPostal;

	public static PersonaView getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PersonaView();
		}
		return INSTANCE;
	}

	private PersonaView() {
		super();

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 334, 576);
		setTitle("Agregar Contacto");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 523);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("* Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("* Telefono");
		lblTelfono.setBounds(10, 42, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField("");
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField("");
		txtTelefono.setBounds(133, 39, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(165, 476, 113, 36);
		panel.add(btnAgregarPersona);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 72, 113, 15);
		panel.add(lblEmail);

		textFieldEmail = new JTextField("");
		textFieldEmail.setBounds(133, 69, 164, 20);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblCumpleaos = new JLabel("Fecha de Cumpleaños");
		lblCumpleaos.setBounds(10, 110, 124, 15);
		panel.add(lblCumpleaos);

		JLabel lblTipoContacto = new JLabel("Tipo contacto");
		lblTipoContacto.setBounds(10, 141, 113, 15);
		panel.add(lblTipoContacto);

		comboBoxTipoContacto = new JComboBox<String>();
		comboBoxTipoContacto.setBounds(133, 136, 164, 24);
		panel.add(comboBoxTipoContacto);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 289, 70, 15);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 326, 70, 15);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(133, 370, 70, 15);
		panel.add(lblPiso);

		textField_1Calle = new JTextField("");
		textField_1Calle.setBounds(75, 286, 223, 19);
		panel.add(textField_1Calle);
		textField_1Calle.setColumns(10);

		textFieldPiso = new JTextField("");
		textFieldPiso.setBounds(213, 367, 84, 19);
		panel.add(textFieldPiso);
		textFieldPiso.setColumns(10);

		textFieldAltura = new JTextField("");
		textFieldAltura.setBounds(75, 323, 48, 19);
		panel.add(textFieldAltura);
		textFieldAltura.setColumns(10);

		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(10, 370, 70, 15);
		panel.add(lblDpto);

		textFieldDpto = new JTextField("");
		textFieldDpto.setBounds(70, 367, 53, 19);
		panel.add(textFieldDpto);
		textFieldDpto.setColumns(10);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 246, 70, 15);
		panel.add(lblLocalidad);

		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(133, 241, 164, 24);
		panel.add(comboBoxLocalidad);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(184, 100, 113, 25);
		panel.add(dateChooser);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 211, 70, 15);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setBounds(133, 206, 164, 24);
		panel.add(comboBoxProvincia);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setBounds(133, 171, 164, 24);
		panel.add(comboBoxPais);
		
		JLabel lblPas = new JLabel("País");
		lblPas.setBounds(10, 176, 70, 15);
		panel.add(lblPas);
		
		JLabel lblNewLabel = new JLabel("Equipo de Futbol");
		lblNewLabel.setBounds(10, 420, 107, 20);
		panel.add(lblNewLabel);
		
		textFieldEquipo = new JTextField();
		textFieldEquipo.setBounds(133, 417, 164, 26);
		panel.add(textFieldEquipo);
		textFieldEquipo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo postal");
		lblNewLabel_1.setBounds(133, 323, 87, 20);
		panel.add(lblNewLabel_1);
		
		textFieldCodigoPostal = new JTextField();
		textFieldCodigoPostal.setBounds(211, 320, 86, 20);
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