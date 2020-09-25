package presentacion.views.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class LocalidadesView extends JDialog {

	JPanel contentPane;
	final String[] nombreColumnas = new String[] { "LocalidadNombre","Provincia","ID" };
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEliminar;
	private JButton btnSalvar;
	private JButton buttonEditar;
	static LocalidadesView vista;
		
	public static LocalidadesView getInstance() {
		if(vista == null) vista = new LocalidadesView();
		return vista;
	}
	
	private LocalidadesView() {
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

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getButtonEditar() {
		return buttonEditar;
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
