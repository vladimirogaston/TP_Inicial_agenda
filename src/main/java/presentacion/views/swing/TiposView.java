package presentacion.views.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TiposView extends JDialog {
	
	final String[] nombreColumnas = new String[] { "TipoContacto", "ID" };
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEditar;
	private JButton btnNewButtonEliminar;
	private JButton btnNewButtonSalvar;
	private static TiposView vista;
	
	public static TiposView getInstance() {
		if(vista == null) vista = new TiposView();
		return vista;
	}
	
	private TiposView() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 706, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Tipos de Contactos registrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 16, 654, 266);
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
		
				btnNewButtonSalvar = new JButton("Crear");
				toolBar.add(btnNewButtonSalvar);

		btnNewButtonEditar = new JButton("Editar");
		toolBar.add(btnNewButtonEditar);
		toolBar.add(btnNewButtonEliminar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);

		tableModel = new DefaultTableModel(null, nombreColumnas){
			public boolean isCellEditable(int row, int column) { return false; } 
		};
		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);
		
		setModal(true);
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
}
