package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VistaAbmLocalidades extends JDialog {

	JPanel contentPane;
	final String[] nombreColumnas = new String[] { "LocalidadNombre","Provincia","ID" };
	JTable table;
	DefaultTableModel tableModel;
	JButton btnNewButtonEliminar;
	private JButton btnSalvar;
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getButtonEditar() {
		return buttonEditar;
	}

	private JButton buttonEditar;
	static VistaAbmLocalidades vista;
	
	public Object [] displayForm(String [] provincias) {
		Object [] ret = null;
		if(provincias != null) {
			JPanel fields = new JPanel(new FlowLayout());
			JLabel labelnombre = new JLabel("Nombre");
			JTextField field = new JTextField(10);
			JComboBox<String> comboBox = new JComboBox<>(provincias);
			fields.add(labelnombre);
			fields.add(field);
			fields.add(comboBox);
			int result = JOptionPane.showConfirmDialog(null, fields, "Provincia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch (result) {
			    case JOptionPane.OK_OPTION:
			        break;
			}
			ret = new Object [] { field.getText().toString(), comboBox.getSelectedItem().toString() };
		}
		return ret;	
	}
	
	public Object [] displayForm(String [] provincias, String loc, String provincia) {
		Object [] ret = null;
		if(provincias != null) {
			JPanel fields = new JPanel(new FlowLayout());
			JLabel labelnombre = new JLabel("Nombre");
			JTextField field = new JTextField(10);
			JComboBox<String> comboBox = new JComboBox<>(provincias);
			fields.add(labelnombre);
			fields.add(field);
			fields.add(comboBox);
			comboBox.setSelectedItem(provincia);
			field.setText(loc);
			int result = JOptionPane.showConfirmDialog(null, fields, "Provincia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch (result) {
			    case JOptionPane.OK_OPTION:
			        break;
			}
			ret = new Object [] { field.getText().toString(), comboBox.getSelectedItem().toString() };
		}
		return ret;
	}
	
	public static VistaAbmLocalidades getInstance() {
		if(vista == null) vista = new VistaAbmLocalidades();
		return vista;
	}
	
	VistaAbmLocalidades() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Localidades registradas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 16, 654, 394);
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
		
		btnSalvar = new JButton("Crear");
	
		toolBar.add(btnSalvar);
		
		buttonEditar = new JButton("Editar");
		toolBar.add(buttonEditar);

		btnNewButtonEliminar = new JButton("Eliminar");
		toolBar.add(btnNewButtonEliminar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);

		tableModel = new DefaultTableModel(null, nombreColumnas){
			public boolean isCellEditable(int row, int column) { return false; } 
		};;
		table = new JTable(tableModel);

		table.getColumnModel().getColumn(0).setMaxWidth(20);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setWidth(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);

		
		scrollPane_1.setViewportView(table);
		setModal(true);
	}

	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JButton getBtnNewButtonEliminar() {
		return btnNewButtonEliminar;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
