package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class VistaAbmLocalidades extends JFrame {

	JPanel contentPane;
	final String[] nombreColumnas = new String[] { "LocalidadNombre", "ID" };
	JTextField textFieldNombre;
	JTable table;
	DefaultTableModel tableModel;
	JButton btnNewButtonEditar;
	JButton btnNewButtonEliminar;
	JButton btnNewButtonSalvar;

	public VistaAbmLocalidades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulario de localidades", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(15, 16, 654, 99);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Localidad nombre");
		lblNewLabel.setBounds(15, 44, 145, 20);
		panel.add(lblNewLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(175, 38, 332, 26);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		btnNewButtonSalvar = new JButton("Salvar");
		btnNewButtonSalvar.setBounds(522, 35, 115, 29);
		panel.add(btnNewButtonSalvar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Localidades registradas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 144, 654, 266);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		scrollPane.setColumnHeaderView(panel_2);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar);

		btnNewButtonEliminar = new JButton("Eliminar");

		btnNewButtonEditar = new JButton("Editar");
		toolBar.add(btnNewButtonEditar);
		toolBar.add(btnNewButtonEliminar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);

		tableModel = new DefaultTableModel(null, nombreColumnas);
		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JButton getBtnNewButtonEditar() {
		return btnNewButtonEditar;
	}

	public JButton getBtnNewButtonEliminar() {
		return btnNewButtonEliminar;
	}

	public JButton getBtnNewButtonSalvar() {
		return btnNewButtonSalvar;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
