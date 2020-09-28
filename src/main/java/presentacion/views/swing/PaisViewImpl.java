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

import dto.PaisDTO;
import presentacion.views.PaisView;

@SuppressWarnings("serial")
public class PaisViewImpl extends JDialog implements PaisView {

	private static PaisViewImpl vista;
	private JPanel contentPane;

	private DefaultTableModel tableModel;
	private JTable table;
	private final String[] nombreColumnas = new String[] { "PaisNombre", "ID" };

	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnEliminar;

	public static PaisViewImpl getInstance() {
		if (vista == null)
			vista = new PaisViewImpl();
		return vista;
	}

	private PaisViewImpl() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Pais registrado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		toolBar.add(btnEliminar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);

		tableModel = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		;
		table = new JTable(tableModel);

		table.getColumnModel().getColumn(0).setMaxWidth(20);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setWidth(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);

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
	public void setData(List<PaisDTO> dtos) {
		assert dtos != null;
		for (PaisDTO loc : dtos) {
			Object[] row = { loc.getNombre(), loc.getId() };
			tableModel.addRow(row);
		}	
	}
	
	@Override
	public PaisDTO getData() {
		if(table.getSelectedRowCount() != 1) return null;
		int row = table.getSelectedRow();
		String nom = tableModel.getValueAt(row, 0).toString();
		int id = Integer.parseInt(tableModel.getValueAt(row, 1).toString());
		return new PaisDTO(id, nom);
	}
	
	@Override
	public void open() {
		setVisible(true);
	}

	@Override
	public void setActionSave(ActionListener object) {
		btnSalvar.addActionListener(object);
	}

	@Override
	public void setActionUpdate(ActionListener object) {
		btnEditar.addActionListener(object);
	}

	@Override
	public void setActionDelete(ActionListener object) {
		btnEliminar.addActionListener(object);
	}
}
