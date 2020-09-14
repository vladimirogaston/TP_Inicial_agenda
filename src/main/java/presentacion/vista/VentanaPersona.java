package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 367);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(182, 344, 113, 23);
		panel.add(btnAgregarPersona);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 89, 113, 15);
		panel.add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(133, 81, 164, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCumpleaos = new JLabel("Cumpleaños");
		lblCumpleaos.setBounds(12, 122, 124, 15);
		panel.add(lblCumpleaos);
		
		JLabel lblTipoContacto = new JLabel("Tipo contacto");
		lblTipoContacto.setBounds(12, 165, 124, 15);
		panel.add(lblTipoContacto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(133, 156, 164, 24);
		panel.add(comboBox);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 207, 70, 15);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 239, 70, 15);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(135, 239, 70, 15);
		panel.add(lblPiso);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 205, 223, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(196, 237, 101, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(75, 237, 48, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(10, 268, 70, 15);
		panel.add(lblDpto);
		
		textField_4 = new JTextField();
		textField_4.setBounds(74, 266, 53, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 300, 70, 15);
		panel.add(lblLocalidad);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(133, 295, 164, 24);
		panel.add(comboBox_1);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
}

