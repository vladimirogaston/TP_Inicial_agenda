package presentacion.views.swing;

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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dto.TipoContactoDTO;
import presentacion.views.TiposView;

@SuppressWarnings("serial")
public class TiposViewImpl extends JDialog implements TiposView {
	
	final String[] nombreColumnas = new String[] { "TipoContacto", "ID" };
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEditar;
	private JButton btnNewButtonEliminar;
	private JButton btnNewButtonSalvar;
	private static TiposViewImpl vista;
	
	public static TiposViewImpl getInstance() {
		if(vista == null) vista = new TiposViewImpl();
		return vista;
	}
	
	private TiposViewImpl() {
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

	@Override
	public void clearData() {
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(nombreColumnas);
	}
	
	@Override
	public void setData(List<TipoContactoDTO> dtos) {
		assert dtos != null;
		for (TipoContactoDTO loc : dtos) {
			Object[] row = { loc.getNombre(), loc.getId() };
			tableModel.addRow(row);
		}	
	}
	
	@Override
	public TipoContactoDTO getData() {
		int rows = table.getSelectedRowCount();
		if(rows != 1) return null;
		int row = table.getSelectedRow();
		String nom = tableModel.getValueAt(row, 0).toString();
		Object obj = tableModel.getValueAt(row, 1);
		Integer id = null;
		if(obj != null) id = Integer.parseInt(tableModel.getValueAt(row, 1).toString());
		return new TipoContactoDTO(id, nom);
	}
	
	@Override
	public void open() {
		setVisible(true);
	}

	@Override
	public void setActionSave(ActionListener object) {
		btnNewButtonSalvar.addActionListener(object);
	}
	
	@Override
	public void setActionUpdate(ActionListener object) {
		btnNewButtonEditar.addActionListener(object);
	}

	@Override
	public void setActionDelete(ActionListener object) {
		btnNewButtonEliminar.addActionListener(object);
	}
}
