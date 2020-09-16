package presentacion.vista;

import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class VentanaPersona extends JFrame {
	
	static final long serialVersionUID = 1L;
	static VentanaPersona INSTANCE;	
	JPanel contentPane;
	JTextField txtNombre;
	JTextField txtTelefono;
	JTextField textFieldEmail;
	JTextField textField_1Calle;
	JTextField textFieldPiso;
	JTextField textFieldAltura;
	JTextField textFieldDpto;
	JComboBox<String> comboBoxLocalidad;
	JDateChooser dateChooser;
	JComboBox<String> comboBoxTipoContacto;
	JButton btnAgregarPersona;
				
	public static VentanaPersona getInstance() {
		if(INSTANCE == null){
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		return INSTANCE;
	}

	private VentanaPersona() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 416);
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
		btnAgregarPersona.setBounds(186, 368, 113, 23);
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
		lblTipoContacto.setBounds(12, 175, 124, 15);
		panel.add(lblTipoContacto);
		
		comboBoxTipoContacto = new JComboBox<String>();
		comboBoxTipoContacto.setBounds(133, 166, 164, 24);
		panel.add(comboBoxTipoContacto);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 211, 70, 15);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 247, 70, 15);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(135, 247, 70, 15);
		panel.add(lblPiso);
		
		textField_1Calle = new JTextField("");
		textField_1Calle.setBounds(74, 209, 223, 19);
		panel.add(textField_1Calle);
		textField_1Calle.setColumns(10);
		
		textFieldPiso = new JTextField("");
		textFieldPiso.setBounds(196, 245, 101, 19);
		panel.add(textFieldPiso);
		textFieldPiso.setColumns(10);
		
		textFieldAltura = new JTextField("");
		textFieldAltura.setBounds(75, 245, 48, 19);
		panel.add(textFieldAltura);
		textFieldAltura.setColumns(10);
		
		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(10, 282, 70, 15);
		panel.add(lblDpto);
		
		textFieldDpto = new JTextField("");
		textFieldDpto.setBounds(74, 280, 53, 19);
		panel.add(textFieldDpto);
		textFieldDpto.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 324, 70, 15);
		panel.add(lblLocalidad);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(133, 319, 164, 24);
		panel.add(comboBoxLocalidad);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(135, 120, 159, 26);
		panel.add(dateChooser);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() { this.setVisible(true); }
	
	public JTextField getTxtNombre() { return txtNombre; }

	public JTextField getTxtTelefono() { return txtTelefono; }

	public JButton getBtnAgregarPersona() {	return btnAgregarPersona; }

	public void cerrar() {
		txtNombre.setText("");
		txtTelefono.setText("");
		textFieldEmail.setText("");
		textField_1Calle.setText("");
		textFieldPiso.setText("");
		textFieldAltura.setText("");
		textFieldDpto.setText("");
		comboBoxLocalidad.removeAllItems();
		comboBoxTipoContacto.removeAllItems();
		this.dispose();
	}	
	
	public void fillLocalidades(String loc) {
		comboBoxLocalidad.addItem(loc);
	}
	
	public void fillTiposContacto(String tipos) {
		comboBoxTipoContacto.addItem(tipos);
	}
	
	public String getFieldNombre() { return txtNombre.getText(); }
	
	public String getFieldTelefono() { return txtTelefono.getText(); }
	
	public String getFieldEmail() { return textFieldEmail.getText(); }
	
	public String getFieldTipoDeContacto() { return comboBoxTipoContacto.getSelectedItem().toString(); }
	
	public Date getFieldFechaDeCumpleaños() { return (Date) dateChooser.getDate(); }
	
	public String getFieldCalle() { return textField_1Calle.getText(); }
	
	public String getFieldAltura() { return textFieldAltura.getText(); }
	
	public String gettFieldPiso() { return textFieldPiso.getText(); }
	
	public String getFieldDepartamento() { return textFieldDpto.getText(); }
	
	public String getFieldLocalidad() { return comboBoxLocalidad.getSelectedItem().toString(); }
}