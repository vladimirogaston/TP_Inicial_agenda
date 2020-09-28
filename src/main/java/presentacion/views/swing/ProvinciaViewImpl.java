package presentacion.views.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ProvinciaViewImpl extends JDialog {

	private static ProvinciaViewImpl vista;
	private JPanel contentPane;

	private DefaultTableModel tableModel;
	private JTable table;
	private final String[] nombreColumnas = new String[] { "Provincia", "Pais", "ID" };

	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnEliminar;

	public static ProvinciaViewImpl getInstance() {
		if (vista == null)
			vista = new ProvinciaViewImpl();
		return vista;
	}

	public ProvinciaViewImpl() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Provincia registrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
	
	public Object[] displayForm(String[] paises) {
		Object [] ret = null;
		if(paises != null) {
			JPanel fields = new JPanel(new FlowLayout());
			JLabel labelnombre = new JLabel("Nombre");
			JTextField field = new JTextField(10);
			JComboBox<String> comboBox = new JComboBox<>(paises);
			fields.add(labelnombre);
			fields.add(field);
			fields.add(comboBox);
			int result = JOptionPane.showConfirmDialog(null, fields, "Pais", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch (result) {
			    case JOptionPane.OK_OPTION:
			        break;
			}
			String prov = comboBox.getSelectedItem() == null ? null : comboBox.getSelectedItem().toString();
			ret = new Object [] { field.getText().toString(), prov};
		}
		return ret;	
	}
	
	public Object [] displayForm(String [] paises, String provincia, String pais) {
		Object [] ret = null;
		if(paises != null) {
			JPanel fields = new JPanel(new FlowLayout());
			JLabel labelnombre = new JLabel("Nombre");
			JTextField field = new JTextField(10);
			JComboBox<String> comboBox = new JComboBox<>(paises);
			fields.add(labelnombre);
			fields.add(field);
			fields.add(comboBox);
			comboBox.setSelectedItem(provincia);
			field.setText(provincia);
			int result = JOptionPane.showConfirmDialog(null, fields, "Editor de Provincia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch (result) {
			    case JOptionPane.OK_OPTION:
			        break;
			}
			ret = new Object [] { field.getText().toString(), comboBox.getSelectedItem().toString() };
		}
		return ret;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
}
