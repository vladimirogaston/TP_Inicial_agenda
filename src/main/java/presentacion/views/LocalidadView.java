package presentacion.views;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class LocalidadView extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnNewButtonEliminar;
	private JButton btnSalvar;
	private JButton buttonEditar;

	final String[] nombreColumnas = new String[] { "LocalidadNombre", "Provincia" };
	private List<LocalidadDTO> localidades;

	static LocalidadView vista;

	public static LocalidadView getInstance() {
		if (vista == null)
			vista = new LocalidadView();
		return vista;
	}

	private LocalidadView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Localidades");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Localidades registradas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		table = new JTable(tableModel);

		table.getColumnModel().getColumn(0).setMaxWidth(20);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setWidth(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);

		scrollPane_1.setViewportView(table);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar);

		btnSalvar = new JButton("Crear");

		toolBar.add(btnSalvar);

		buttonEditar = new JButton("Editar");
		toolBar.add(buttonEditar);

		btnNewButtonEliminar = new JButton("Eliminar");
		toolBar.add(btnNewButtonEliminar);
		setModal(true);
		setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearData();
			}
		});
	}

	public void clearData() {
		localidades = null;
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(nombreColumnas);
	}

	public void setData(List<LocalidadDTO> localidades) {
		assert localidades != null;
		this.localidades = localidades;
		for (LocalidadDTO loc : localidades) {
			Object[] row = { loc.getNombre(), loc.getProvincia() };
			tableModel.addRow(row);
		}
	}

	public LocalidadDTO getData() {
		if (table.getSelectedColumnCount() != 1) return null;
		int row = table.getSelectedRow();
		return localidades.get(row);
	}

	public void open() {
		setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		btnSalvar.addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		buttonEditar.addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		btnNewButtonEliminar.addActionListener(object);
	}
}
