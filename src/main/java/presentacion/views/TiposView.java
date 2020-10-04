package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.TipoContactoDTO;

@SuppressWarnings("serial")
public class TiposView extends JDialog {

	final String[] nombreColumnas = new String[] { "TipoContacto" };
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEditar;
	private JButton btnNewButtonEliminar;
	private JButton btnNewButtonSalvar;

	private List<TipoContactoDTO> tipos;
	private static TiposView vista;
	private JScrollPane scrollPane;
	private JTable table;

	public static TiposView getInstance() {
		if (vista == null)
			vista = new TiposView();
		return vista;
	}

	private TiposView() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 475, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Tipos de Contacto");

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar);

		btnNewButtonEliminar = new JButton("Eliminar");

		btnNewButtonSalvar = new JButton("Crear");
		toolBar.add(btnNewButtonSalvar);

		btnNewButtonEditar = new JButton("Editar");
		toolBar.add(btnNewButtonEditar);
		toolBar.add(btnNewButtonEliminar);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tableModel = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		setModal(true);
		setResizable(false);
	}

	public void clearData() {
		tipos = null;
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(nombreColumnas);
	}

	public void setData(List<TipoContactoDTO> dtos) {
		assert dtos != null;
		tipos = dtos;
		for (TipoContactoDTO loc : dtos) {
			Object[] row = { loc.getNombre() };
			tableModel.addRow(row);
		}
	}

	public TipoContactoDTO getData() {
		int rows = table.getSelectedRowCount();
		if (rows != 1)	return null;
		int row = table.getSelectedRow();
		return tipos.get(row);
	}

	public void open() {
		setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		btnNewButtonSalvar.addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		btnNewButtonEditar.addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		btnNewButtonEliminar.addActionListener(object);
	}
}
