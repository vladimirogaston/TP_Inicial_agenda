package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dto.PaisDTO;

@SuppressWarnings("serial")
public class PaisView extends JDialog {

	private final String[] nombreColumnas = new String[] { "PaisNombre" };
	private List<PaisDTO> paises;

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JToolBar toolBar;
	private JTable table;

	private static PaisView vista;

	public static PaisView getInstance() {
		if (vista == null)
			vista = new PaisView();
		return vista;
	}

	private PaisView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 283, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tableModel = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar);

		btnSalvar = new JButton("Crear");
		toolBar.add(btnSalvar);

		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		toolBar.add(btnEliminar);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		tableModel = new DefaultTableModel(null, nombreColumnas);
		table = new JTable(tableModel);

		scrollPane.setViewportView(table);

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
		paises = null;
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.setColumnIdentifiers(nombreColumnas);
	}

	public void setData(List<PaisDTO> dtos) {
		assert dtos != null;
		paises = dtos;
		for (PaisDTO loc : dtos) {
			Object[] row = { loc.getNombre() };
			tableModel.addRow(row);
		}
	}

	public PaisDTO getData() {
		if (table.getSelectedRowCount() != 1)
			return null;
		int row = table.getSelectedRow();
		return paises.get(row);
	}

	public void open() {
		setVisible(true);
	}

	public void setActionSave(ActionListener object) {
		btnSalvar.addActionListener(object);
	}

	public void setActionUpdate(ActionListener object) {
		btnEditar.addActionListener(object);
	}

	public void setActionDelete(ActionListener object) {
		btnEliminar.addActionListener(object);
	}
}
