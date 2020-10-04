package presentacion.views;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class WorkbenchView {
	
	static final String[] nombreColumnas = { "Nombre y Apellido", "Telefono", "Email", "Cumpleaños", "Tipo", "Calle", "Altura",
			"Piso", "Departamento", "Localidad", "Provincia", "Pais", "Equipo", "CP" };
	
	private JFrame frame;
	private JTable tablaPersonas;
	private DefaultTableModel modelPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnEditar;
	private JPanel panel_1;
	private JMenuItem mntmNewMenuItemPaises;
	private JMenuItem mntmNewMenuItemProvincias;
	private JMenuItem mntmNewMenuItemLocalidades;
	private JMenuItem mntmNewMenuItemTipos;

	private JToolBar toolBar;
	static WorkbenchView vista;
	private Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/agenda.png"));

	private List<PersonaDTO> personas;
	
	public static WorkbenchView getInstance() {
		if(vista == null) vista = new WorkbenchView();
		return vista;
	}
	
	@SuppressWarnings("serial")
	WorkbenchView() {
		super();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1132, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Agenda");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(icon);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(0, 39, 1123, 270);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) { return false; } 
		};
		tablaPersonas = new JTable(modelPersonas);
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
	
		spPersonas.setViewportView(tablaPersonas);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(0, 308, 1123, 39);
		panel.add(panel_1);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);

		btnAgregar = new JButton("Agregar");
		toolBar.add(btnAgregar);

		btnBorrar = new JButton("Borrar");
		toolBar.add(btnBorrar);

		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);

		btnReporte = new JButton("Reporte");
		toolBar.add(btnReporte);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1123, 39);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);

		mntmNewMenuItemPaises = new JMenuItem("Paises");
		mnNewMenu.add(mntmNewMenuItemPaises);
		
		mntmNewMenuItemProvincias = new JMenuItem("Provincias");
		mnNewMenu.add(mntmNewMenuItemProvincias);
		
		mntmNewMenuItemLocalidades = new JMenuItem("Localidades");
		mnNewMenu.add(mntmNewMenuItemLocalidades);

		mntmNewMenuItemTipos = new JMenuItem("Tipos");
		mnNewMenu.add(mntmNewMenuItemTipos);
	}
	
	public JMenuItem getMntmNewMenuItemPaises() {
		return mntmNewMenuItemPaises;
	}

	public JMenuItem getMntmNewMenuItemProvincias() {
		return mntmNewMenuItemProvincias;
	}

	public JMenuItem getMntmNewMenuItemLocalidades() {
		return mntmNewMenuItemLocalidades;
	}

	public JMenuItem getMntmNewMenuItemTipos() {
		return mntmNewMenuItemTipos;
	}
	
	public void open() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}
	
	public PersonaDTO getData() {
		if(tablaPersonas.getSelectedRowCount() != 1) return null;
		int row = tablaPersonas.getSelectedRow();
		return personas.get(row);
	}
	
	public void setData(List<PersonaDTO> personas) {
		this.personas = personas;
		for(PersonaDTO p : personas) {
			Object[] fila = { 
				p.getNombre(), p.getTelefono(), p.getEmail(), p.getFechaNacimiento(), p.getTipoContacto(),
				p.getCalle(), p.getAltura(), p.getPiso(), p.getDpto(), p.getLocalidad(), p.getProvincia(), p.getPais(),
				p.getEquipoFutbol(), p.getCodigoPostal()
			};
			modelPersonas.addRow(fila);	
		}
	}
	
	public void close() {
		System.exit(0);
	}
	
	public void clearData() {
		personas = null;
		modelPersonas.setRowCount(0);
		modelPersonas.setColumnCount(0);
		modelPersonas.setColumnIdentifiers(nombreColumnas);
	}
		
	public void setActionSave(ActionListener listener) {
		btnAgregar.addActionListener(listener);
	}

	public void setActionUpdate(ActionListener listener) {
		btnEditar.addActionListener(listener);
	}

	public void setActionDelete(ActionListener listener) {
		btnBorrar.addActionListener(listener);
	}

	public void setActionReport(ActionListener listener) {
		btnReporte.addActionListener(listener);
	}

	public void lockOptions() {
		this.toolBar.setEnabled(false);
	}
	
	public void unLockOptions() {
		this.toolBar.setEnabled(true);
	}
}