package presentacion.views;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;
import repositories.jdbc.Conexion;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class WorkbenchView {
	
	static final String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Email", "Fecha cumple", "Tipo", "Calle", "Altura",
			"Piso", "Dpto", "Localidad", "Provincia", "Pais", "Equipo", "CodigoPostal", "ID" };
	
	JFrame frame;
	JTable tablaPersonas;
	DefaultTableModel modelPersonas;
	JButton btnAgregar;
	JButton btnBorrar;
	JButton btnReporte;
	JButton btnEditar;
	JPanel panel_1;
	JMenuItem mntmNewMenuItemPaises;
	JMenuItem mntmNewMenuItemProvincias;
	JMenuItem mntmNewMenuItemLocalidades;
	JMenuItem mntmNewMenuItemTipos;
	static WorkbenchView vista;
	
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

		JToolBar toolBar = new JToolBar();
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

	public void open() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
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
	
	public PersonaDTO getData() {
		if(tablaPersonas.getSelectedRowCount() != 1) return null;
		int row = tablaPersonas.getSelectedRow();
		PersonaDTO dto = new PersonaDTO
				.Builder(getValueAt(row, 0), getValueAt(row, 1))
				.email(getValueAt(row, 2))
				.tipoContacto(getValueAt(row, 4))
				.calle(getValueAt(row, 5))
				.altura(getValueAt(row, 6))
				.piso(getValueAt(row, 7))
				.dpto(getValueAt(row, 8))
				.localidad(getValueAt(row, 9))
				.provincia(getValueAt(row, 10))
				.pais(getValueAt(row, 11))
				.equipoFutbol(getValueAt(row, 12))
				.codigoPostal(getValueAt(row, 13))
				.build();
		if(getValueAt(row, 14).isEmpty()) dto.setIdPersona(null);
		else dto.setIdPersona(Integer.parseInt(getValueAt(row, 14)));
		try {
			dto.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(getValueAt(row, 3)));
		} catch (ParseException e) {
			dto.setFechaNacimiento(null);
		}
		return dto;
	}

	String getValueAt(int row, int column) {
		Object obj = modelPersonas.getValueAt(row, column);
		if(obj != null) {
			return modelPersonas.getValueAt(row, column).toString();
		}
		else return "";
	}
	
	public void setData(PersonaDTO [] personas) {
		for(PersonaDTO p : personas) {
			if(p != null) {
				Object[] fila = { 
					p.getNombre(), p.getTelefono(), p.getEmail(), p.getFechaNacimiento(), p.getTipoContacto(),
					p.getCalle(), p.getAltura(), p.getPiso(), p.getDpto(), p.getLocalidad(), p.getProvincia(), p.getPais(),
					p.getEquipoFutbol(), p.getCodigoPostal(), p.getId()
				};
				modelPersonas.addRow(fila);	
			}
		}
	}
	
	public void clearData() {
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
}