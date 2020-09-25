package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VistaAbmTiposDeContacto extends JDialog {
	JPanel contentPane;
	final String[] nombreColumnas = new String[] { "TipoContacto", "ID" };
	JTable table;
	DefaultTableModel tableModel;
	JButton btnNewButtonEditar;
	JButton btnNewButtonEliminar;
	JButton btnNewButtonSalvar;
	static VistaAbmTiposDeContacto vista;

	public static VistaAbmTiposDeContacto getInstance() {
		if (vista == null)
			vista = new VistaAbmTiposDeContacto();
		return vista;
	}

	public String displayForm() {
		return JOptionPane.showInputDialog("Nombre del Tipo de Contacto");
	}

	VistaAbmTiposDeContacto() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 706, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("ABM Tipo de Contacto");
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tipo de Contacto registrado", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
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

		btnNewButtonSalvar = new JButton("Crear");
		toolBar.add(btnNewButtonSalvar);

		btnNewButtonEditar = new JButton("Editar");
		toolBar.add(btnNewButtonEditar);

		btnNewButtonEliminar = new JButton("Eliminar");
		toolBar.add(btnNewButtonEliminar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);

		tableModel = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		scrollPane_1.setViewportView(table);

		setModal(true);
		ocultarColumnaId();
	}

	void ocultarColumnaId() {
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
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
