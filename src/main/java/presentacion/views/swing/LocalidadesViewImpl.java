package presentacion.views.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import presentacion.views.LocalidadView;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class LocalidadesViewImpl extends JDialog implements LocalidadView {

	JPanel contentPane;
	final String[] nombreColumnas = new String[] { "LocalidadNombre","Provincia","ID" };
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEliminar;
	private JButton btnSalvar;
	private JButton buttonEditar;
	static LocalidadesViewImpl vista;
		
	public static LocalidadesViewImpl getInstance() {
		if(vista == null) vista = new LocalidadesViewImpl();
		return vista;
	}
	
	private LocalidadesViewImpl() {
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
	
	@Override
	public void clearData() {
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(nombreColumnas);
	}
	
	@Override
	public void setData(List<LocalidadDTO> localidades) {
		assert localidades != null;
		for (LocalidadDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getProvincia(), loc.getId() };
			tableModel.addRow(row);
		}	
	}
	
	@Override
	public LocalidadDTO getData() {
		if(table.getSelectedColumnCount() != 1) {
			return null;
		}
		int row = table.getSelectedRow();
		String locNom = tableModel.getValueAt(row, 0).toString();
		String provNom = tableModel.getValueAt(row, 1).toString();
		int locID = Integer.parseInt(tableModel.getValueAt(row, 2).toString());
		return new LocalidadDTO(locID, locNom, provNom);
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
		buttonEditar.addActionListener(object);
	}

	@Override
	public void setActionDelete(ActionListener object) {
		btnNewButtonEliminar.addActionListener(object);
	}
}
